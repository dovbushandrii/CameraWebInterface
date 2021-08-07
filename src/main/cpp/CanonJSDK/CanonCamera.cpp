// Camera.cpp : Defines the exported functions for the DLL.
//

/*
* TODO:
* Module structure
* Eds_String property set
*/



#include "pch.h"
#include "framework.h"
#include <string>

#include "CanonCamera.h"
#include "EDSDK.h"
#include "EDSDKErrors.h"
#include "EDSDKTypes.h"
#include "delay.h"
#include "Encodings.h"


#define MAX_STRING_LENGTH 64
#define AF_SECONDS 2
#define CANON_LIB_PATH                      "camera_api/"
#define ENCODINGS_ENUM_PATH                 CANON_LIB_PATH"canon/encodings/"
#define CAM_PROP_ENUM_PATH                  ENCODINGS_ENUM_PATH"cameraprops/"
#define SDK_ENUM_PATH                       ENCODINGS_ENUM_PATH"sdk/"


#define EDS_ERROR_ENUM_NAME                 "EdsError"
#define EDS_BATTERY_QUALITY_ENUM_NAME       "EdsBatteryQuality"
#define EDS_LV_SET_ENUM_NAME                "EdsLiveViewSettings"
#define EDS_EXPOSURE_ENUM_NAME              "EdsExposure"
#define EDS_ISO_ENUM_NAME                   "EdsISO"
#define EDS_AF_SET_ENUM_NAME                "EdsFocusSettings"
#define EDS_AV_SET_ENUM_NAME                "EdsAperture"
#define EDS_SAVE_TO_ENUM_NAME               "EdsSaveTo"
#define EDS_DRIVE_MODE_ENUM_NAME            "EdsDriveMode"
#define EDS_METERING_MODE_ENUM_NAME         "EdsMeteringMode"
#define EDS_BRACKET_ENUM_NAME               "EdsBracket"
#define EDS_WB_ENUM_NAME                    "EdsWhiteBalance"
#define EDS_COLOR_SPACE_ENUM_NAME           "EdsColorSpace"
#define EDS_PICTURE_STYLE_ENUM_NAME         "EdsPictureStyle"
#define EDS_LENS_STATUS_ENUM_NAME           "EdsLensStatus"
#define EDS_AE_MODE_ENUM_NAME               "EdsAEMode"
#define EDS_AE_MODE_SELECT_ENUM_NAME        "EdsAEModeSelect"
#define EDS_EXPOSURE_COMP_ENUM_NAME         "EdsExposureComp"
#define EDS_SUMMER_TIME_ENUM_NAME           "EdsSummerTimeSetting"

/*----------------------------------------------------------------------------------------------*/
/**
* Stops program till camera
* is not busy
*
* @param camera Camera pointer
*/
void waitTillNotBusy(EdsCameraRef* camera) {
    EdsError err = EDS_ERR_DEVICE_BUSY;
    int exp;
    EdsGetPropertyData(*camera, kEdsPropID_Tv, 0, sizeof(EdsInt32), &exp);

    //Tries to set same settings till camera allows it
    while (err == EDS_ERR_DEVICE_BUSY) {
        err = EdsSetPropertyData(*camera, kEdsPropID_Tv, 0, sizeof(EdsInt32), &exp);
    }
}

