package cameraweb.modelDAO;

import camera_api.ProxyCamera;
import camera_api.interfaces.camerasdk.Camera;
import cameraweb.model.pictureset.inter.PictureSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class PhotoSessionCAO {
    private final ProxyCamera camFac;

    @Autowired
    public PhotoSessionCAO(ProxyCamera camFac) {
        this.camFac = camFac;
    }

    public void startSession(List<PictureSet> sets) {
        for (PictureSet set : sets) {
            processPictureSet(set);
        }
    }

    private void processPictureSet(PictureSet set) {
        Camera cam = this.camFac.getCamera();
        //Setting new settings
        set.applySettings(cam);

        if(set.exposureTimeGiven()) {
            for (int i = 0; i < set.getCount(); i++) {
                cam.takePicture(set.getExposureTime());
            }
        }
        else{
            for (int i = 0; i < set.getCount(); i++) {
                cam.takePicture();
            }
        }

        //Pause after shooting
        try {
            TimeUnit.MILLISECONDS.sleep((long) (set.getPause() * 1000));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
