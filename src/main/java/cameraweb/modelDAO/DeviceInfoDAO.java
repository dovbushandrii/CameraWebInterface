package cameraweb.modelDAO;

import camera_api.CameraFactory;
import camera_api.interfaces.Camera;
import cameraweb.exceptions.NoDeviceFoundException;
import cameraweb.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoDAO {

    private Camera cam = null;
    private CameraFactory camFac;

    @Autowired
    public DeviceInfoDAO(CameraFactory camFac) {
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

    public DeviceInfo read() {
        return new DeviceInfo(this.cam);
    }
}
