package webapp.modelDAO;

import camera_api.CameraLoader;
import camera_api.interfaces.camerasdk.Camera;
import webapp.model.CameraSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CameraSettingsDAO {
    private final CameraLoader camera;

    @Autowired
    public CameraSettingsDAO(CameraLoader camera) {
        this.camera = camera;
    }

    public void update(CameraSettings settings) {
        Camera cam = this.camera.getCamera();
        cam.setExposure(settings.getExposure());
        cam.setISO(settings.getIso());
        cam.setAperture(settings.getAperture());
        cam.setExposureComp(settings.getExposureComp());
        cam.setFocusSettings(settings.getAfSetting());
        cam.setWhiteBalance(settings.getWbSetting());
        cam.setDriveMode(settings.getDriveMode());
        cam.setColorSpace(settings.getColorSpace());
    }

    public CameraSettings read() {
        return new CameraSettings(camera.getCamera());
    }
}
