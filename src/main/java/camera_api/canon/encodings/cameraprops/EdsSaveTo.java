package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsSaveTo implements CameraProp {

    MEM_CARD        (1),
    HOST_COMPUTER   (2),
    BOTH            (3);

    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsSaveTo(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsSaveTo fromCode(int code) {
        for (EdsSaveTo type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