/**
* Gets first camera from
* list of available cameras.
* @param camera Pointer on camera that will be returned
* @return Error Code, if ok - returns EDS_ERR_OK
*/
EdsError getCameraRefFromList(EdsCameraRef* camera, int index) {
    EdsError err = EDS_ERR_OK;
    EdsCameraListRef cameraList = NULL;
    EdsUInt32 count = 0;
    // Get camera list
    err = EdsGetCameraList(&cameraList);
    // Get number of cameras
    if (err == EDS_ERR_OK)
    {
        err = EdsGetChildCount(cameraList, &count);
        if (index >= count)
        {
            err = EDS_ERR_DEVICE_NOT_FOUND;
        }
    }
    // Get first camera retrieved
    if (err == EDS_ERR_OK)
    {
        err = EdsGetChildAtIndex(cameraList, index, camera);
    }
    // Release camera list
    if (cameraList != NULL)
    {
        EdsRelease(cameraList);
        cameraList = NULL;
    }
    return err;
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
jboolean isCameraSessionOpen(JNIEnv* env, jobject camObj) {
    jclass thizClass = env->GetObjectClass(camObj);
    jfieldID field = env->GetFieldID(thizClass, "isSessionOpen", "Z");
    jboolean isOpen = env->GetBooleanField(camObj, field);
    env->DeleteLocalRef(thizClass);
    return isOpen;
}

/**
* Gets camera pointer from
* Camera java class object
* @param env JNI environment pointer
* @param cameraClass Camera Java Object
* @return Camera pointer
*/
EdsCameraRef* getCameraRef(JNIEnv* env, jobject cameraClass) {
    EdsCameraRef* camera = new EdsCameraRef();

    //Gets Camera class from Camera object
    jclass thizClass = env->GetObjectClass(cameraClass);

    //Finds private getCamRef() method for getting camera reference(pointer)
    jmethodID getRef = env->GetMethodID(thizClass, "getCamRef", "()J");
    camera = (EdsCameraRef*)env->CallLongMethod(cameraClass, getRef);

    //Must delete Camera class reference
    env->DeleteLocalRef(thizClass);
    if ((jlong)camera) return camera;
    return nullptr;
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/**
* Constructs Java EdsError enum variable
* from error code
* @param env JNI environment pointer
* @param err Code of error
* @return Constructed Java EdsError object
*/
jobject edsErrorFromCode(JNIEnv* env, EdsError err) {

    //Finds class pointer
    jclass enumClass = env->FindClass(SDK_ENUM_PATH EDS_ERROR_ENUM_NAME);

    std::string signature = SDK_ENUM_PATH;
    signature = "(I)L" + signature + EDS_ERROR_ENUM_NAME + ";";

    //Gets public static enum method to construct
    //enum object using code of error
    jmethodID enumConstruct = env->GetStaticMethodID(enumClass, "fromCode", signature.c_str());
    jobject EdsERROR = env->CallStaticObjectMethod(enumClass, enumConstruct, (jint)err);

    //Must delete EdsError class reference
    env->DeleteLocalRef(enumClass);
    return EdsERROR;
}

/**
* Constructs Java enum on specific
* camera property. For instance:
* EdsExposure on EDSDK's kEdsPropID_Tv.
*
* Example:
* return enumFromCode(env, "EdsExposure", propCode);
* return enumFromCode(env, "org/src/EdsExposure", propCode);
*
* @param env JNI environment pointer
* @param name Full name on Java enum class
* @param code Code on camera property.
* @return Constructed Java enum class
*/
jobject enumFromCode(JNIEnv* env, std::string name, int code) {
    //Constructing signature of fromCode() enum method
    std::string signature = "(I)L" + name + ";";

    //Finds class pointer on class
    //with name @name
    jclass enumClass = env->FindClass(name.c_str());

    //Gets public static enum method to construct
    //enum object using code of error
    jmethodID enumConstruct = env->GetStaticMethodID(enumClass, "fromCode", signature.c_str());
    jobject enumObj = env->CallStaticObjectMethod(enumClass, enumConstruct, code);

    //Must delete enum class reference
    env->DeleteLocalRef(enumClass);
    return enumObj;
}

/**
* Constructs Java enum list on specific
* possible camera property values. For instance:
* EdsExposure on EDSDK's kEdsPropID_Tv.
*
*
* Example:
* return enumListFromCodeList(env, "EdsExposure", list);
* return enumListFromCodeList(env, "org/src/EdsExposure", list);
*
* @param env JNI environment pointer
* @param name Full name on Java enum class
* @param list Possible property value list
* @return Constructed list as Enum array
*/
jobjectArray enumListFromCodeList(JNIEnv* env, std::string name, EdsPropertyDesc list) {
    //Constructing signature of fromCode() enum method
    std::string signature = "(I)L" + name + ";";

    //Finds class pointer on class
    //with name @name
    jclass enumClass = env->FindClass(name.c_str());

    //Gets public static enum method to construct
    //enum object using code of error
    jmethodID enumConstruct = env->GetStaticMethodID(enumClass, "fromCode", signature.c_str());

    //Java Object Array of enums objects 
    //with name @name initialization
    jobjectArray posEnums = env->NewObjectArray(list.numElements, enumClass, NULL);

    for (int i = 0; i < list.numElements; i++) {
        //Creating enum object from code @list.propDesc[i]
        jobject EdsENUM = env->CallStaticObjectMethod(enumClass, enumConstruct, list.propDesc[i]);

        //Setting previous enum object in
        //array @posEnums on position
        //with index @i
        env->SetObjectArrayElement(posEnums, i, EdsENUM);
    }

    //Must delete enum class reference
    env->DeleteLocalRef(enumClass);
    return posEnums;
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/**
* Gets from camera
* and constructs Java enum on specific
* camera property. For instance:
* EdsExposure on EDSDK's kEdsPropID_Tv.
*
* Gets ONLY UInt32 camera properties that have encodings.
*
* Example:
* return getCamUInt32PropEnum(env, thisCamera, "EdsExposure", kEdsPropID_Tv);
* return getCamUInt32PropEnum(env, thisCamera, "org/src/EdsExposure", kEdsPropID_Tv);
*
* @param env JNI environment pointer
* @param camObj Camera class object
* @param enumName Full name on Java enum class
* @param propID EDSDK property ID
* @return Constructed Java enum object
*/
jobject getCamUInt32PropEnum(JNIEnv* env, jobject camObj, std::string enumName, EdsPropertyID propID) {

    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, camObj);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera && isCameraSessionOpen(env, camObj)) {
        //Waiting for camera access
        waitTillNotBusy(camera);

        //Standart camera property getting procedure
        EdsUInt32 prop;
        err = EdsGetPropertyData(*camera, propID, 0, sizeof(EdsUInt32), &prop);

        //If property was received and contained in @prop - 
        //constructs corresponging Java enum object
        if (err == EDS_ERR_OK) {
            return enumFromCode(env, enumName, (jint)prop);
        }
    }
    /*-----------------------------------*/

     /*---------ERROR CODE RETURN---------*/
    //If something went wrong - returns NULL
    return nullptr;
    /*-----------------------------------*/
}

/**
* Gets from camera EdsChar[] property
* and returns it as Java String.
*
* Gets ONLY EdsChar[] camera properties.
*
* Example:
* return getCamStringProp(env, thiz, kEdsPropID_ProductName);
* return getCamStringProp(env, thiz, kEdsPropID_BodyIDEx);
*
*
* @param env JNI environment pointer
* @param camObj Camera class object
* @param propID EDSDK property ID
* @return Constructed Java String object
*/
jstring getCamStringProp(JNIEnv* env, jobject camObj, EdsPropertyID propID) {
    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, camObj);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera && isCameraSessionOpen(env, camObj)) {
        //Waiting for camera access
        waitTillNotBusy(camera);

        EdsChar line[MAX_STRING_LENGTH];
        err = EdsGetPropertyData(*camera, propID, 0, MAX_STRING_LENGTH * sizeof(EdsChar), &line);
        if (err == EDS_ERR_OK) {
            jstring result;
            result = env->NewStringUTF(line);
            return result;
        }
    }
    /*-----------------------------------*/

     /*---------ERROR CODE RETURN---------*/
    return nullptr;
    /*-----------------------------------*/
}

