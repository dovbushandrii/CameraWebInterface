package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsWhiteBalance implements CameraProp {

    AUTO                (0, "Auto: Ambience priority"),
    DAYLIGHT            (1, "Daylight"),
    CLOUDY              (2, "Cloudy"),
    TUNGSTEN            (3, "Tungsten"),
    FLUORESCENT         (4, "Fluorescent"),
    FLASH               (5, "Flash"),
    MANUAL              (6, "Manual (White card)"),
    SHADE               (8, "Shade"),
    COLOR_TEMP          (9, "Color temperature"),
    PC_1                (10,"Custom: PC-1"),
    PC_2                (11,"Custom: PC-2"),
    PC_3                (12,"Custom: PC-3"),
    MANUAL_2            (15,"Manual 2"),
    MANUAL_3            (16,"Manual 3"),
    MANUAL_4            (18,"Manual 4"),
    MANUAL_5            (19,"Manual 5"),
    PC_4                (20,"Custom: PC-4"),
    PC_5                (21,"Custom: PC-5"),
    AUTO_WHITE_Prior    (23,"Auto: White priority");

    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsWhiteBalance(int code, String line) {
        this.code = code;
        this.line = line;
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

    @Override
    public String toString(){
        return this.line;
    }
}
