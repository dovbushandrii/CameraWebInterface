package camera_api.canon.encodings.sdk;

import camera_api.exceptions.NoSuchErrorCodeException;
import camera_api.interfaces.ErrorCode;

public enum EdsError implements ErrorCode {

/******************************************************************************
 Definition of error Codes
 ******************************************************************************/
/*-----------------------------------------------------------------------
    ED-SDK Error Code Masks
------------------------------------------------------------------------*/
    EDS_ISSPECIFIC_MASK                                 (0x80000000),
    EDS_COMPONENTID_MASK                                (0x7F000000),
    EDS_RESERVED_MASK                                   (0x00FF0000),
    EDS_ERRORID_MASK                                    (0x0000FFFF),
/*-----------------------------------------------------------------------
   ED-SDK Base Component IDs
------------------------------------------------------------------------*/
    EDS_CMP_ID_CLIENT_COMPONENTID                       (0x01000000),
    EDS_CMP_ID_LLSDK_COMPONENTID                        (0x02000000),
    EDS_CMP_ID_HLSDK_COMPONENTID                        (0x03000000),

/*-----------------------------------------------------------------------
    ED-SDK Functin Success Code
------------------------------------------------------------------------*/
    EDS_ERR_OK                                          (0x00000000),

/*-----------------------------------------------------------------------
   ED-SDK Generic Error IDs
------------------------------------------------------------------------*/
    /* Miscellaneous errors */
    EDS_ERR_UNIMPLEMENTED                               (0x00000001),
    EDS_ERR_INTERNAL_ERROR                              (0x00000002),
    EDS_ERR_MEM_ALLOC_FAILED                            (0x00000003),
    EDS_ERR_MEM_FREE_FAILED                             (0x00000004),
    EDS_ERR_OPERATION_CANCELLED                         (0x00000005),
    EDS_ERR_INCOMPATIBLE_VERSION                        (0x00000006),
    EDS_ERR_NOT_SUPPORTED                               (0x00000007),
    EDS_ERR_UNEXPECTED_EXCEPTION                        (0x00000008),
    EDS_ERR_PROTECTION_VIOLATION                        (0x00000009),
    EDS_ERR_MISSING_SUBCOMPONENT                        (0x0000000A),
    EDS_ERR_SELECTION_UNAVAILABLE                       (0x0000000B),

    /* File errors */
    EDS_ERR_FILE_IO_ERROR                               (0x00000020),
    EDS_ERR_FILE_TOO_MANY_OPEN                          (0x00000021),
    EDS_ERR_FILE_NOT_FOUND                              (0x00000022),
    EDS_ERR_FILE_OPEN_ERROR                             (0x00000023),
    EDS_ERR_FILE_CLOSE_ERROR                            (0x00000024),
    EDS_ERR_FILE_SEEK_ERROR                             (0x00000025),
    EDS_ERR_FILE_TELL_ERROR                             (0x00000026),
    EDS_ERR_FILE_READ_ERROR                             (0x00000027),
    EDS_ERR_FILE_WRITE_ERROR                            (0x00000028),
    EDS_ERR_FILE_PERMISSION_ERROR                       (0x00000029),
    EDS_ERR_FILE_DISK_FULL_ERROR                        (0x0000002A),
    EDS_ERR_FILE_ALREADY_EXISTS                         (0x0000002B),
    EDS_ERR_FILE_FORMAT_UNRECOGNIZED                    (0x0000002C),
    EDS_ERR_FILE_DATA_CORRUPT                           (0x0000002D),
    EDS_ERR_FILE_NAMING_NA                              (0x0000002E),

    /* Directory errors */
    EDS_ERR_DIR_NOT_FOUND                               (0x00000040),
    EDS_ERR_DIR_IO_ERROR                                (0x00000041),
    EDS_ERR_DIR_ENTRY_NOT_FOUND                         (0x00000042),
    EDS_ERR_DIR_ENTRY_EXISTS                            (0x00000043),
    EDS_ERR_DIR_NOT_EMPTY                               (0x00000044),

    /* Property errors */
    EDS_ERR_PROPERTIES_UNAVAILABLE                      (0x00000050),
    EDS_ERR_PROPERTIES_MISMATCH                         (0x00000051),
    EDS_ERR_PROPERTIES_NOT_LOADED                       (0x00000053),

