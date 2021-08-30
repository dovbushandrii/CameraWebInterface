package cameraweb.model.photosessionparams.inter;

import camera_api.interfaces.camerasdk.Camera;
import camera_api.interfaces.encogings.CameraProp;

public interface PhotoSessionParams {

    long getId();

    void setId(long id);

    int getCount();

    void setCount(int count);

    CameraProp getExposure();

    void setExposure(int exposureCode);

    void setExposure(String exposureValue);

    double getExposureTime();

    void setExposureTime(double exposureTime);

    CameraProp getIso();

    void setIso(int isoCode);

    void setIso(String isoValue);

    CameraProp getAperture();

    void setAperture(int apertureCode);

    void setAperture(String apertureCode);

    String getPictureName();

    void setPictureName(String pictureName);

    double getPause();

    void setPause(double pause);

    void applySettings(Camera cam);

    boolean exposureTimeGiven();
}
