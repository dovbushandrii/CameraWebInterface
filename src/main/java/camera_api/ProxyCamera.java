package camera_api;

import camera_api.exceptions.CameraNotFoundException;
import camera_api.interfaces.camerasdk.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProxyCamera {

    private final ProxyCompany company;
    private Camera camera = null;


    @Autowired
    public ProxyCamera(ProxyCompany company) {
        this.company = company;
    }

    //TODO: Listener for Company Change
    public void loadCamera(int id) {
        Camera newCamera = company
                .getCompany()
                .getCameraSDK()
                .getCamera(id);
        if(!newCamera.equals(camera)) {
            if (camera != null) {
                camera.closeSession();
            }
            camera = newCamera;
            this.camera.openSession();
        }
    }

    public Camera getCamera() {
        if (camera == null) {
            throw new CameraNotFoundException("No camera was loaded before");
        } else {
            return camera;
        }
    }
}
