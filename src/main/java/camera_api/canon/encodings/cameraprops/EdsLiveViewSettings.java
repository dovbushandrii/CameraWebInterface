package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

//kEdsPropID_Evf_Mode
public enum EdsLiveViewSettings implements CameraProp {

    DISABLE(0, "Disable"),
    ENABLE(1, "Enable");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsLiveViewSettings(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsLiveViewSettings fromCode(int code) {
        for (EdsLiveViewSettings type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    @Override
    public String toString() {
        return this.value;
    }
}
