package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsLensStatus implements CameraProp {

    NOT_ATTACHED(0, "The lens is not attached"),
    ATTACHED(1, "The lens is attached");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsLensStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsLensStatus fromCode(int code) {
        for (EdsLensStatus type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    public static EdsLensStatus fromValue(String value) {
        for (EdsLensStatus type : values()) {
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
