package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsExposureComp implements CameraProp {

    PLUS_5          (0x28,"+5"),
    PLUS_4_2d3      (0x25,"+4 2/3"),
    PLUS_4_1d2      (0x24,"+4 1/2"),
    PLUS_4_1d3      (0x23,"+4 1/3"),
    PLUS_4          (0x20,"+4"),
    PLUS_3_2d3      (0x1D,"+3 2/3"),
    PLUS_3_1d2      (0x1C,"+3 1/2"),
    PLUS_3_1d3      (0x1B,"+3 1/3"),
    PLUS_3          (0x18,"+3"),
    PLUS_2_2d3      (0x15,"+2 2/3"),
    PLUS_2_1d2      (0x14,"+2 1/2"),
    PLUS_2_1d3      (0x13,"+2 1/3"),
    PLUS_2          (0x10,"+2"),
    PLUS_1_2d3      (0x0D,"+1 2/3"),
    PLUS_1_1d2      (0x0C,"+1 1/2"),
    PLUS_1_1d3      (0x0B,"+1 1/3"),
    PLUS_1          (0x08,"+1"),
    PLUS_2d3        (0x05,"+2/3"),
    PLUS_1d2        (0x04,"+1/2"),
    PLUS_1d3        (0x03,"+1/3"),
    ZERO            (0x00,"0"),
    MINUS_1d3       (0xFD,"-1/3"),
    MINUS_1d2       (0xFC,"-1/2"),
    MINUS_2d3       (0xFB,"-2/3"),
    MINUS_1         (0xF8,"-1"),
    MINUS_1_1d3     (0xF5,"-1 1/3"),
    MINUS_1_1d2     (0xF4,"-1 1/2"),
    MINUS_1_2d3     (0xF3,"-1 2/3"),
    MINUS_2         (0xF0,"-2"),
    MINUS_2_1d3     (0xED,"-2 1/3"),
    MINUS_2_1d2     (0xEC,"-2 1/2"),
    MINUS_2_2d3     (0xEB,"-2 2/3"),
    MINUS_3         (0xE8,"-3"),
    MINUS_3_1d3     (0xE5,"-3 1/3"),
    MINUS_3_1d2     (0xE4,"-3 1/2"),
    MINUS_3_2d3     (0xE3,"-3 2/3"),
    MINUS_4         (0xE0,"-4"),
    MINUS_4_1d3     (0xDD,"-4 1/3"),
    MINUS_4_1d2     (0xDC,"-4 1/2"),
    MINUS_4_2d3     (0xDB,"-4 2/3"),
    MINUS_5         (0xD8,"-5"),
    NOT_VALID       (-1,"Not valid/no settings changes");



    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsExposureComp(int code, String line) {
        this.code = code;
        this.line = line;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsExposureComp fromCode(int code){
        for (EdsExposureComp type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    @Override
    public String toString(){
        return this.line;
    }
}
