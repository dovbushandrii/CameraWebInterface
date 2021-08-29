package cameraweb.modelDAO;

import camera_api.ProxyCameraFactory;
import camera_api.interfaces.Camera;
import camera_api.interfaces.CameraProp;
import cameraweb.model.pictureset.inter.PictureSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class PhotoSessionCAO {
    private final ProxyCameraFactory camFac;

    @Autowired
    public PhotoSessionCAO(ProxyCameraFactory camFac) {
        this.camFac = camFac;
    }

    public void startSession(List<PictureSet> sets) {
        for (PictureSet set : sets) {
            processPictureSet(set);
        }
    }

    private void processPictureSet(PictureSet set) {
        Camera cam = this.camFac.getCamera();

        //Saving previous settings
        CameraProp iso = cam.getISO();
        CameraProp aperture = cam.getAperture();

        //Setting new settings
        cam.setISO(set.getIso());
        cam.setAperture(set.getAperture());

        if(set.getExposureTime() == 0.0) {
            //Saving previous settings
            CameraProp exposure = cam.getExposure();
            //Setting new settings
            cam.setExposure(set.getExposure());
            for (int i = 0; i < set.getCount(); i++) {
                cam.takePicture();
            }
            //Restoring settings
            cam.setExposure(exposure);
        }
        else{
            for (int i = 0; i < set.getCount(); i++) {
                cam.takePicture(set.getExposureTime());
            }
        }

        //Restoring settings
        cam.setISO(iso);
        cam.setAperture(aperture);

        //Pause after shooting
        try {
            TimeUnit.MILLISECONDS.sleep((long) (set.getPause() * 1000));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
