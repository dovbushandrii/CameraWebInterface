package camera_api;

import camera_api.interfaces.camerasdk.Camera;
import camera_api.interfaces.camerasdk.CameraSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class CameraFactory {

    private final ProxyCompany company;

    @Autowired
    public CameraFactory(ProxyCompany company) {
        this.company = company;
    }

    public void updateCameraList() {
        company.getCompany().getCameraSDK().updateCameraList();
    }

    public int getDeviceCount() {
        return company.getCompany().getCameraSDK().getDeviceCount();
    }

    public Camera getCamera(int index) {
        return company.getCompany().getCameraSDK().getCamera(index);
    }

    public String getCameraName(int index) {
        return company.getCompany().getCameraSDK().getCameraName(index);
    }

    public String[] getCameraNameList() {
        return company.getCompany().getCameraSDK().getCameraNameList();
    }

    public String getCameraPort(int index) {
        return company.getCompany().getCameraSDK().getCameraPort(index);
    }
}
