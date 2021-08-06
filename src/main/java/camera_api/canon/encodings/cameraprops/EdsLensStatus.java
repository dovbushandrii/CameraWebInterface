package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsLensStatus implements CameraProp {

    NOT_ATTACHED    (0),
    ATTACHED        (1);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsLensStatus(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsLensStatus fromCode(int code) {
        for (EdsLensStatus type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
