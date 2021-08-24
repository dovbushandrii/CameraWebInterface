package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsMeteringMode implements CameraProp {

    SPOT(1, "Spot metering"),
    EVALUATIVE(3, "Evaluative metering"),
    PARTIAL(4, "Partial metering"),
    CW_AVERAGING(5, "Center-weighted averaging metering");
    //NOT_VALID       (-1,"Not valid/no settings changes");

    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsMeteringMode(int code, String line) {
        this.code = code;
        this.line = line;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsMeteringMode fromCode(int code) {
        for (EdsMeteringMode type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    @Override
    public String toString() {
        return this.line;
    }
}
