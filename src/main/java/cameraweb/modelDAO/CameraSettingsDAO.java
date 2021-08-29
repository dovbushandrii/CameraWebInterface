package cameraweb.modelDAO;

import camera_api.ProxyCameraFactory;
import camera_api.interfaces.Camera;
import cameraweb.model.CameraSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CameraSettingsDAO {
    private final ProxyCameraFactory camFac;

    @Autowired
    public CameraSettingsDAO(ProxyCameraFactory camFac) {
        this.camFac = camFac;
    }

    public void update(CameraSettings set) {
        Camera cam = this.camFac.getCamera();
        cam.setExposure(set.getExposure());
        cam.setISO(set.getIso());
        cam.setAperture(set.getAperture());
        cam.setExposureComp(set.getExposureComp());
        cam.setFocusSettings(set.getAfSetting());
        cam.setWhiteBalance(set.getWbSetting());
        cam.setDriveMode(set.getDriveMode());
        cam.setColorSpace(set.getColorSpace());
    }

    public CameraSettings read() {
        return new CameraSettings(camFac.getCamera());
    }
}
