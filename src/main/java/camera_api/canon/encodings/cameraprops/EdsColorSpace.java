package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsColorSpace implements CameraProp {

    sRGB            (1),
    ADOBE_RGB       (2),
    UNKNOWN         (-1);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsColorSpace(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsColorSpace fromCode(int code) {
        for (EdsColorSpace type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
