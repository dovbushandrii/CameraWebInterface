package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsBatteryQuality implements CameraProp {

    FULL(3, "No degradation"),
    HI(2, "Slight degradation"),
    HALF(1, "Degraded"),
    LOW(0, "Degraded");


    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsBatteryQuality(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsBatteryQuality fromCode(int code) {
        for (EdsBatteryQuality type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    public static EdsBatteryQuality fromValue(String value) {
        for (EdsBatteryQuality type : values()) {
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
