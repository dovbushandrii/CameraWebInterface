package webapp.modelDAO;

import camera_api.CameraLoader;
import camera_api.interfaces.camerasdk.Camera;
import webapp.model.photosessionparams.inter.PhotoSessionParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class PhotoSessionCAO {
    private final CameraLoader camera;

    @Autowired
    public PhotoSessionCAO(CameraLoader camera) {
        this.camera = camera;
    }

    public void startSession(List<PhotoSessionParams> params) {
        for (PhotoSessionParams param : params) {
            processPhotoSession(param);
        }
    }

    private void processPhotoSession(PhotoSessionParams params) {
        applySettingsToCamera(params);

        if (params.exposureTimeGiven()) {
            takePictures(params.getCount(), params.getExposureTime());
        } else {
            takePictures(params.getCount());
        }

        //Pause after shooting
        this.pause(params.getPause());
    }

    private void applySettingsToCamera(PhotoSessionParams params) {
        Camera cam = camera.getCamera();
        //Setting new settings
        params.applySettings(cam);
    }

    private void takePictures(int count, double exposureTime) {
        Camera cam = camera.getCamera();
        for (int i = 0; i < count; i++) {
            cam.takePicture(exposureTime);
        }
    }

    private void takePictures(int count) {
        Camera cam = camera.getCamera();
        for (int i = 0; i < count; i++) {
            cam.takePicture();
        }
    }

    private void pause(double seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
