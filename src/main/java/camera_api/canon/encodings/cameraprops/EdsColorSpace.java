package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.encogings.CameraProp;

public enum EdsColorSpace implements CameraProp {

    sRGB(1, "sRGB"),
    ADOBE_RGB(2, "Adobe RGB"),
    UNKNOWN(-1, "Unknown");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsColorSpace(int code, String value) {
        this.code = code;
        this.value = value;
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
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    public static EdsColorSpace fromValue(String value) {
        for (EdsColorSpace type : values()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid settings value");
    }

    @Override
    public String toString() {
        return this.value;
    }
}
