package webapp.modelDAO;

import camera_api.CameraLoader;
import webapp.model.CameraSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CameraSettingsDAO {
    private final CameraLoader cameraLoader;

    @Autowired
    public CameraSettingsDAO(CameraLoader cameraLoader) {
        this.cameraLoader = cameraLoader;
    }

    public void update(CameraSettings settings) {
        settings.applyCameraSettings(cameraLoader.getCamera());
    }

    public CameraSettings read() {
        return new CameraSettings(cameraLoader.getCamera());
    }
}
