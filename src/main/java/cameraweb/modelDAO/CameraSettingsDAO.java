package cameraweb.modelDAO;

import camera_api.ProxyCamera;
import camera_api.interfaces.camerasdk.Camera;
import cameraweb.model.CameraSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CameraSettingsDAO {
    private final ProxyCamera camFac;

    @Autowired
    public CameraSettingsDAO(ProxyCamera camFac) {
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
