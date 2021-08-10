package camera_api.canon;/*
* TODO:
*  Exposure Compensation (set, getPoss)
*  EdsTime
*  EdsFocusInfo
*  ImageQuality
*  ColorTemperature
*  kEdsPropID_DC_Zoom
*  WB bracketing
*  WhiteBalanceShift
*  PictureStyleDesc
*  EdsChar[] setters.
*  Test on Battery Quality
*  kEdsPropID_Evf_OutputDevice
*  Power Zoom functionality
*  LiveView functionality
*
* */


import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.Camera;
import camera_api.interfaces.CameraProp;
import camera_api.canon.encodings.cameraprops.*;
import camera_api.canon.encodings.sdk.*;


public class CanonCamera implements Camera {

/*------------------------CAMERA REFERENCE HANDLERS---------------------------*/
    private long CamRef = 0;

    private void setCamRef(long ref){
        this.CamRef = ref;
    }
    private long getCamRef(){
        return this.CamRef;
    }
/*----------------------------------------------------------------------------*/
/*-----------------------CONSTRUCTORS AND FINALIZERS--------------------------*/
    /**
     * Indicates if the Camera session is open.
     */
    private final boolean isSessionOpen = false;

    /**
     * Opens camera session
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError openSession();

    /**
     * Closes camera session.
     * Must be done at the end of app session.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError closeSession();

    /**
     * Gets camera reference at index @index
     * from device list, and sets and calls
     * setCamRef() method to save it.
     * @param index Device list index
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    private native EdsError setCamRefFromList(int index);

    /**
     * Releases Camera Reference from memory.
     */
    private native void releaseCamRef();

    /**
     * Constructor to get specific camera from device list.
     * Gets camera at @index position in device list
     * DOES NOT OPEN THE CAMERA SESSION
     */
    private CanonCamera(int index){
        setCamRefFromList(index);
    }

    /**
     * Static method that can be called only by camera_api.canon.CanonSDK class
     * to construct Camera class objects.
     * @param permission Key that camera_api.canon.CanonSDK use to construct Camera class objects
     * @param index Index of Camera in Device List
     * @return Constructed Camera class object
     */
    public static CanonCamera createCamera(CanonSDK.EDSDKPermit permission, int index){
        if(permission != null){
            return new CanonCamera(index);
        }
        return null;
    }

    /**
     * Default finalizer.
     * Must be called at the end of the program and called
     * ONLY by camera_api.canon.CanonSDK static methods.
     */
    protected void release(CanonSDK.EDSDKPermit permission){
        if(permission != null) {
            this.releaseCamRef();
            this.setCamRef(0);
        }
    }
/*----------------------------------------------------------------------------*/


/*------------------------------MAIN COMMANDS---------------------------------*/

    /* INFO COMMANDS */
    /**
     * Gets product name
     * Can be called even if camera session
     * is not open
     * @return Product name
     */
    public native String productName();

    /**
     * Gets product serial number
     * @return Product serial number
     */
    public native String bodyIDEx();

    /**
     * Sets a string identifying the owner as registered on the camera.
     * Up to 64 characters.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setOwnerName(String name);

    /**
     * Gets a string identifying the owner as registered on the camera
     * @return Owners name
     */
    public native String getOwnerName();

    /**
     * Sets a string identifying the copyright information as registered on the camera.
     * This property indicates the owner name for the remote camera.
     * Up to 64 characters.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setCopyright(String name);

    /**
     * Gets a string identifying the copyright information as registered on the camera.
     * This property indicates the owner name for the remote camera.
     * @return Copyright information as registered on the camera
     */
    public native String getCopyright();

    /**
     * Gets s a string s the camera's firmware version.
     * @return Firmware version
     */
    public native String firmwareVersion();

    /**
     * Gets the current storage media for the camera.
     * @return Current storage media for the camera.
     */
    public native String currentStorage();

    /**
     * Gets the current folder for the camera.
     * @return Current folder for the camera.
     */
    public native String currentFolder();

    /**
     * Gets lens name
     * @return Lens name
     */
    public native String lensName();

    /**
     * Gets battery level from camera
     * Return values:
     * -1           Error occurred
     * 0-100        Battery level(%)
     * 0xffffffff   AC power
     * @return Battery level in %
     */
    public native int batteryLevel();

