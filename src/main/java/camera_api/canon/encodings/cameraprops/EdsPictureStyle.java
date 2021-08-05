package camera_api.canon.encodings.cameraprops;
/*
 * This property is valid only for models supporting picture styles.
 */

import camera_api.CameraProp;

public enum EdsPictureStyle implements CameraProp {

    STANDARD        (0x0081),
    PORTRAIT        (0x0082),
    LANDSCAPE       (0x0083),
    NEUTRAL         (0x0084),
    FAITHFUL        (0x0085),
    MONOCHROME      (0x0086),
    AUTO            (0x0087),
    FINE_DETAIL     (0x0088),
    COMPUTER_1      (0x0041),
    COMPUTER_2      (0x0042),
    COMPUTER_3      (0x0043),
    USER_1          (0x0021),
    USER_2          (0x0022),
    USER_3          (0x0023);


    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsPictureStyle(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsPictureStyle fromCode(int code) {
        for (EdsPictureStyle type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
