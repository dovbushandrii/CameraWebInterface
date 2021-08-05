package camera_api.canon.encodings.cameraprops;

import camera_api.CameraProp;

public enum EdsDriveMode implements CameraProp {

    SINGLE_SHOOT            (0x00000000),
    CONTIN_SHOOT            (0x00000001),
    VIDEO                   (0x00000002),
    HIGH_SPD_CONTIN         (0x00000004),
    LOW_SPD_CONTIN          (0x00000005),
    SINGLE_SILENT_SHOOT     (0x00000006),
    SELF_TIMER_CONTIN       (0x00000007),
    SELF_TIMER_10_SEC       (0x00000010),
    SELF_TIMER_2_SEC        (0x00000011),
    FPS_14_SUPER_HIGH       (0x00000012),
    SILENT_SINGLE_SHOOT     (0x00000013),
    SILENT_CONTIN_SHOOT     (0x00000014),
    SILENT_HS_CONTIN        (0x00000015),
    SILENT_LS_CONTIN        (0x00000016);


    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsDriveMode(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsDriveMode fromCode(int code) {
        for (EdsDriveMode type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
