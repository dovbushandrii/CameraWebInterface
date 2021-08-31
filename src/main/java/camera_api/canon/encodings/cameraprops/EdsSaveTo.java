package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.encogings.CameraProp;

import java.util.Arrays;

public enum EdsSaveTo implements CameraProp {

    MEM_CARD(1, "Save on a memory card"),
    HOST_COMPUTER(2, "Save on a host computer"),
    BOTH(3, "Save both way");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsSaveTo(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsSaveTo fromCode(int code) {
        return Arrays.stream(values())
                .filter(type -> type.getCode() == code)
                .findFirst()
                .orElseThrow(NoSuchPropertyValueException::new);
    }

    public static EdsSaveTo fromValue(String value) {
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