    /**
     * Gets battery quality from camera
     * If camera does not support it -
     * returns null
     * @return Battery quality code
     */
    public native EdsBatteryQuality batteryQuality() throws NoSuchPropertyValueException;

    /**
     * Returns the number of shots available on a camera.
     * Cameras return the number of shots left on the camera based
     * on the available disk capacity of the host computer
     * they are connected to.
     * If error occurred - returns -1.
     * @return The number of shots available on a camera.
     */
    public native int availableShots();

    /* PICTURE TAKING COMMANDS*/
    /**
     * For two seconds tries to auto-adjust focus.
     * @return Error code
     */
    public native EdsError autoFocus();
    /**
     * Takes picture with
     * current settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError takePicture();

    /**
     * If BULB mode was not set,
     * sets BULB mode for one shot
     * with exposure @expTime
     * and sets back old settings
     * @param expTime exposure time
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError takePicture(double expTime);

    /* INTERFACE COMMANDS */
    /**
     * Locks UI of camera if
     * parameter was given as
     * TRUE. FALSE unlocks UI.
     * @param lock If TRUE - locks UI, FALSE - unlocks
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError cameraUILock(boolean lock);

    /**
     * Sets live view settings status.
     * @param lvStatus Live view status
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setLVSettingsStatus(EdsLiveViewSettings lvStatus);

    /**
     * Gets live view settings status.
     * @return Live view status code
     */
    public native EdsLiveViewSettings getLVSettingsStatus() throws NoSuchPropertyValueException;

    /* SETTINGS CONTROL COMMANDS */

    /* EXPOSURE */
    /**
     * Sets Exposure settings
     * @param exposure Exposure settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setExposure(CameraProp exposure);

    /**
     * Gets exposure settings
     * @return Exposure settings code
     */
    public native EdsExposure getExposure() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible exposure settings
     * @return A list of possible exposure settings
     */
    public native EdsExposure[] getPossibleExposure() throws NoSuchPropertyValueException;

    /* ISO */
    /**
     * Sets ISO settings
     * @param iso ISO settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setISO(CameraProp iso);

    /**
     * Gets ISO settings
     * @return ISO settings code
     */
    public native EdsISO getISO() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible ISO settings
     * @return A list of possible ISO settings
     */
    public native EdsISO[] getPossibleISO() throws NoSuchPropertyValueException;

    /* FOCUS SETTINGS */
    /**
     * Sets AutoFocus settings
     * @param focus AutoFocus settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setFocusSettings(CameraProp focus);

    /**
     * Gets AF settings
     * @return AF settings code
     */
    public native EdsFocusSettings getFocusSettings() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible AF settings
     * @return A list of possible AF settings
     */
    public native EdsFocusSettings[] getPossibleFocusSettings() throws NoSuchPropertyValueException;

    /* APERTURE */
    /**
     * Sets aperture settings
     * @param aperture Aperture settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setAperture(CameraProp aperture);

    /**
     * Gets aperture settings
     * @return Aperture settings code
     */
    public native EdsAperture getAperture() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible aperture settings
     * @return A list of possible aperture settings
     */
    public native EdsAperture[] getPossibleAperture() throws NoSuchPropertyValueException;

    /*  DESTINATION OF IMAGES AFTER SHOOTING */
    /**
     * Sets the destination of images after shooting
     * @param destination The destination of images after shooting
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setSaveTo(EdsSaveTo destination);

    /**
     * Gets the destination of images after shooting
     * @return The destination code of images after shooting
     */
    public native EdsSaveTo getSaveTo() throws NoSuchPropertyValueException;

    /* DRIVE MODE */
    /**
     * Sets drive mode settings
     * @param driveMode Drive mode settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setDriveMode(CameraProp driveMode);

    /**
     * Gets drive mode settings
     * @return Drive mode settings code
     */
    public native EdsDriveMode getDriveMode() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible drive mode settings
     * @return A list of possible drive mode settings
     */
    public native EdsDriveMode[] getPossibleDriveMode() throws NoSuchPropertyValueException;

