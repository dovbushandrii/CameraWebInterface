/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class CanonSDK */

#ifndef _Included_CanonSDK
#define _Included_CanonSDK
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     CanonSDK
 * Method:    getCameraListSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_camera_1api_canon_CanonSDK_getCameraListSize
  (JNIEnv *, jclass);

/*
 * Class:     CanonSDK
 * Method:    initializeNativeSDK
 * Signature: ()Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonSDK_initializeNativeSDK
  (JNIEnv *, jobject);

/*
 * Class:     CanonSDK
 * Method:    terminateNativeSDK
 * Signature: ()Lencodings/sdk/EdsError;
 */
JNIEXPORT jobject JNICALL Java_camera_1api_canon_CanonSDK_terminateNativeSDK
  (JNIEnv *, jobject);

/*
 * Class:     CanonSDK
 * Method:    getDevicePortInfo
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_camera_1api_canon_CanonSDK_getCameraPortInfo
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