/**
* Gets from camera EdsInt32 property
* and returns it as Java int.
*
* Gets ONLY EdsInt32(EdsUInt32) camera properties that DOES NOT
* have encodings, such as battery level.
*
* Example:
* return getCamIntProp(env, thiz, kEdsPropID_BatteryLevel);
* return getCamIntProp(env, thiz, kEdsPropID_AvailableShots);
*
*
* @param env JNI environment pointer
* @param camObj Camera class object
* @param propID EDSDK property ID
* @return Constructed Java int
*/
jint getCamIntProp(JNIEnv* env, jobject camObj, EdsPropertyID propID) {

    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, camObj);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera && isCameraSessionOpen(env, camObj)) {
        //Waiting for camera access
        waitTillNotBusy(camera);

        EdsUInt32 val;
        err = EdsGetPropertyData(*camera, propID, 0, sizeof(EdsUInt32), &val);
        if (err == EDS_ERR_OK) {
            return (jint)val;
        }
    }
    /*-----------------------------------*/

     /*---------ERROR CODE RETURN---------*/
    return -1;
    /*-----------------------------------*/
}

/**
* Gets from camera and
* constructs Java enum list on specific
* possible camera property values. For instance:
* EdsExposure on EDSDK's kEdsPropID_Tv.
*
*
* Example:
* return getCamInt32PossiblePropVals(env, thisCamera, "EdsExposure", kEdsPropID_Tv);
* return getCamInt32PossiblePropVals(env, thisCamera, "org/src/EdsExposure", kEdsPropID_Tv);
*
* @param env JNI environment pointer
* @param camObj Camera class object
* @param enumName Full name on Java enum class
* @param propID EDSDK property ID
* @return Constructed Java enum object array
*/
jobjectArray getCamUInt32PossiblePropVals(JNIEnv* env, jobject camObj, std::string enumName, EdsPropertyID propID) {

    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, camObj);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera && isCameraSessionOpen(env, camObj)) {
        //Waiting for camera access
        waitTillNotBusy(camera);

        //Standart procedure for getting
        //possible camera property values
        EdsPropertyDesc list;
        err = EdsGetPropertyDesc(*camera, propID, &list);

        //If property list was received and contained in @list - 
        //constructs corresponging Java enum object array
        if (err == EDS_ERR_OK) {
            return enumListFromCodeList(env, enumName, list);
        }
    }
    /*-----------------------------------*/


    /*---------ERROR CODE RETURN---------*/
    //If something went wrong - returns NULL
    return nullptr;
    /*-----------------------------------*/
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/**
* Sets camera Int32 property
*
* @param env JNI environment pointer
* @param camObj Camera class object
* @param jpropEnum Java enum object with property value
* @param propID EDSDK property ID
* @return Error Code, if ok - returns EDS_ERR_OK
*/
jobject setCamUInt32PropEnum(JNIEnv* env, jobject camObj, jobject jpropEnum, EdsPropertyID propID) {
    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, camObj);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera) {
        if (isCameraSessionOpen(env, camObj)) {
            //Waiting for camera access
            waitTillNotBusy(camera);

            //Gets Java enum class from enum object
            jclass enumClass = env->GetObjectClass(jpropEnum);

            //Gets getCode() method pointer
            jmethodID getCode = env->GetMethodID(enumClass, "getCode", "()I");

            //Gets code of enum object 
            EdsInt32 propVal = (EdsInt32)env->CallIntMethod(jpropEnum, getCode);

            //Must delete enum class reference
            env->DeleteLocalRef(enumClass);

            //Sets camera propery
            err = EdsSetPropertyData(*camera, propID, 0, sizeof(EdsInt32), &propVal);
        }
        else {
            err = EDS_ERR_SESSION_NOT_OPEN;
        }
    }
    else {
        err = EDS_ERR_DEVICE_NOT_FOUND;
    }
    /*-----------------------------------*/


    /*---------ERROR CODE RETURN---------*/
    //If error occured - returns EdsError enum object
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}

