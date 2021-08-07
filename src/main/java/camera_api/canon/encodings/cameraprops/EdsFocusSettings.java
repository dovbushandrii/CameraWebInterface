package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsFocusSettings implements CameraProp {
    ONE_SHOT        (0, "One-Shot AF"),
    AI_SERVO        (1, "AI Servo AF"),
    AI_FOCUS        (2, "AI Focus AF"),
    MANUAL          (3, "Manual Focus"),
    NOT_VALID       (-1,"Not valid/no settings changes");

    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of AF setting
     */
    EdsFocusSettings(int code, String line) {
        this.code = code;
        this.line = line;
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

    @Override
    public String toString(){
        return this.line;
    }
}
