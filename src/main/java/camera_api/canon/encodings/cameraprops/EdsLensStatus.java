package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsLensStatus implements CameraProp {

    NOT_ATTACHED    (0,"The lens is not attached"),
    ATTACHED        (1,"The lens is attached");

    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsLensStatus(int code, String line) {
        this.code = code;
        this.line = line;
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

    @Override
    public String toString(){
        return this.line;
    }
}
