package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsWhiteBalance implements CameraProp {

    AUTO                (0),
    DAYLIGHT            (1),
    CLOUDY              (2),
    TUNGSTEN            (3),
    FLUORESCENT         (4),
    FLASH               (5),
    MANUAL              (6),
    SHADE               (8),
    COLOR_TEMP          (9),
    PC_1                (10),
    PC_2                (11),
    PC_3                (12),
    MANUAL_2            (15),
    MANUAL_3            (16),
    MANUAL_4            (18),
    MANUAL_5            (19),
    PC_4                (20),
    PC_5                (21),
    AUTO_WHITE          (23);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsWhiteBalance(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsWhiteBalance fromCode(int code) {
        for (EdsWhiteBalance type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
