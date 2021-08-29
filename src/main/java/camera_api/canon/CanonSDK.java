package camera_api.canon;

import camera_api.interfaces.camerasdk.CameraSDK;
import camera_api.canon.encodings.sdk.*;
import org.springframework.stereotype.Component;


@Component
public class CanonSDK implements CameraSDK {

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

    private static EdsError closeAllSessions() {
        EdsError err = EdsError.EDS_ERR_OK;
        for (CanonCamera canonCamera : deviceList) {
            err = canonCamera.closeSession();
            if (err != EdsError.EDS_ERR_OK) {
                break;
            }
        }
        return err;
    }

    private static void releaseCameraList() {
        for (CanonCamera canonCamera : deviceList) {
            canonCamera.release(permission);
        }
        deviceList = new CanonCamera[0];
    }

    private static CanonCamera findInDeviceList(CanonCamera cam) {
        for (CanonCamera dev : deviceList) {
            if (dev.equals(cam)) return dev;
        }
        return null;
    }

    private native String getCameraPortInfo(int index);

    /*----------------------------------------------------------------------------*/
    private static final boolean isInitialized = false;

    private native EdsError initializeNativeSDK();

    private native EdsError terminateNativeSDK();

    public EdsError initializeSDK() {
        System.load("C:/Users/DovbushAndriy/Desktop/demo/AstroSoft/CDLL/EDSDK.dll");
        System.load("C:/Users/DovbushAndriy/Desktop/demo/AstroSoft/CDLL/CameraForJava.dll");
        EdsError err = initializeNativeSDK();
        if (err == EdsError.EDS_ERR_OK) {
            setCameraList();
        }
        return err;
    }

    public EdsError terminateSDK() {
        EdsError err = closeAllSessions();
        if (err == EdsError.EDS_ERR_OK) {
            releaseCameraList();
            err = terminateNativeSDK();
        }
        return err;
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
        throw new IndexOutOfBoundsException("Invalid device index");
    }

    public String getCameraName(int index) {
        if (deviceList.length > index && index >= 0) {
            return deviceList[index].productName();
        }
        throw new IndexOutOfBoundsException("Invalid device index");
    }

    public String[] getCameraNameList() {
        String dnl[] = new String[deviceList.length];
        for (int i = 0; i < dnl.length; i++) {
            dnl[i] = getCameraName(i);
        }
        return dnl;
    }
    
    public String getCameraPort(int index) {
        if (deviceList.length > index && index >= 0) {
            return getCameraPortInfo(index);
        }
        throw new IndexOutOfBoundsException("Invalid device index");
    }

}