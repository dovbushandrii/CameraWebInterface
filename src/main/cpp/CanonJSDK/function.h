#include "EDSDK.h"
#include "EDSDKErrors.h"

/**
* Gets first camera from
* list of available cameras.
* @param camera Pointer on camera that will be returned
* @return Error Code, if ok - returns EDS_ERR_OK
*/
EdsError getCameraRefFromList(EdsCameraRef* camera, int index);