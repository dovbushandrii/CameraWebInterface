package camera_api.canon.encodings.cameraprops;

import camera_api.CameraProp;

public enum EdsBatteryQuality implements CameraProp {

    FULL        (3),
    HI          (2),
    HALF        (1),
    LOW         (0);


    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsBatteryQuality(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsBatteryQuality fromCode(int code) {
        for (EdsBatteryQuality type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
