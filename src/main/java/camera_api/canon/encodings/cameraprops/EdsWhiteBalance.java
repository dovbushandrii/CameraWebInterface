package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.encogings.CameraProp;

import java.util.Arrays;

public enum EdsWhiteBalance implements CameraProp {

    AUTO(0, "Auto: Ambience priority"),
    DAYLIGHT(1, "Daylight"),
    CLOUDY(2, "Cloudy"),
    TUNGSTEN(3, "Tungsten"),
    FLUORESCENT(4, "Fluorescent"),
    FLASH(5, "Flash"),
    MANUAL(6, "Manual (White card)"),
    SHADE(8, "Shade"),
    COLOR_TEMP(9, "Color temperature"),
    PC_1(10, "Custom: PC-1"),
    PC_2(11, "Custom: PC-2"),
    PC_3(12, "Custom: PC-3"),
    MANUAL_2(15, "Manual 2"),
    MANUAL_3(16, "Manual 3"),
    MANUAL_4(18, "Manual 4"),
    MANUAL_5(19, "Manual 5"),
    PC_4(20, "Custom: PC-4"),
    PC_5(21, "Custom: PC-5"),
    AUTO_WHITE_PRIOR(23, "Auto: White priority");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsWhiteBalance(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static CameraProp fromCode(int code) {
        return Arrays.stream(values())
                .filter(type -> type.getCode() == code)
                .findFirst()
                .orElseThrow(NoSuchPropertyValueException::new);
    }

    public static CameraProp fromValue(String value) {
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