    /* METERING MODE */
    /**
     * Sets metering mode settings
     * @param meteringMode Metering mode settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setMeteringMode(EdsMeteringMode meteringMode);

    /**
     * Gets metering mode settings
     * @return Metering mode settings code
     */
    public native EdsMeteringMode getMeteringMode() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible metering mode settings
     * @return A list of possible metering mode settings
     */
    public native EdsMeteringMode[] getPossibleMeteringMode() throws NoSuchPropertyValueException;

    /* BRACKET TYPE */
    /**
     * Gets the current bracket type
     * @return Current bracket type code
     */
    public native EdsBracket getBracketType() throws NoSuchPropertyValueException;

    /* WHITE BALANCE */
    /**
     * Sets white balance settings
     * @param whiteBalance White balance settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setWhiteBalance(CameraProp whiteBalance);

    /**
     * Gets white balance settings
     * @return White balance settings code
     */
    public native EdsWhiteBalance getWhiteBalance() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible white balance settings
     * @return A list of possible white balance settings
     */
    public native EdsWhiteBalance[] getPossibleWhiteBalance() throws NoSuchPropertyValueException;

    /* COLOR SPACE */
    /**
     * Sets color space settings
     * @param colorSpace Color space settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setColorSpace(CameraProp colorSpace);

    /**
     * Gets color space settings
     * @return Color space settings code
     */
    public native EdsColorSpace getColorSpace() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible color space settings
     * @return A list of possible color space settings
     */
    public native EdsColorSpace[] getPossibleColorSpace() throws NoSuchPropertyValueException;

    /* PICTURE STYLE */
    /**
     * Sets picture style settings.
     * This property is valid only for models supporting picture styles.
     * @param pictureStyle Picture style settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setPictureStyle(EdsPictureStyle pictureStyle);

    /**
     * Gets picture style settings.
     * This property is valid only for models supporting picture styles.
     * @return Picture style settings code
     */
    public native EdsPictureStyle getPictureStyle() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible picture style settings.
     * This property is valid only for models supporting picture styles.
     * @return A list of possible picture style settings
     */
    public native EdsPictureStyle[] getPossiblePictureStyle() throws NoSuchPropertyValueException;

    /* LENS STATUS */
    /**
     * Gets lens status (attached or not).
     * @return Lens status code
     */
    public native EdsLensStatus getLensStatus() throws NoSuchPropertyValueException;

    /* AE MODE SELECT */
    /**
     * Sets settings values of the camera in shooting mode.
     * You cannot set this property on cameras with a mode dial.
     * @param aeModeSel Settings values
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setAEModeSelect(EdsAEModeSelect aeModeSel);

    /**
     * Gets settings values of the camera in shooting mode.
     * @return Settings values of the camera in shooting mode.
     */
    public native EdsAEModeSelect getAEModeSelect() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible settings values of the camera in shooting mode.
     * @return A list of possible settings values of the camera in shooting mode.
     */
    public native EdsAEModeSelect[] getPossibleAEModeSelect() throws NoSuchPropertyValueException;

    /* EXPOSURE COMPENSATION */
    /**
     * Sets the exposure compensation.
     * @param exposureComp Exposure compensation.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setExposureComp(CameraProp exposureComp);

    /**
     * Gets the exposure compensation.
     * @return Exposure compensation.
     */
    public native EdsExposureComp getExposureComp() throws NoSuchPropertyValueException;

    /**
     * Gets a list of possible exposure compensation.
     * @return A list of possible exposure compensation.
     */
    public native EdsExposureComp[] getPossibleExposureComp() throws NoSuchPropertyValueException;

    /* AE MODE */
    /**
     * Gets settings values of the camera in shooting mode.
     * @return AE mode code
     */
    public native EdsAEMode getAEMode() throws NoSuchPropertyValueException;

    /* SUMMER TIME  */
    /**
     * Sets the camera's daylight savings time setting.
     * This property is only supported for models with the following menus:
     * [ Date / Time / Zone ]
     * @param sumTime Summer time setting.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    public native EdsError setSummerTimeSetting(EdsSummerTimeSetting sumTime);

    /**
     * Gets the camera's daylight savings time setting.
     * This property is only supported for models with the following menus:
     * [ Date / Time / Zone ]
     * @return Summer time setting.
     */
    public native EdsSummerTimeSetting getSummerTimeSetting() throws NoSuchPropertyValueException;


/*----------------------------------------------------------------------------*/
}
