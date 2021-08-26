package camera_api;

import camera_api.interfaces.Camera;
import camera_api.interfaces.CameraSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class CameraFactory {

    private final CameraSDK sdk;

    @Autowired
    public CameraFactory(CameraSDK sdk) {
        this.sdk = sdk;
    }

    @PostConstruct
    public void initialize() {
        this.sdk.initializeSDK();
    }

    @PreDestroy
    public void terminate() {
        this.sdk.terminateSDK();
    }

    public void updateCameraList() {
        this.sdk.updateCameraList();
    }

    public int getDeviceCount() {
        return this.sdk.getDeviceCount();
    }

    public Camera getCamera(int index) {
        return this.sdk.getCamera(index);
    }

    public String getCameraName(int index) {
        return this.sdk.getCameraName(index);
    }

    public String[] getCameraNameList() {
        return this.sdk.getCameraNameList();
    }

    public String getCameraPort(int index) {
        return this.sdk.getCameraPort(index);
    }
}
