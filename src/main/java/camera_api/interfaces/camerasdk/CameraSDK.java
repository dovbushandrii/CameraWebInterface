package camera_api.interfaces.camerasdk;

import camera_api.interfaces.encogings.ErrorCode;

public interface CameraSDK {
    ErrorCode initializeSDK();

    ErrorCode terminateSDK();

    void updateCameraList();

    int getDeviceCount();

    Camera getCamera(int index) throws IndexOutOfBoundsException;

    String getCameraName(int index) throws IndexOutOfBoundsException;

    String[] getCameraNameList();

    String getCameraPort(int index) throws IndexOutOfBoundsException;
}
