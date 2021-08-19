package cameraweb.pictureset.inter;

import camera_api.interfaces.CameraProp;

public interface PictureSet {
    int getCount();
    void setCount(int count);

    CameraProp getExposure();
    void setExposure(CameraProp exposureCode);

    double getExposureTime();
    void setExposureTime(double exposureTime);

    CameraProp getIso();
    void setIso(CameraProp isoCode);

    CameraProp getAperture();
    void setAperture(CameraProp apertureCode);

    String getPictureName();
    void setPictureName(String pictureName);

    double getPause();
    void setPause(double pause);
}
