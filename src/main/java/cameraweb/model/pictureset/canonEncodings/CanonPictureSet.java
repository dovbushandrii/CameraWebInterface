package cameraweb.model.pictureset.canonEncodings;

import camera_api.canon.encodings.cameraprops.EdsAperture;
import camera_api.canon.encodings.cameraprops.EdsExposure;
import camera_api.canon.encodings.cameraprops.EdsISO;
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
    public void setExposure(int exposureCode) {
        this.exposure = EdsExposure.fromCode(exposureCode);
    }

    @Override
    public void setIso(int isoCode) {
        this.iso = EdsISO.fromCode(isoCode);
    }


    @Override
    public void setAperture(int apertureCode) {
        this.aperture = EdsAperture.fromCode(apertureCode);
    }

}
