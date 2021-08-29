package camera_api.interfaces.companies;

import camera_api.interfaces.camerasdk.CameraSDK;

public interface Company {

    String getCompanyName();

    CameraSDK getCameraSDK();

    PictureSetFactory getPictureSetFactory();
}
