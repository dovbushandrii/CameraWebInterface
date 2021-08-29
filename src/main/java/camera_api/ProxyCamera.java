package camera_api;

import camera_api.exceptions.NoCameraInstanceWasLoadedException;
import camera_api.interfaces.camerasdk.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProxyCamera {

    private final CameraFactory cameraFactory;
    private Camera camera = null;


    @Autowired
    public ProxyCamera(CameraFactory cameraFactory) {
        this.cameraFactory = cameraFactory;
    }

    //TODO: Listener for Company Change
    public void loadCamera(int id) {
        if (camera != null) {
            camera.closeSession();
        }
        camera = cameraFactory.getCamera(id);
        this.camera.openSession();
    }

    public Camera getCamera() {
        if (camera == null) {
            throw new NoCameraInstanceWasLoadedException("No camera was loaded before");
        } else {
            return camera;
        }
    }

    public String[] getCameraNameList() {
        return this.cameraFactory.getCameraNameList();
    }
}
