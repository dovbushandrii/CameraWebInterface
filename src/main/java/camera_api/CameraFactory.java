package camera_api;

import camera_api.interfaces.Camera;
import camera_api.interfaces.CameraSDK;
import camera_api.interfaces.ErrorCode;

public class CameraFactory {

    private CameraSDK sdk;

    private static boolean isInit = false;

    public CameraFactory(CameraSDK sdk){
        this.sdk = sdk;
    }

    public ErrorCode initialize(){
        this.isInit = true;
        return this.sdk.initializeSDK();
    }

    public ErrorCode terminate(){
        this.isInit = false;
        return this.sdk.terminateSDK();
    }

    public int getDeviceCount(){
        if(this.isInit){
            return this.sdk.getDeviceCount();
        }
        return 0;
    }

    public Camera getCamera(int index){
        if(this.isInit){
            return this.sdk.getCamera(0);
        }
        return null;
    }

    public String getCameraName(int index){
        if(this.isInit){
            return this.sdk.getCameraName(index);
        }
        return null;
    }

    public String[] getCameraNameList(){
        if(this.isInit){
            return this.sdk.getCameraNameList();
        }
        return null;
    }

    public String getCameraPortInfo(int index){
        if(this.isInit){
            return this.sdk.getCameraPortInfo(index);
        }
        return null;
    }
}
