package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsLiveViewSettings implements CameraProp {

    DISABLE         (0),
    ENABLE          (1);

    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsLiveViewSettings(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsLiveViewSettings fromCode(int code) {
        for (EdsLiveViewSettings type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
