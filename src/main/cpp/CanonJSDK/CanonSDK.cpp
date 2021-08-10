// Camera.cpp : Defines the exported functions for the DLL.
//

#include "pch.h"
#include "framework.h"
#include "CanonSDK.h"
#include "EDSDK.h"
#include "EDSDKErrors.h"
#include "EDSDKTypes.h"

jboolean isInitialized(JNIEnv* env) {
    jclass CanonSKDClass = env->FindClass("camera_api/canon/CanonSDK");
    jfieldID field = env->GetStaticFieldID(CanonSKDClass, "isInitialized", "Z");
    jboolean isInit = env->GetStaticBooleanField(CanonSKDClass, field);
    env->DeleteLocalRef(CanonSKDClass);
    return isInit;
}

/*
 * Class:     camera_api/canon/CanonSDK
 * Method:    getCameraListSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_camera_1api_canon_CanonSDK_getCameraListSize
(JNIEnv* env, jclass thiz) {
    if (isInitialized(env)) {
        EdsError err = EDS_ERR_OK;
        EdsCameraListRef cameraList = NULL;
        EdsUInt32 count = 0;
        // Get camera list
        err = EdsGetCameraList(&cameraList);
        // Get number of cameras
        if (err == EDS_ERR_OK)
        {
            err = EdsGetChildCount(cameraList, &count);
        }
        if (cameraList != NULL)
        {
            EdsRelease(cameraList);
            cameraList = NULL;
        }
        if (err == EDS_ERR_OK) {
            return count;
        }
    }
    return -1;
}

/*
 * Class:     camera_api/canon/CanonSDK
 * Method:    initializeNativeSDK
 * Signature: ()Lcamera_api/canon/encodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonSDK_initializeNativeSDK
(JNIEnv* env, jclass){
    EdsError err = EDS_ERR_OK;
    // Initialize SDK
    if (!isInitialized(env)) {
        err = EdsInitializeSDK();

        jclass CanonSKDClass = env->FindClass("camera_api/canon/CanonSDK");
        jfieldID field = env->GetStaticFieldID(CanonSKDClass, "isInitialized", "Z");
        env->SetStaticBooleanField(CanonSKDClass, field, true);
        env->DeleteLocalRef(CanonSKDClass);
    }
    else {
        err = EDS_ERR_SESSION_NOT_OPEN;
    }
    jclass enumClass = env->FindClass("camera_api/canon/encodings/sdk/EdsError");
    jmethodID enumConstruct = env->GetStaticMethodID(enumClass, "fromCode", "(I)Lcamera_api/canon/encodings/sdk/EdsError;");
    jobject EdsERROR = env->CallStaticObjectMethod(enumClass, enumConstruct, (jint)err);
    env->DeleteLocalRef(enumClass);
    return EdsERROR;
}

/*
 * Class:     camera_api/canon/CanonSDK
 * Method:    terminateNativeSDK
 * Signature: ()Lcamera_api/canon/encodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonSDK_terminateNativeSDK
(JNIEnv* env, jclass) {
    EdsError err = EDS_ERR_OK;
    if (isInitialized(env)) {
        // Terminate SDK
        err = EdsTerminateSDK();

        jclass CanonSKDClass = env->FindClass("camera_api/canon/CanonSDK");
        jfieldID field = env->GetStaticFieldID(CanonSKDClass, "isInitialized", "Z");
        env->SetStaticBooleanField(CanonSKDClass, field, false);
        env->DeleteLocalRef(CanonSKDClass);
    }
    else {
        err = EDS_ERR_SESSION_NOT_OPEN;
    }

    jclass enumClass = env->FindClass("camera_api/canon/encodings/sdk/EdsError");
    jmethodID enumConstruct = env->GetStaticMethodID(enumClass, "fromCode", "(I)Lcamera_api/canon/encodings/sdk/EdsError;");
    jobject EdsERROR = env->CallStaticObjectMethod(enumClass, enumConstruct, (jint)err);
    env->DeleteLocalRef(enumClass);
    return EdsERROR;
}


/*
 * Class:     camera_api/canon/CanonSDK
 * Method:    getDevicePortInfo
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonSDK_getDevicePortInfo
(JNIEnv* env, jclass thiz, jint index) {
    EdsError err = EDS_ERR_OK;

    EdsCameraRef* camera = new EdsCameraRef();
    err = getCameraRefFromList(camera, index);

    if (err == EDS_ERR_OK) {
        EdsDeviceInfo info;
        err = EdsGetDeviceInfo(*camera, &info);
        if (err == EDS_ERR_OK) {

            jstring portInfo = env->NewStringUTF(info.szPortName);

            return portInfo;
        }
    }
    return env->NewStringUTF("");
}