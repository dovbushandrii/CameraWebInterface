package camera_api.canon;

import camera_api.exceptions.CameraNotFoundException;
import camera_api.exceptions.CameraSessionCloseException;
import camera_api.interfaces.camerasdk.CameraSDK;
import camera_api.canon.encodings.sdk.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@PropertySource("classpath:cameralibs.properties")
public class CanonSDK implements CameraSDK {

    @Value("${canon.sdk.path}")
    private String sdkPath;
    @Value("${canon.jnilib.path}")
    private String jniLibPath;

    public static class EDSDKPermit {
        private EDSDKPermit() {
        }
    }

    private static final EDSDKPermit permission = new EDSDKPermit();

    private static CanonCamera[] deviceList = new CanonCamera[0];

    /*----------------------------------------------------------------------------*/

    private static native int getCameraListSize();

    private static void setCameraList() {
        int size = getCameraListSize();
        deviceList = new CanonCamera[size];
        for (int i = 0; i < size; i++) {
            deviceList[i] = CanonCamera.createCamera(permission, i);
        }
    }

    private static void closeAllSessions() {
        Arrays.stream(deviceList)
                .forEach(camera -> {
            if (camera.closeSession() != EdsError.EDS_ERR_OK) {
                throw new CameraSessionCloseException();
            }
        });
    }

    private static void releaseCameraList() {
        Arrays.stream(deviceList).forEach(camera -> camera.release(permission));
        deviceList = new CanonCamera[0];
    }

    private native String getCameraPortInfo(int index);

    /*----------------------------------------------------------------------------*/
    private static final boolean isInitialized = false;

    private native EdsError initializeNativeSDK();

    private native EdsError terminateNativeSDK();

    public EdsError initializeSDK() {
        System.load(sdkPath);
        System.load(jniLibPath);
        EdsError err = initializeNativeSDK();
        if (err == EdsError.EDS_ERR_OK) {
            setCameraList();
        }
        return err;
    }

    public EdsError terminateSDK() {
        closeAllSessions();
        releaseCameraList();
        return terminateNativeSDK();
    }
    /*----------------------------------------------------------------------------*/

    public void updateCameraList() {
        releaseCameraList();
        setCameraList();
    }

    public int getDeviceCount() {
        return deviceList.length;
    }

    public CanonCamera getCamera(int index) {
        if (deviceList.length > index && index >= 0) {
            return deviceList[index];
        }
        throw new CameraNotFoundException("Invalid device index");
    }

    public String getCameraName(int index) {
        if (deviceList.length > index && index >= 0) {
            return deviceList[index].productName();
        }
        throw new CameraNotFoundException("Invalid device index");
    }

    public String[] getCameraNameList() {
        String[] cameraNameList = new String[deviceList.length];
        for (int i = 0; i < cameraNameList.length; i++) {
            cameraNameList[i] = getCameraName(i);
        }
        return cameraNameList;
    }

    public String getCameraPort(int index) {
        if (deviceList.length > index && index >= 0) {
            return getCameraPortInfo(index);
        }
        throw new CameraNotFoundException("Invalid device index");
    }

}