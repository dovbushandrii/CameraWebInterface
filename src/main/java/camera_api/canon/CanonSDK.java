package camera_api.canon;

import camera_api.interfaces.CameraSDK;
import camera_api.canon.encodings.sdk.*;


/*
 *   TODO: Commenting
 */
public class CanonSDK implements CameraSDK {

    /**
     *
     */
    public static class EDSDKPermit { private EDSDKPermit(){}}

    /**
     *
     */
    private static final EDSDKPermit permission = new EDSDKPermit();

    /**
     *
     */
    private static CanonCamera[] deviceList = new CanonCamera[0];

/*----------------------------------------------------------------------------*/

    /**
     *
     * @return
     */
    private static native int getCameraListSize();

    /**
     *
     */
    private static EdsError setCameraList(){
        int size = getCameraListSize();
        deviceList = new CanonCamera[size];
        for(int i = 0; i < size; i++) {
            CanonCamera cam = CanonCamera.createCamera(permission, i);
            if(cam == null) {
                return EdsError.EDS_ERR_DEVICE_NOT_FOUND;
            }
            else{
                deviceList[i] = cam;

            }
        }
        return EdsError.EDS_ERR_OK;
    }

    /**
     *
     */
    private static EdsError releaseCameraList(){
        EdsError err = EdsError.EDS_ERR_OK;
        for (CanonCamera canonCamera : deviceList) {
            err = canonCamera.closeSession();
            if (err == EdsError.EDS_ERR_OK) {
                canonCamera.release(permission);
            } else {
                return err;
            }
        }
        deviceList = null;
        return err;
    }
/*----------------------------------------------------------------------------*/
    /**
     *
     */
    private static final boolean isInitialized = false;

    /**
     * Initializes Canon EOS SDK.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    private native EdsError initializeNativeSDK();

    /**
     * Terminates Canon EOS SDK.
     * MUST BE called at the end.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    private native EdsError terminateNativeSDK();

    /**
     *
     */
    public EdsError initializeSDK(){
        EdsError err = initializeNativeSDK();
        if(err == EdsError.EDS_ERR_OK) {
            err = setCameraList();
        }
        return err;
    }

    /**
     *
     */
    public EdsError terminateSDK(){
        EdsError err = releaseCameraList();
        if(err == EdsError.EDS_ERR_OK) {
            err = terminateNativeSDK();
        }
        return err;
    }
/*----------------------------------------------------------------------------*/

    public int getDeviceCount(){
        return deviceList.length;
    }

    /**
     *
     * @param index
     * @return
     */
    public CanonCamera getCamera(int index){
        if(deviceList.length > index && index >= 0){
            return deviceList[index];
        }
        return null;
    }

    /**
     *
     * @param index
     * @return
     */
    public String getCameraName(int index){
        return deviceList[index].productName();
    }

    /**
     *
     * @return
     */
    public String[] getCameraNameList(){
        String dnl[] = new String[deviceList.length];
        for(int i = 0 ; i < dnl.length; i++){
            dnl[i] = getCameraName(i);
        }
        return dnl;
    }

    /**
     *
     * @param index
     * @return
     */
    public native String getCameraPortInfo(int index);
}