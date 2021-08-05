package camera_api.canon.encodings.cameraprops;

import camera_api.CameraProp;

public enum EdsExposureComp implements CameraProp {

    PLUS_5          (0x28),
    PLUS_4_2d3      (0x25),
    PLUS_4_1d2      (0x24),
    PLUS_4_1d3      (0x23),
    PLUS_4          (0x20),
    PLUS_3_2d3      (0x1D),
    PLUS_3_1d2      (0x1C),
    PLUS_3_1d3      (0x1B),
    PLUS_3          (0x18),
    PLUS_2_2d3      (0x15),
    PLUS_2_1d2      (0x14),
    PLUS_2_1d3      (0x13),
    PLUS_2          (0x10),
    PLUS_1_2d3      (0x0D),
    PLUS_1_1d2      (0x0C),
    PLUS_1_1d3      (0x0B),
    PLUS_1          (0x08),
    PLUS_2d3        (0x05),
    PLUS_1d2        (0x04),
    PLUS_1d3        (0x03),
    ZERO            (0x00),
    MINUS_1d3       (0xFD),
    MINUS_1d2       (0xFC),
    MINUS_2d3       (0xFB),
    MINUS_1         (0xF8),
    MINUS_1_1d3     (0xF5),
    MINUS_1_1d2     (0xF4),
    MINUS_1_2d3     (0xF3),
    MINUS_2         (0xF0),
    MINUS_2_1d3     (0xED),
    MINUS_2_1d2     (0xEC),
    MINUS_2_2d3     (0xEB),
    MINUS_3         (0xE8),
    MINUS_3_1d3     (0xE5),
    MINUS_3_1d2     (0xE4),
    MINUS_3_2d3     (0xE3),
    MINUS_4         (0xE0),
    MINUS_4_1d3     (0xDD),
    MINUS_4_1d2     (0xDC),
    MINUS_4_2d3     (0xDB),
    MINUS_5         (0xD8),
    NOT_VALID       (-1);



    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsExposureComp(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsExposureComp fromCode(int code) {
        for (EdsExposureComp type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
