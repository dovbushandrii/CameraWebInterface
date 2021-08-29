package cameraweb.model.pictureset.canonEncodings;

import camera_api.canon.encodings.cameraprops.EdsAperture;
import camera_api.canon.encodings.cameraprops.EdsExposure;
import camera_api.canon.encodings.cameraprops.EdsISO;
import camera_api.interfaces.Camera;
import cameraweb.model.pictureset.inter.PictureSet;
import lombok.Getter;
import lombok.Setter;

public class CanonPictureSet implements PictureSet {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private int count;

    @Getter
    private EdsExposure exposure;

    @Getter
    @Setter
    private double exposureTime;

    @Getter
    private EdsISO iso;

    @Getter
    private EdsAperture aperture;

    @Getter
    @Setter
    private String pictureName;

    @Getter
    @Setter
    private double pause;

    @Override
    public void applySettings(Camera cam) {
        cam.setISO(this.iso);
        cam.setAperture(this.aperture);
        if(!exposureTimeGiven()){
            cam.setExposure(this.exposure);
        }
    }

    @Override
    public boolean exposureTimeGiven() {
        return this.exposure == null;
    }

    @Override
    public void setExposure(int exposureCode) {
        this.exposure = EdsExposure.fromCode(exposureCode);
    }

    @Override
    public void setExposure(String exposureValue) {
        this.exposure = EdsExposure.fromValue(exposureValue);
    }

    @Override
    public void setIso(int isoCode) {
        this.iso = EdsISO.fromCode(isoCode);
    }

    @Override
    public void setIso(String isoValue) {
        this.iso = EdsISO.fromValue(isoValue);
    }

    @Override
    public void setAperture(int apertureCode) {
        this.aperture = EdsAperture.fromCode(apertureCode);
    }

    @Override
    public void setAperture(String apertureValue) {
        this.aperture = EdsAperture.fromValue(apertureValue);
    }

}
