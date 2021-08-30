package webapp.model.photosessionparams.abstracts;

import camera_api.interfaces.camerasdk.Camera;
import camera_api.interfaces.encogings.CameraProp;
import lombok.Getter;
import lombok.Setter;
import webapp.model.photosessionparams.inter.PhotoSessionParams;

public abstract class AbstractPhotoSessionParams implements PhotoSessionParams {
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private int count;

    @Getter
    protected CameraProp exposure;

    @Getter
    @Setter
    private double exposureTime;

    @Getter
    protected CameraProp iso;

    @Getter
    protected CameraProp aperture;

    @Getter
    @Setter
    private String pictureName;

    @Getter
    @Setter
    private double pause;

    @Override
    public void applySettings(Camera camera) {
        camera.setISO(this.iso);
        camera.setAperture(this.aperture);
        if (!exposureTimeGiven()) {
            camera.setExposure(this.exposure);
        }
    }

    @Override
    public boolean exposureTimeGiven() {
        return this.exposure == null;
    }
}
