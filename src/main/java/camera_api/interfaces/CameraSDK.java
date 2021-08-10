package camera_api.interfaces;

public interface CameraSDK {
    ErrorCode initializeSDK();
    ErrorCode terminateSDK();
    int getDeviceCount();
    Camera getCamera(int index) throws IndexOutOfBoundsException;
    String getCameraName(int index) throws IndexOutOfBoundsException;
    String[] getCameraNameList();
    String getCameraPort(int index) throws IndexOutOfBoundsException;
}
