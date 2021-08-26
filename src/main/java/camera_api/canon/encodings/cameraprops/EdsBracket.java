package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsBracket implements CameraProp {

    AE(0x01, "AE bracket"),
    ISO(0x02, "ISO bracket"),
    WB(0x04, "WB bracket"),
    FE(0x08, "FE bracket"),
    AE_ISO(0x03, "AE+ISO brackets"),
    AE_WB(0x05, "AE+WB brackets"),
    AE_FE(0x09, "AE+FE brackets"),
    ISO_WB(0x06, "ISO+WB brackets"),
    ISO_FE(0x0A, "ISO+FE brackets"),
    WB_FE(0x0C, "WB+FE brackets"),
    AE_ISO_WB(0x07, "AE+ISO+WB brackets"),
    AE_ISO_FE(0x0B, "AE+ISO+FE brackets"),
    AE_WB_FE(0x0D, "AE+WB+FE brackets"),
    ISO_WB_FE(0x0E, "ISO+WB+FE brackets"),
    AE_ISO_WB_FE(0x0, "AE+ISO+WB+FE brackets"),
    BRACKET_OFF(-1, "Bracket off");


    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsBracket(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsBracket fromCode(int code) {
        for (EdsBracket type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    public static EdsBracket fromValue(String value) {
        for (EdsBracket type : values()) {
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
