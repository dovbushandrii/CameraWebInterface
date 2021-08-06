package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsFocusSettings implements CameraProp {
    ONE_SHOT(0),
    AI_SERVO(1),
    AI_FOCUS(2),
    MANUAL(3),
    NOT_VALID (-1);
    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of AF setting
     */
    EdsFocusSettings(int code) {
        this.code = code;
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
        return null;
    }
}
