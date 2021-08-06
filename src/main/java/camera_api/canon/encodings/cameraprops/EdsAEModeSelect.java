package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsAEModeSelect implements CameraProp {

    CUSTOM_1        (7),
    CUSTOM_2        (16),
    CUSTOM_3        (17),
    SCN_SPECIAL     (25);

    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsAEModeSelect(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsAEModeSelect fromCode(int code) {
        for (EdsAEModeSelect type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
