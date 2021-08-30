package camera_api.canon.encodings.photosessionparams;

import camera_api.canon.encodings.cameraprops.EdsAperture;
import camera_api.canon.encodings.cameraprops.EdsExposure;
import camera_api.canon.encodings.cameraprops.EdsISO;
import webapp.model.photosessionparams.abstracts.AbstractPhotoSessionParams;


public class CanonPhotoSessionParams extends AbstractPhotoSessionParams {

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