jobject setCamStringProp(JNIEnv* env, jobject camObj, jstring line, EdsPropertyID propID) {
    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, camObj);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera) {
        if (isCameraSessionOpen(env, camObj)) {
            //Waiting for camera access
            waitTillNotBusy(camera);

            auto stringProp = env->GetStringChars(line, 0);
            err = EdsSetPropertyData(*camera, propID, 0, env->GetStringLength(line) * sizeof(EdsChar), stringProp);
        }
        else {
            err = EDS_ERR_SESSION_NOT_OPEN;
        }
    }
    else {
        err = EDS_ERR_DEVICE_NOT_FOUND;
    }
    /*-----------------------------------*/

     /*---------ERROR CODE RETURN---------*/
    //If error occured - returns EdsError enum object
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}
/*----------------------------------------------------------------------------------------------*/



/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    openSession
 * Signature: ()Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_openSession
(JNIEnv* env, jobject thiz) {
    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, thiz);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    //If reference is valid, opens camera session
    if (camera) {
        if (!isCameraSessionOpen(env, thiz)) {
            err = EdsOpenSession(*camera);
            jclass thizClass = env->GetObjectClass(thiz);
            jfieldID field = env->GetFieldID(thizClass, "isSessionOpen", "Z");
            env->SetBooleanField(thiz, field, true);
            env->DeleteLocalRef(thizClass);
        }
        else {
            err = EDS_ERR_SESSION_ALREADY_OPEN;
        }
    }
    /*-----------------------------------*/


    /*---------ERROR CODE RETURN---------*/
    //Returns EdsError enum object as result
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}

/*
 * Class:     CanonCamera
 * Method:    closeSession
 * Signature: ()Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_closeSession
(JNIEnv* env, jobject thiz) {

    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, thiz);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    //If camera reference is valid, closes session
    if (camera && isCameraSessionOpen(env, thiz)) {
        err = EdsCloseSession(*camera);
        jclass thizClass = env->GetObjectClass(thiz);
        jfieldID field = env->GetFieldID(thizClass, "isSessionOpen", "Z");
        env->SetBooleanField(thiz, field, false);
        env->DeleteLocalRef(thizClass);
    }
    /*-----------------------------------*/


    /*---------ERROR CODE RETURN---------*/
    //Returns EdsError enum object as result
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}

/*
 * Class:     CanonCamera
 * Method:    setCamRefFromList
 * Signature: (I)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setCamRefFromList
(JNIEnv* env, jobject thiz, jint index) {
    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = new EdsCameraRef();
    //Gets first camera in device list 
    err = getCameraRefFromList(camera, index);
    /*-----------------------------------*/


    /*------STORE CAMERA REFERENCE-------*/
    //Gets Camera Java class of Camera object 
    jclass thizClass = env->GetObjectClass(thiz);

    //Gets Camera Java class method to set camera reference
    jmethodID setRef = env->GetMethodID(thizClass, "setCamRef", "(J)V");

    //If @camera contains valid reference
    if (err == EDS_ERR_OK) {
        //Sets camera reference as long integer
        env->CallVoidMethod(thiz, setRef, (jlong)camera);
    }
    else {
        //Sets camera reference as 0
        env->CallVoidMethod(thiz, setRef, (jlong)0);
    }

    //Must delete Camera class reference
    env->DeleteLocalRef(thizClass);
    /*-----------------------------------*/

    /*---------ERROR CODE RETURN---------*/
    //Returns EdsError enum object as result
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}

/*
 * Class:     CanonCamera
 * Method:    releaseCamRef
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_camera_1api_canon_CanonCamera_releaseCamRef
(JNIEnv* env, jobject thiz) {
    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, thiz);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    //If reference is valid, releases 
    if (camera) {
        EdsRelease(*camera);
    }
    /*-----------------------------------*/
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    productName
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonCamera_productName
(JNIEnv* env, jobject thiz) {
    if (isCameraSessionOpen(env, thiz)) {
        return getCamStringProp(env, thiz, kEdsPropID_ProductName);
    }
    else {
        EdsError err = EDS_ERR_OK;

        EdsCameraRef* camera = getCameraRef(env, thiz);

        if (err == EDS_ERR_OK) {
            EdsDeviceInfo info;
            err = EdsGetDeviceInfo(*camera, &info);
            if (err == EDS_ERR_OK) {

                jstring deviceName = env->NewStringUTF(info.szDeviceDescription);

                return deviceName;
            }
        }
        return nullptr;
    }
}

