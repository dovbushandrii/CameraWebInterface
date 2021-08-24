package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsFocusSettings implements CameraProp {
    ONE_SHOT(0, "One-Shot AF"),
    AI_SERVO(1, "AI Servo AF"),
    AI_FOCUS(2, "AI Focus AF"),
    MANUAL(3, "Manual Focus");
    //NOT_VALID       (-1,"Not valid/no settings changes");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of AF setting
     */
    EdsFocusSettings(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsFocusSettings fromCode(int code) {
        for (EdsFocusSettings type : values()) {
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
