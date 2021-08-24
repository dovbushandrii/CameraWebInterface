package cameraweb.pictureset.inter;

import camera_api.interfaces.CameraProp;

public interface PictureSet {

    long getId();

    void setId(long id);

    int getCount();

    void setCount(int count);

    CameraProp getExposure();

    void setExposure(int exposureCode);

    double getExposureTime();

    void setExposureTime(double exposureTime);

    CameraProp getIso();

    void setIso(int isoCode);

    CameraProp getAperture();

    void setAperture(int apertureCode);

    String getPictureName();

    void setPictureName(String pictureName);

    double getPause();

    void setPause(double pause);
}