    /* Function Parameter errors */
    EDS_ERR_INVALID_PARAMETER                           (0x00000060),
    EDS_ERR_INVALID_HANDLE                              (0x00000061),
    EDS_ERR_INVALID_POINTER                             (0x00000062),
    EDS_ERR_INVALID_INDEX                               (0x00000063),
    EDS_ERR_INVALID_LENGTH                              (0x00000064),
    EDS_ERR_INVALID_FN_POINTER                          (0x00000065),
    EDS_ERR_INVALID_SORT_FN                             (0x00000066),

    /* Device errors */
    EDS_ERR_DEVICE_NOT_FOUND                            (0x00000080),
    EDS_ERR_DEVICE_BUSY                                 (0x00000081),
    EDS_ERR_DEVICE_INVALID                              (0x00000082),
    EDS_ERR_DEVICE_EMERGENCY                            (0x00000083),
    EDS_ERR_DEVICE_MEMORY_FULL                          (0x00000084),
    EDS_ERR_DEVICE_INTERNAL_ERROR                       (0x00000085),
    EDS_ERR_DEVICE_INVALID_PARAMETER                    (0x00000086),
    EDS_ERR_DEVICE_NO_DISK                              (0x00000087),
    EDS_ERR_DEVICE_DISK_ERROR                           (0x00000088),
    EDS_ERR_DEVICE_CF_GATE_CHANGED                      (0x00000089),
    EDS_ERR_DEVICE_DIAL_CHANGED                         (0x0000008A),
    EDS_ERR_DEVICE_NOT_INSTALLED                        (0x0000008B),
    EDS_ERR_DEVICE_STAY_AWAKE                           (0x0000008C),
    EDS_ERR_DEVICE_NOT_RELEASED                         (0x0000008D),

    /* Stream errors */
    EDS_ERR_STREAM_IO_ERROR                             (0x000000A0),
    EDS_ERR_STREAM_NOT_OPEN                             (0x000000A1),
    EDS_ERR_STREAM_ALREADY_OPEN                         (0x000000A2),
    EDS_ERR_STREAM_OPEN_ERROR                           (0x000000A3),
    EDS_ERR_STREAM_CLOSE_ERROR                          (0x000000A4),
    EDS_ERR_STREAM_SEEK_ERROR                           (0x000000A5),
    EDS_ERR_STREAM_TELL_ERROR                           (0x000000A6),
    EDS_ERR_STREAM_READ_ERROR                           (0x000000A7),
    EDS_ERR_STREAM_WRITE_ERROR                          (0x000000A8),
    EDS_ERR_STREAM_PERMISSION_ERROR                     (0x000000A9),
    EDS_ERR_STREAM_COULDNT_BEGIN_THREAD                 (0x000000AA),
    EDS_ERR_STREAM_BAD_OPTIONS                          (0x000000AB),
    EDS_ERR_STREAM_END_OF_STREAM                        (0x000000AC),

    /* Communications errors */
    EDS_ERR_COMM_PORT_IS_IN_USE                         (0x000000C0),
    EDS_ERR_COMM_DISCONNECTED                           (0x000000C1),
    EDS_ERR_COMM_DEVICE_INCOMPATIBLE                    (0x000000C2),
    EDS_ERR_COMM_BUFFER_FULL                            (0x000000C3),
    EDS_ERR_COMM_USB_BUS_ERR                            (0x000000C4),

    /* Lock/Unlock */
    EDS_ERR_USB_DEVICE_LOCK_ERROR                       (0x000000D0),
    EDS_ERR_USB_DEVICE_UNLOCK_ERROR                     (0x000000D1),

    /* STI/WIA */
    EDS_ERR_STI_UNKNOWN_ERROR                           (0x000000E0),
    EDS_ERR_STI_INTERNAL_ERROR                          (0x000000E1),
    EDS_ERR_STI_DEVICE_CREATE_ERROR                     (0x000000E2),
    EDS_ERR_STI_DEVICE_RELEASE_ERROR                    (0x000000E3),
    EDS_ERR_DEVICE_NOT_LAUNCHED                         (0x000000E4),

    EDS_ERR_ENUM_NA                                     (0x000000F0),
    EDS_ERR_INVALID_FN_CALL                             (0x000000F1),
    EDS_ERR_HANDLE_NOT_FOUND                            (0x000000F2),
    EDS_ERR_INVALID_ID                                  (0x000000F3),
    EDS_ERR_WAIT_TIMEOUT_ERROR                          (0x000000F4),

