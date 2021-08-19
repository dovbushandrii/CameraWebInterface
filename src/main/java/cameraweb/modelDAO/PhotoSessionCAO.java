package cameraweb.modelDAO;

import camera_api.CameraFactory;
import camera_api.interfaces.Camera;
import cameraweb.exceptions.NoDeviceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoSessionCAO {
    private Camera cam = null;
    private final CameraFactory camFac;

    @Autowired
    public PhotoSessionCAO(CameraFactory camFac){
        this.camFac = camFac;
    }

    public void setCamera(int id){
        if(this.cam != null){
            this.cam.closeSession();
        }
        if (camFac.getDeviceCount() > id) {
            this.cam = camFac.getCamera(id);
            System.out.println(cam.openSession());
        }
        else{
            throw new NoDeviceFoundException("Device ID is not valid");
        }
    }

    public void takePicture(){
        if(this.cam != null){
            this.cam.takePicture();
        }
    }

    public void takePicture(double time){
        if(this.cam != null){
            this.cam.takePicture(time);
        }
    }
}
