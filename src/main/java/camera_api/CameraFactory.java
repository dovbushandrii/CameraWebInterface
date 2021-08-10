package camera_api;

import camera_api.exceptions.SDKIsNotInitializedException;
import camera_api.interfaces.Camera;
import camera_api.interfaces.CameraSDK;
import camera_api.interfaces.ErrorCode;

public class CameraFactory {

    private final CameraSDK sdk;

    private static boolean isInit = false;

    public CameraFactory(CameraSDK sdk){
        this.sdk = sdk;
    }

    public ErrorCode initialize(){
        isInit = true;
        return this.sdk.initializeSDK();
    }

    public ErrorCode terminate(){
        isInit = false;
        return this.sdk.terminateSDK();
    }

    public int getDeviceCount() throws SDKIsNotInitializedException{
        if(isInit){
            return this.sdk.getDeviceCount();
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }

    public Camera getCamera(int index) throws IndexOutOfBoundsException, SDKIsNotInitializedException {
        if(isInit){
            return this.sdk.getCamera(index);
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }

    public String getCameraName(int index) throws IndexOutOfBoundsException, SDKIsNotInitializedException{
        if(isInit){
            return this.sdk.getCameraName(index);
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }

    public String[] getCameraNameList() throws SDKIsNotInitializedException{
        if(isInit){
            return this.sdk.getCameraNameList();
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }

    public String getCameraPort(int index) throws SDKIsNotInitializedException{
        if(isInit){
            return this.sdk.getCameraPort(index);
        }
        throw new SDKIsNotInitializedException("Camera SDK is not initialized");
    }
}