    /* PTP */
    EDS_ERR_SESSION_NOT_OPEN                            (0x00002003),
    EDS_ERR_INVALID_TRANSACTIONID                       (0x00002004),
    EDS_ERR_INCOMPLETE_TRANSFER                         (0x00002007),
    EDS_ERR_INVALID_STRAGEID                            (0x00002008),
    EDS_ERR_DEVICEPROP_NOT_SUPPORTED                    (0x0000200A),
    EDS_ERR_INVALID_OBJECTFORMATCODE                    (0x0000200B),
    EDS_ERR_SELF_TEST_FAILED                            (0x00002011),
    EDS_ERR_PARTIAL_DELETION                            (0x00002012),
    EDS_ERR_SPECIFICATION_BY_FORMAT_UNSUPPORTED         (0x00002014),
    EDS_ERR_NO_VALID_OBJECTINFO                         (0x00002015),
    EDS_ERR_INVALID_CODE_FORMAT                         (0x00002016),
    EDS_ERR_UNKNOWN_VENDOR_CODE                         (0x00002017),
    EDS_ERR_CAPTURE_ALREADY_TERMINATED                  (0x00002018),
    EDS_ERR_PTP_DEVICE_BUSY                             (0x00002019),
    EDS_ERR_INVALID_PARENTOBJECT                        (0x0000201A),
    EDS_ERR_INVALID_DEVICEPROP_FORMAT                   (0x0000201B),
    EDS_ERR_INVALID_DEVICEPROP_VALUE                    (0x0000201C),
    EDS_ERR_SESSION_ALREADY_OPEN                        (0x0000201E),
    EDS_ERR_TRANSACTION_CANCELLED                       (0x0000201F),
    EDS_ERR_SPECIFICATION_OF_DESTINATION_UNSUPPORTED    (0x00002020),
    EDS_ERR_NOT_CAMERA_SUPPORT_SDK_VERSION              (0x00002021),

    /* PTP Vendor */
    EDS_ERR_UNKNOWN_COMMAND                             (0x0000A001),
    EDS_ERR_OPERATION_REFUSED                           (0x0000A005),
    EDS_ERR_LENS_COVER_CLOSE                            (0x0000A006),
    EDS_ERR_LOW_BATTERY                                 (0x0000A101),
    EDS_ERR_OBJECT_NOTREADY                             (0x0000A102),
    EDS_ERR_CANNOT_MAKE_OBJECT                          (0x0000A104),
    EDS_ERR_MEMORYSTATUS_NOTREADY                       (0x0000A106),

    /* Take Picture errors */
    EDS_ERR_TAKE_PICTURE_AF_NG                          (0x00008D01),
    EDS_ERR_TAKE_PICTURE_RESERVED                       (0x00008D02),
    EDS_ERR_TAKE_PICTURE_MIRROR_UP_NG                   (0x00008D03),
    EDS_ERR_TAKE_PICTURE_SENSOR_CLEANING_NG             (0x00008D04),
    EDS_ERR_TAKE_PICTURE_SILENCE_NG                     (0x00008D05),
    EDS_ERR_TAKE_PICTURE_NO_CARD_NG                     (0x00008D06),
    EDS_ERR_TAKE_PICTURE_CARD_NG                        (0x00008D07),
    EDS_ERR_TAKE_PICTURE_CARD_PROTECT_NG                (0x00008D08),
    EDS_ERR_TAKE_PICTURE_MOVIE_CROP_NG                  (0x00008D09),
    EDS_ERR_TAKE_PICTURE_STROBO_CHARGE_NG               (0x00008D0A),
    EDS_ERR_TAKE_PICTURE_NO_LENS_NG                     (0x00008D0B),
    EDS_ERR_TAKE_PICTURE_SPECIAL_MOVIE_MODE_NG          (0x00008D0C),
    EDS_ERR_TAKE_PICTURE_LV_REL_PROHIBIT_MODE_NG        (0x00008D0D),

    EDS_ERR_LAST_GENERIC_ERROR_PLUS_ONE                 (0x000000F5);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of the error
     */
    EdsError(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsError fromCode(int code){
        for (EdsError type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new NoSuchErrorCodeException("Invalid code/sdk is not initialized");
    }
}
