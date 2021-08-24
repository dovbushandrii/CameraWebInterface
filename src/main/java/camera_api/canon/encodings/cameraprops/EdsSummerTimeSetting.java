package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

import java.rmi.NoSuchObjectException;

public enum EdsSummerTimeSetting implements CameraProp {

    OFF(0),
    ON(1);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsSummerTimeSetting(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsSummerTimeSetting fromCode(int code) {
        for (EdsSummerTimeSetting type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }
}
