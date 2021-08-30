package camera_api;

import camera_api.exceptions.CameraNotFoundException;
import camera_api.interfaces.camerasdk.Camera;
import camera_api.interfaces.patterns.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProxyCamera implements Observer {

    private final ProxyCompany company;
    private Camera camera = null;

    @Autowired
    public ProxyCamera(ProxyCompany company) {
        this.company = company;
        company.addObserver(this);
    }

    public void loadCamera(int id) {
        Camera newCamera = company
                .getCompany()
                .getCameraSDK()
                .getCamera(id);
        if (!newCamera.equals(camera)) {
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

    @Override
    public void handleEvent() {
        this.camera = null;
    }
}