/*
 * Class:     CanonCamera
 * Method:    bodyIDEx
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonCamera_bodyIDEx
(JNIEnv* env, jobject thiz) {
    return getCamStringProp(env, thiz, kEdsPropID_BodyIDEx);
}

/*
 * Class:     CanonCamera
 * Method:    setOwnerName
 * Signature: (Ljava/lang/String;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setOwnerName
(JNIEnv* env, jobject thiz, jstring line) {
    return setCamStringProp(env, thiz, line, kEdsPropID_Artist);
}

/*
 * Class:     CanonCamera
 * Method:    getOwnerName
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonCamera_getOwnerName
(JNIEnv* env, jobject thiz) {
    return getCamStringProp(env, thiz, kEdsPropID_Artist);
}

/*
 * Class:     CanonCamera
 * Method:    setCopyright
 * Signature: (Ljava/lang/String;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setCopyright
(JNIEnv* env, jobject thiz, jstring line) {
    return setCamStringProp(env, thiz, line, kEdsPropID_Copyright);
}

/*
 * Class:     CanonCamera
 * Method:    getCopyright
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonCamera_getCopyright
(JNIEnv* env, jobject thiz) {
    return getCamStringProp(env, thiz, kEdsPropID_Copyright);
}

/*
 * Class:     Camera
 * Method:    firmwareVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonCamera_firmwareVersion
(JNIEnv* env, jobject thiz) {
    return getCamStringProp(env, thiz, kEdsPropID_FirmwareVersion);
}

/*
 * Class:     CanonCamera
 * Method:    currentStorage
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonCamera_currentStorage
(JNIEnv* env, jobject thiz) {
    return getCamStringProp(env, thiz, kEdsPropID_CurrentStorage);
}

/*
 * Class:     CanonCamera
 * Method:    currentFolder
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonCamera_currentFolder
(JNIEnv* env, jobject thiz) {
    return getCamStringProp(env, thiz, kEdsPropID_CurrentFolder);
}

/*
 * Class:     CanonCamera
 * Method:    lensName
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonCamera_lensName
(JNIEnv* env, jobject thiz) {
    return getCamStringProp(env, thiz, kEdsPropID_LensName);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    batteryQuality
 * Signature: ()Lencodings/cameraprops/EdsBatteryQuality;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_batteryQuality
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_BATTERY_QUALITY_ENUM_NAME, kEdsPropID_BatteryQuality);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    batteryLevel
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_camera_1api_canon_CanonCamera_batteryLevel
(JNIEnv* env, jobject thiz) {
    return getCamIntProp(env, thiz, kEdsPropID_BatteryLevel);
}

/*
 * Class:     CanonCamera
 * Method:    availableShots
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_camera_1api_canon_CanonCamera_availableShots
(JNIEnv* env, jobject thiz) {
    return getCamIntProp(env, thiz, kEdsPropID_AvailableShots);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    autoFocus
 * Signature: ()Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_autoFocus__
(JNIEnv* env, jobject thiz) {
    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, thiz);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera) {
        if (isCameraSessionOpen(env, thiz)) {
            //Waiting for camera access
            waitTillNotBusy(camera);

            //AutoFocus
            err = EdsSendCommand(*camera, kEdsCameraCommand_PressShutterButton, kEdsCameraCommand_ShutterButton_Halfway);
            if (err == EDS_ERR_OK) {
                delay(AF_SECONDS);
                //Shutter Button Release
                err = EdsSendCommand(*camera, kEdsCameraCommand_PressShutterButton, kEdsCameraCommand_ShutterButton_OFF);
            }

            //Waiting for camera access
            waitTillNotBusy(camera);
        }
        else {
            err = EDS_ERR_SESSION_NOT_OPEN;
        }
    }
    else {
        err = EDS_ERR_DEVICE_NOT_FOUND;
    }
    /*-----------------------------------*/


    /*---------ERROR CODE RETURN---------*/
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}

/*
 * Class:     CanonCamera
 * Method:    takePicture
 * Signature: ()Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_takePicture__
(JNIEnv* env, jobject thiz) {

    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, thiz);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera) {
        if (isCameraSessionOpen(env, thiz)) {
            //Waiting for camera access
            waitTillNotBusy(camera);

            //Taking picture
            err = EdsSendCommand(*camera, kEdsCameraCommand_PressShutterButton, kEdsCameraCommand_ShutterButton_Completely);
            //Shutter Button Release
            err = (err == EDS_ERR_OK) ? EdsSendCommand(*camera, kEdsCameraCommand_PressShutterButton, kEdsCameraCommand_ShutterButton_OFF) : err;

            //Waiting for camera access
            waitTillNotBusy(camera);
        }
        else {
            err = EDS_ERR_SESSION_NOT_OPEN;
        }
    }
    else {
        err = EDS_ERR_DEVICE_NOT_FOUND;
    }
    /*-----------------------------------*/


    /*---------ERROR CODE RETURN---------*/
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}

