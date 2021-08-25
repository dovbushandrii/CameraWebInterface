package cameraweb.modelDAO;

import camera_api.CameraFactory;
import camera_api.interfaces.Camera;
import cameraweb.exceptions.NoDeviceFoundException;
import cameraweb.model.CameraSettings;
import cameraweb.model.pictureset.inter.PictureSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhotoSessionCAO {
    private Camera cam = null;
    private final CameraFactory camFac;

    @Autowired
    public PhotoSessionCAO(CameraFactory camFac) {
        this.camFac = camFac;
    }

    public void setCamera(int id) {
        if (this.cam != null) {
            this.cam.closeSession();
        }
        if (camFac.getDeviceCount() > id) {
            this.cam = camFac.getCamera(id);
            System.out.println(cam.openSession());
        } else {
            throw new NoDeviceFoundException("Device ID is not valid");
        }
    }

    public void startSession(List<PictureSet> sets) {

    }
}
