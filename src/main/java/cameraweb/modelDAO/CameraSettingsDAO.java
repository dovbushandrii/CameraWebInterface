package cameraweb.modelDAO;

import camera_api.CameraFactory;
import camera_api.interfaces.Camera;
import cameraweb.exceptions.NoDeviceFoundException;
import cameraweb.model.CameraSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CameraSettingsDAO {

    private Camera cam = null;
    private final CameraFactory camFac;

    @Autowired
    public CameraSettingsDAO(CameraFactory camFac) {
        this.camFac = camFac;
    }

    public void setCamera(int id) {
        if (this.cam != null) {
            this.cam.closeSession();
        }
        if (camFac.getDeviceCount() > id) {
            this.cam = camFac.getCamera(id);
        } else {
            throw new NoDeviceFoundException("Device ID is not valid");
        }
    }

    public void update(CameraSettings set) {
        this.cam.setExposure(set.getExposure());
        this.cam.setISO(set.getIso());
        this.cam.setAperture(set.getAperture());
        this.cam.setExposureComp(set.getExposureComp());
        this.cam.setFocusSettings(set.getAfSetting());
        this.cam.setWhiteBalance(set.getWbSetting());
        this.cam.setDriveMode(set.getDriveMode());
        this.cam.setColorSpace(set.getColorSpace());
    }

    public CameraSettings read() {
        return new CameraSettings(cam);
    }
}