/*
 * Class:     CanonCamera
 * Method:    takePicture
 * Signature: (D)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_takePicture__D
(JNIEnv* env, jobject thiz, jdouble seconds) {

    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, thiz);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (camera) {
        if (isCameraSessionOpen(env, thiz)) {
            Exposures reset;
            int exp;
            //Getting exposure setting
            err = EdsGetPropertyData(*camera, kEdsPropID_Tv, 0, sizeof(EdsInt32), &exp);
            if (err == EDS_ERR_OK) {
                //If current exposure setting is not BULB, sets BULB
                if ((Exposures)exp != Exposures::BULB) {
                    Exposures bulb = Exposures::BULB;
                    reset = (Exposures)exp;
                    err = EdsSetPropertyData(*camera, kEdsPropID_Tv, 0, sizeof(EdsInt32), &bulb);
                }
                if (err == EDS_ERR_OK) {
                    //Waiting for camera access
                    waitTillNotBusy(camera);

                    //UI Lock, must be done for BULB shooting
                    err = EdsSendStatusCommand(*camera, kEdsCameraStatusCommand_UILock, 0);

                    //Taking picture
                    if (err == EDS_ERR_OK) err = EdsSendCommand(*camera, kEdsCameraCommand_PressShutterButton, kEdsCameraCommand_ShutterButton_Completely);
                    //Exposure
                    if (err == EDS_ERR_OK) delay(seconds);
                    //Shutter Button Release
                    if (err == EDS_ERR_OK) err = EdsSendCommand(*camera, kEdsCameraCommand_PressShutterButton, kEdsCameraCommand_ShutterButton_OFF);

                    //Waiting for camera access
                    waitTillNotBusy(camera);

                    //UI Unlock
                    err = EdsSendStatusCommand(*camera, kEdsCameraStatusCommand_UIUnLock, 0);

                    //Waiting for camera access
                    waitTillNotBusy(camera);

                    //Returning old exposure settings
                    err = EdsSetPropertyData(*camera, kEdsPropID_Tv, 0, sizeof(EdsInt32), &exp);
                }
            }
        }
        else {
            err = EDS_ERR_SESSION_NOT_OPEN;
        }
    }
    else {
        err = EDS_ERR_DEVICE_NOT_FOUND;
    }
    /*-----------------------------------*/


    /*---------ERROR CODE RETURN---------*/
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    cameraUILock
 * Signature: (Z)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_cameraUILock
