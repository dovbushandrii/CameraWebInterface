package cameraweb.pictureset.canonEncodings;

import camera_api.canon.encodings.cameraprops.EdsAperture;
import camera_api.canon.encodings.cameraprops.EdsExposure;
import camera_api.canon.encodings.cameraprops.EdsISO;
import camera_api.interfaces.CameraProp;
import cameraweb.pictureset.inter.PictureSet;

public class CanonPictureSet implements PictureSet {

    private int count;
    private EdsExposure exposure;
    private double exposureTime;
    private EdsISO iso;
    private EdsAperture aperture;
    private String pictureName;
    private double pause;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public CameraProp getExposure() {
        return this.exposure;
    }

    @Override
    public void setExposure(CameraProp exposureCode) {
        this.exposure = EdsExposure.fromCode(exposureCode.getCode());
    }

    @Override
    public double getExposureTime() {
        return this.exposureTime;
    }

    @Override
    public void setExposureTime(double exposureTime) {
        this.exposureTime = exposureTime;
    }

    @Override
    public CameraProp getIso() {
        return this.iso;
    }

    @Override
    public void setIso(CameraProp isoCode) {
        this.iso = EdsISO.fromCode(isoCode.getCode());
    }

    @Override
    public CameraProp getAperture() {
        return this.aperture;
    }

    @Override
    public void setAperture(CameraProp apertureCode) {
        this.aperture = EdsAperture.fromCode(apertureCode.getCode());
    }

    @Override
    public String getPictureName() {
        return this.pictureName;
    }

    @Override
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public double getPause() {
        return this.pause;
    }

    @Override
    public void setPause(double pause) {
        this.pause = pause;
    }
}
