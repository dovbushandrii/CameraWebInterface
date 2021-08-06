package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsMeteringMode implements CameraProp {

    SPOT            (1),
    EVALUATIVE      (3),
    PARTIAL         (4),
    CW_AVERAGING    (5),
    NOT_VALID       (-1);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsMeteringMode(int code) {
        this.code = code;
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
        return null;
    }
}
