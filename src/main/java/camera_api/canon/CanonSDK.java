package camera_api.canon;

import camera_api.canon.encodings.sdk.*;


/*
 *   TODO: Commenting
 */
public class CanonSDK {

    /**
     *
     */
    public static class EDSDKPermit { private EDSDKPermit(){}}

    /**
     *
     */
    private static EDSDKPermit permission = new EDSDKPermit();

    /**
     *
     */
    private static CanonCamera[] deviceList = null;

/*----------------------------------------------------------------------------*/

    /**
     *
     * @return
     */
    private static native int getCameraListSize();

    /**
     *
     * @throws Throwable
     */
    private static void setCameraList() throws Throwable{
        int size = getCameraListSize();
        deviceList = new CanonCamera[size];
        for(int i = 0; i < size; i++) {
            deviceList[i] = CanonCamera.createCamera(permission, i);
        }
    }

    /**
     *
     * @throws Throwable
     */
    private static void releaseCameraList(){
        for(int i = 0; i < deviceList.length; i++){
            deviceList[i].closeSession();
            deviceList[i].finalize(permission);
        }
        deviceList = null;
    }
/*----------------------------------------------------------------------------*/
    /**
     *
     */
    private static boolean isInitialized = false;

    /**
     * Initializes Canon EOS SDK.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    private static native EdsError initializeNativeSDK();

    /**
     * Terminates Canon EOS SDK.
     * MUST BE called at the end.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    private static native EdsError terminateNativeSDK();

    /**
     *
     * @throws Throwable
     */
    public static EdsError initializeSDK() throws Throwable{
        EdsError err = CanonSDK.initializeNativeSDK();
        setCameraList();
        return err;
    }

    /**
     *
     * @throws Throwable
     */
    public static EdsError terminateSDK(){
        EdsError err = terminateNativeSDK();
        releaseCameraList();
        return err;
    }
/*----------------------------------------------------------------------------*/

    /**
     *
     * @param index
     * @return
     */
    public static CanonCamera getCamera(int index){
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
    public static String getDeviceName(int index){
        return deviceList[index].productName();
    }

    public static String[] getDeviceNameList(){
        String dnl[] = new String[deviceList.length];
        for(int i = 0 ; i < dnl.length; i++){
            dnl[i] = getDeviceName(i);
        }
        return dnl;
    }

    /**
     *
     * @param index
     * @return
     */
    public static native String getDevicePortInfo(int index);
}