package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsBatteryQuality implements CameraProp {

    FULL(3, "No degradation"),
    HI(2, "Slight degradation"),
    HALF(1, "Degraded"),
    LOW(0, "Degraded");


    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsBatteryQuality(int code, String line) {
        this.code = code;
        this.line = line;
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

    @Override
    public String toString() {
        return this.line;
    }
}
