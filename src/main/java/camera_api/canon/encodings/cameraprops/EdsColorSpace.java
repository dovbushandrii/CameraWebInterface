package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.encogings.CameraProp;

import java.util.Arrays;

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
        return Arrays.stream(values())
                .filter(type -> type.getCode() == code)
                .findFirst()
                .orElseThrow(NoSuchPropertyValueException::new);
    }

    public static EdsColorSpace fromValue(String value) {
        return Arrays.stream(values())
                .filter(type -> type.toString().equals(value))
                .findFirst()
                .orElseThrow(NoSuchPropertyValueException::new);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
