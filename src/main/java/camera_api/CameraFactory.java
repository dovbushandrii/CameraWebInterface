package camera_api;

import camera_api.exceptions.SDKIsNotInitializedException;
import camera_api.interfaces.Camera;
import camera_api.interfaces.CameraSDK;
import camera_api.interfaces.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class CameraFactory {

    private final CameraSDK sdk;

    private static boolean isInit = false;

    @Autowired
    public CameraFactory(CameraSDK sdk) {
        this.sdk = sdk;
    }

    @PostConstruct
    public void initialize() {
        isInit = true;
        this.sdk.initializeSDK();
    }

    @PreDestroy
    public void terminate() {
        isInit = false;
        this.sdk.terminateSDK();
    }

    public void updateCameraList() {
        if (isInit) {
            this.sdk.updateCameraList();
        } else {
            throw new SDKIsNotInitializedException("Camera SDK is not initialized");
        }
    }

    public int getDeviceCount() {
        if (isInit) {
            return this.sdk.getDeviceCount();
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }

    public Camera getCamera(int index) {
        if (isInit) {
            return this.sdk.getCamera(index);
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }

    public String getCameraName(int index) {
        if (isInit) {
            return this.sdk.getCameraName(index);
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }

    public String[] getCameraNameList() {
        if (isInit) {
            return this.sdk.getCameraNameList();
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }

    public String getCameraPort(int index) {
        if (isInit) {
            return this.sdk.getCameraPort(index);
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }
}