(JNIEnv* env, jobject thiz, jboolean lockStatus) {
    EdsError err = EDS_ERR_OK;

    /*------CAMERA REFERENCE GETTER------*/
    EdsCameraRef* camera = getCameraRef(env, thiz);
    /*-----------------------------------*/


    /*-------MAIN CODE OF FUNCTION-------*/
    if (err == EDS_ERR_OK) {

        //Waiting for camera access
        waitTillNotBusy(camera);

        if (lockStatus) {
            //UI Lock
            err = EdsSendStatusCommand(*camera, kEdsCameraStatusCommand_UILock, 0);
        }
        else {
            //UI Unlock
            err = EdsSendStatusCommand(*camera, kEdsCameraStatusCommand_UIUnLock, 0);
        }

        //Waiting for camera access
        waitTillNotBusy(camera);
    }
    /*-----------------------------------*/


    /*---------ERROR CODE RETURN---------*/
    return edsErrorFromCode(env, err);
    /*-----------------------------------*/
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setLVSettingsStatus
 * Signature: (Lencodings/cameraprops/EdsLiveViewSettings;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setLVSettingsStatus
(JNIEnv* env, jobject thiz, jobject liveView) {
    return setCamUInt32PropEnum(env, thiz, liveView, kEdsPropID_Evf_Mode);
}

/*
 * Class:     CanonCamera
 * Method:    getLVSettingsStatus
 * Signature: ()Lencodings/cameraprops/EdsLiveViewSettings;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getLVSettingsStatus
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_LV_SET_ENUM_NAME, kEdsPropID_Evf_Mode);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setExposure
 * Signature: (Lencodings/cameraprops/EdsExposure;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setExposure
(JNIEnv* env, jobject thiz, jobject exposure) {
    return setCamUInt32PropEnum(env, thiz, exposure, kEdsPropID_Tv);
}

/*
 * Class:     CanonCamera
 * Method:    getExposure
 * Signature: ()Lencodings/cameraprops/EdsExposure;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getExposure
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_EXPOSURE_ENUM_NAME, kEdsPropID_Tv);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleExposure
 * Signature: ()[Lencodings/cameraprops/EdsExposure;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleExposure
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_EXPOSURE_ENUM_NAME, kEdsPropID_Tv);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setISO
 * Signature: (Lencodings/cameraprops/EdsISO;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setISO
(JNIEnv* env, jobject thiz, jobject iso) {
    return setCamUInt32PropEnum(env, thiz, iso, kEdsPropID_ISOSpeed);
}

/*
 * Class:     CanonCamera
 * Method:    getISO
 * Signature: ()Lencodings/cameraprops/EdsISO;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getISO
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_ISO_ENUM_NAME, kEdsPropID_ISOSpeed);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleISO
 * Signature: ()[Lencodings/cameraprops/EdsISO;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleISO
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_ISO_ENUM_NAME, kEdsPropID_ISOSpeed);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setFocusSettings
 * Signature: (Lencodings/cameraprops/EdsFocusSettings;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setFocusSettings
(JNIEnv* env, jobject thiz, jobject focus) {
    return setCamUInt32PropEnum(env, thiz, focus, kEdsPropID_AFMode);
}

/*
 * Class:     CanonCamera
 * Method:    getFocusSettings
 * Signature: ()Lencodings/cameraprops/EdsFocusSettings;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getFocusSettings
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_AF_SET_ENUM_NAME, kEdsPropID_AFMode);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleFocusSettings
 * Signature: ()[Lencodings/cameraprops/EdsFocusSettings;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleFocusSettings
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_AF_SET_ENUM_NAME, kEdsPropID_AFMode);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setAperture
 * Signature: (Lencodings/cameraprops/EdsAperture;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setAperture
(JNIEnv* env, jobject thiz, jobject aperture) {
    return setCamUInt32PropEnum(env, thiz, aperture, kEdsPropID_Av);
}

/*
 * Class:     CanonCamera
 * Method:    getAperture
 * Signature: ()Lencodings/cameraprops/EdsAperture;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getAperture
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_AV_SET_ENUM_NAME, kEdsPropID_Av);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleAperture
 * Signature: ()[Lencodings/cameraprops/EdsAperture;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleAperture
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_AV_SET_ENUM_NAME, kEdsPropID_Av);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setSaveTo
 * Signature: (Lencodings/cameraprops/EdsSaveTo;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setSaveTo
(JNIEnv* env, jobject thiz, jobject destination) {
    return setCamUInt32PropEnum(env, thiz, destination, kEdsPropID_SaveTo);
}


/*
 * Class:     CanonCamera
 * Method:    getSaveTo
 * Signature: ()Lencodings/cameraprops/EdsSaveTo;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getSaveTo
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_SAVE_TO_ENUM_NAME, kEdsPropID_SaveTo);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setDriveMode
 * Signature: (Lencodings/cameraprops/EdsDriveMode;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setDriveMode
(JNIEnv* env, jobject thiz, jobject driveMode) {
    return setCamUInt32PropEnum(env, thiz, driveMode, kEdsPropID_DriveMode);
}

/*
 * Class:     CanonCamera
 * Method:    getDriveMode
 * Signature: ()Lencodings/cameraprops/EdsDriveMode;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getDriveMode
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_DRIVE_MODE_ENUM_NAME, kEdsPropID_DriveMode);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleDriveMode
 * Signature: ()[Lencodings/cameraprops/EdsDriveMode;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleDriveMode
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_DRIVE_MODE_ENUM_NAME, kEdsPropID_DriveMode);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setMeteringMode
 * Signature: (Lencodings/cameraprops/EdsMeteringMode;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setMeteringMode
(JNIEnv* env, jobject thiz, jobject meteringMode) {
    return setCamUInt32PropEnum(env, thiz, meteringMode, kEdsPropID_MeteringMode);
}

/*
 * Class:     CanonCamera
 * Method:    getMeteringMode
 * Signature: ()Lencodings/cameraprops/EdsMeteringMode;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getMeteringMode
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_METERING_MODE_ENUM_NAME, kEdsPropID_MeteringMode);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleMeteringMode
 * Signature: ()[Lencodings/cameraprops/EdsMeteringMode;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleMeteringMode
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_METERING_MODE_ENUM_NAME, kEdsPropID_MeteringMode);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    getBracketType
 * Signature: ()Lencodings/cameraprops/EdsBracket;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getBracketType
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_BRACKET_ENUM_NAME, kEdsPropID_Bracket);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setWhiteBalance
 * Signature: (Lencodings/cameraprops/EdsWhiteBalance;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setWhiteBalance
(JNIEnv* env, jobject thiz, jobject whiteBalance) {
    return setCamUInt32PropEnum(env, thiz, whiteBalance, kEdsPropID_WhiteBalance);
}

/*
 * Class:     CanonCamera
 * Method:    getWhiteBalance
 * Signature: ()Lencodings/cameraprops/EdsWhiteBalance;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getWhiteBalance
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_WB_ENUM_NAME, kEdsPropID_WhiteBalance);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleWhiteBalance
 * Signature: ()[Lencodings/cameraprops/EdsWhiteBalance;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleWhiteBalance
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_WB_ENUM_NAME, kEdsPropID_WhiteBalance);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setColorSpace
 * Signature: (Lencodings/cameraprops/EdsColorSpace;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setColorSpace
(JNIEnv* env, jobject thiz, jobject colorSpace) {
    return setCamUInt32PropEnum(env, thiz, colorSpace, kEdsPropID_ColorSpace);
}

/*
 * Class:     CanonCamera
 * Method:    getColorSpace
 * Signature: ()Lencodings/cameraprops/EdsColorSpace;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getColorSpace
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_COLOR_SPACE_ENUM_NAME, kEdsPropID_ColorSpace);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleColorSpace
 * Signature: ()[Lencodings/cameraprops/EdsColorSpace;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleColorSpace
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_COLOR_SPACE_ENUM_NAME, kEdsPropID_ColorSpace);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setPictureStyle
 * Signature: (Lencodings/cameraprops/EdsPictureStyle;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setPictureStyle
(JNIEnv* env, jobject thiz, jobject pictureStyle) {
    return setCamUInt32PropEnum(env, thiz, pictureStyle, kEdsPropID_PictureStyle);
}

/*
 * Class:     CanonCamera
 * Method:    getPictureStyle
 * Signature: ()Lencodings/cameraprops/EdsPictureStyle;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getPictureStyle
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_PICTURE_STYLE_ENUM_NAME, kEdsPropID_PictureStyle);
}

/*
 * Class:     CanonCamera
 * Method:    getPossiblePictureStyle
 * Signature: ()[Lencodings/cameraprops/EdsPictureStyle;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossiblePictureStyle
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_PICTURE_STYLE_ENUM_NAME, kEdsPropID_PictureStyle);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    getLensStatus
 * Signature: ()Lencodings/cameraprops/EdsLensStatus;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getLensStatus
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_LENS_STATUS_ENUM_NAME, kEdsPropID_LensStatus);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setAEModeSelect
 * Signature: (Lencodings/cameraprops/EdsAEModeSelect;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setAEModeSelect
(JNIEnv* env, jobject thiz, jobject select) {
    return setCamUInt32PropEnum(env, thiz, select, kEdsPropID_AEModeSelect);
}

/*
 * Class:     CanonCamera
 * Method:    getAEModeSelect
 * Signature: ()Lencodings/cameraprops/EdsAEModeSelect;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getAEModeSelect
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_AE_MODE_SELECT_ENUM_NAME, kEdsPropID_AEModeSelect);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleAEModeSelect
 * Signature: ()[Lencodings/cameraprops/EdsAEModeSelect;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleAEModeSelect
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_AE_MODE_SELECT_ENUM_NAME, kEdsPropID_AEModeSelect);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setExposureComp
 * Signature: (Lencodings/cameraprops/EdsExposureComp;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setExposureComp
(JNIEnv* env, jobject thiz, jobject expComp) {
    return setCamUInt32PropEnum(env, thiz, expComp, kEdsPropID_ExposureCompensation);
}

/*
 * Class:     CanonCamera
 * Method:    getExposureComp
 * Signature: ()Lencodings/cameraprops/EdsExposureComp;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getExposureComp
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_EXPOSURE_COMP_ENUM_NAME, kEdsPropID_ExposureCompensation);
}

/*
 * Class:     CanonCamera
 * Method:    getPossibleExposureComp
 * Signature: ()[Lencodings/cameraprops/EdsExposureComp;
 */
JNIEXPORT jobjectArray JNICALL Java_camera_1api_canon_CanonCamera_getPossibleExposureComp
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PossiblePropVals(env, thiz, CAM_PROP_ENUM_PATH EDS_EXPOSURE_COMP_ENUM_NAME, kEdsPropID_ExposureCompensation);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    getAEMode
 * Signature: ()Lencodings/cameraprops/EdsAEMode;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getAEMode
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_AE_MODE_ENUM_NAME, kEdsPropID_AEMode);
}
/*----------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------*/
/*
 * Class:     CanonCamera
 * Method:    setSummerTimeSetting
 * Signature: (Lencodings/cameraprops/EdsSummerTimeSetting;)Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_setSummerTimeSetting
(JNIEnv* env, jobject thiz, jobject sumTime) {
    return setCamUInt32PropEnum(env, thiz, sumTime, kEdsPropID_SummerTimeSetting);
}


/*
 * Class:     CanonCamera
 * Method:    getSummerTimeSetting
 * Signature: ()Lencodings/cameraprops/EdsSummerTimeSetting;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonCamera_getSummerTimeSetting
(JNIEnv* env, jobject thiz) {
    return getCamUInt32PropEnum(env, thiz, CAM_PROP_ENUM_PATH EDS_SUMMER_TIME_ENUM_NAME, kEdsPropID_SummerTimeSetting);
}
/*----------------------------------------------------------------------------------------------*/