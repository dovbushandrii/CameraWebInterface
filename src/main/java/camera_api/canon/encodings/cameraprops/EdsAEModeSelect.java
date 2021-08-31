package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.encogings.CameraProp;

import java.util.Arrays;


public enum EdsAEModeSelect implements CameraProp {

    CUSTOM_1(7, "Custom1"),
    CUSTOM_2(16, "Custom2"),
    CUSTOM_3(17, "Custom3"),
    SCN_SPECIAL(25, "SCN Special scene");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsAEModeSelect(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsAEModeSelect fromCode(int code) {
        return Arrays.stream(values())
                .filter(type -> type.getCode() == code)
                .findFirst()
                .orElseThrow(NoSuchPropertyValueException::new);
    }

    public static EdsAEModeSelect fromValue(String value) {
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
