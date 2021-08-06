package camera_api.interfaces;

public interface CameraSDK {
    ErrorCode initializeSDK();
    ErrorCode terminateSDK();
    int getDeviceCount();
    Camera getCamera(int index);
    String getCameraName(int index);
    String[] getCameraNameList();
    String getCameraPortInfo(int index);
}