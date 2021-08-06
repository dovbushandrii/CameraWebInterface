package camera_api;

public abstract class CameraSDK {
    public abstract ErrorCode initializeSDK();
    public abstract ErrorCode terminateSDK();
    public abstract int getDeviceCount();
    public abstract Camera getCamera(int index);
    public abstract String getCameraName(int index);
    public abstract String[] getCameraNameList();
    public abstract String getCameraPortInfo(int index);
}
