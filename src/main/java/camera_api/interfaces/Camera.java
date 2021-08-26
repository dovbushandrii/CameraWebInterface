package camera_api.interfaces;

// TODO: 8/5/2021 COMMENTING

import camera_api.exceptions.NoSuchPropertyValueException;


public interface Camera {
    /**
     * Opens camera session
     *
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode openSession();

    /**
     * Closes camera session.
     * Must be done at the end of app session.
     *
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode closeSession();
    /*----------------------------------------------------------------------------*/


    /*------------------------------MAIN COMMANDS---------------------------------*/

    /* INFO COMMANDS */

    /**
     * Gets product name
     * Can be called even if camera session
     * is not open
     *
     * @return Product name
     */
    String productName();

    /**
     * Gets product serial number
     *
     * @return Product serial number
     */
    String bodyIDEx();

    /**
     * Sets a string identifying the owner as registered on the camera.
     * Up to 64 characters.
     *
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setOwnerName(String name);

    /**
     * Gets a string identifying the owner as registered on the camera
     *
     * @return Owners name
     */
    String getOwnerName();

    /**
     * Gets s a string s the camera's firmware version.
     *
     * @return Firmware version
     */
    String firmwareVersion();

    /**
     * Gets the current storage media for the camera.
     *
     * @return Current storage media for the camera.
     */
    String currentStorage();

    /**
     * Gets the current folder for the camera.
     *
     * @return Current folder for the camera.
     */
    String currentFolder();

    /**
     * Gets lens name
     *
     * @return Lens name
     */
    String lensName();

    /**
     * Gets battery level from camera
     * Return values:
     * -1           Error occurred
     * 0-100        Battery level(%)
     * 0xffffffff   AC power
     *
     * @return Battery level in %
     */
    int batteryLevel();

    /**
     * Returns the number of shots available on a camera.
     * Cameras return the number of shots left on the camera based
     * on the available disk capacity of the host computer
     * they are connected to.
     * If error occurred - returns -1.
     *
     * @return The number of shots available on a camera.
     */
    int availableShots();

    /* PICTURE TAKING COMMANDS*/

    /**
     * For two seconds tries to auto-adjust focus.
     *
     * @return Error code
     */
    ErrorCode autoFocus();

    /**
     * Takes picture with
     * current settings
     *
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode takePicture();

    /**
     * If BULB mode was not set,
     * sets BULB mode for one shot
     * with exposure @expTime
     * and sets back old settings
     *
     * @param expTime exposure time
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode takePicture(double expTime);

    /* INTERFACE COMMANDS */

    /**
     * Locks UI of camera if
     * parameter was given as
     * TRUE. FALSE unlocks UI.
     *
     * @param lock If TRUE - locks UI, FALSE - unlocks
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode cameraUILock(boolean lock);

    /* SETTINGS CONTROL COMMANDS */

    /* AE MODE */

    /**
     * Gets settings values of the camera in shooting mode.
     *
     * @return AE mode code
     */
    CameraProp getAEMode();

    /* EXPOSURE */

    /**
     * Sets Exposure settings
     *
     * @param exposure Exposure settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setExposure(CameraProp exposure);

    /**
     * Gets exposure settings
     *
     * @return Exposure settings code
     */
    CameraProp getExposure();

    /**
     * Gets a list of possible exposure settings
     *
     * @return A list of possible exposure settings
     */
    CameraProp[] getPossibleExposure();

    /* ISO */

    /**
     * Sets ISO settings
     *
     * @param iso ISO settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setISO(CameraProp iso);

    /**
     * Gets ISO settings
     *
     * @return ISO settings code
     */
    CameraProp getISO();

    /**
     * Gets a list of possible ISO settings
     *
     * @return A list of possible ISO settings
     */
    CameraProp[] getPossibleISO();

    /* FOCUS SETTINGS */

    /**
     * Sets AutoFocus settings
     *
     * @param focus AutoFocus settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setFocusSettings(CameraProp focus);

    /**
     * Gets AF settings
     *
     * @return AF settings code
     */
    CameraProp getFocusSettings();

    /**
     * Gets a list of possible AF settings
     *
     * @return A list of possible AF settings
     */
    CameraProp[] getPossibleFocusSettings();

    /* APERTURE */

    /**
     * Sets aperture settings
     *
     * @param aperture Aperture settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setAperture(CameraProp aperture);

    /**
     * Gets aperture settings
     *
     * @return Aperture settings code
     */
    CameraProp getAperture();

    /**
     * Gets a list of possible aperture settings
     *
     * @return A list of possible aperture settings
     */
    CameraProp[] getPossibleAperture();

    /* DRIVE MODE */

    /**
     * Sets drive mode settings
     *
     * @param driveMode Drive mode settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setDriveMode(CameraProp driveMode);

    /**
     * Gets drive mode settings
     *
     * @return Drive mode settings code
     */
    CameraProp getDriveMode();

    /**
     * Gets a list of possible drive mode settings
     *
     * @return A list of possible drive mode settings
     */
    CameraProp[] getPossibleDriveMode();

    /* WHITE BALANCE */

    /**
     * Sets white balance settings
     *
     * @param whiteBalance White balance settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setWhiteBalance(CameraProp whiteBalance);

    /**
     * Gets white balance settings
     *
     * @return White balance settings code
     */
    CameraProp getWhiteBalance();

    /**
     * Gets a list of possible white balance settings
     *
     * @return A list of possible white balance settings
     */
    CameraProp[] getPossibleWhiteBalance();

    /* COLOR SPACE */

    /**
     * Sets color space settings
     *
     * @param colorSpace Color space settings
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setColorSpace(CameraProp colorSpace);

    /**
     * Gets color space settings
     *
     * @return Color space settings code
     */
    CameraProp getColorSpace();

    /**
     * Gets a list of possible color space settings
     *
     * @return A list of possible color space settings
     */
    CameraProp[] getPossibleColorSpace();

    /* LENS STATUS */

    /**
     * Gets lens status (attached or not).
     *
     * @return Lens status code
     */
    CameraProp getLensStatus();

    /* EXPOSURE COMPENSATION */

    /**
     * Sets the exposure compensation.
     *
     * @param exposureComp Exposure compensation.
     * @return Error Code, if ok - returns EDS_ERR_OK
     */
    ErrorCode setExposureComp(CameraProp exposureComp);

    /**
     * Gets the exposure compensation.
     *
     * @return Exposure compensation.
     */
    CameraProp getExposureComp();

    /**
     * Gets a list of possible exposure compensation.
     *
     * @return A list of possible exposure compensation.
     */
    CameraProp[] getPossibleExposureComp();
    /*----------------------------------------------------------------------------*/
}
