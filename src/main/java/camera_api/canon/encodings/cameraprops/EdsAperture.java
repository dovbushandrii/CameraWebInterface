package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsAperture implements CameraProp {

    /* Note: Values labeled "STEP" represent
     * property values when the step set
     * in the Custom Function is 1/3.
     */
    F_1			(0x08,"F1"),
    F_1p1		(0x0B,"F1.1"),
    F_1p2		(0x0C,"F1.2"),
    F_1p2_STEP	(0x0D,"F1.2"),
    F_1p4		(0x10,"F1.4"),
    F_1p6		(0x13,"F1.6"),
    F_1p8		(0x14,"F1.8"),
    F_1p8_STEP	(0x15,"F1.8"),
    F_2			(0x18,"F2"),
    F_2p2		(0x1B,"F2.2"),
    F_2p5		(0x1C,"F2.5"),
    F_2p5_STEP	(0x1D,"F2.5"),
    F_2p8		(0x20,"F2.8"),
    F_3p2		(0x23,"F3.2"),
    F_3p4		(0x85,"F3.4"),
    F_3p5		(0x24,"F3.5"),
    F_3p5_STEP	(0x25,"F3.5"),
    F_4			(0x28,"F4"),
    F_4p5		(0x2B,"F4.5"),
    F_4p5_v2    (0x2C,"F4.5"),
    F_5		    (0x2D,"F5"),
    F_5p6		(0x30,"F5.6"),
    F_6p3		(0x33,"F6.3"),
    F_6p7		(0x34,"F6.7"),
    F_7p1		(0x35,"F7.1"),
    F_8			(0x38,"F8"),
    F_9			(0x3B,"F9"),
    F_9p5		(0x3C,"F9.5"),
    F_10		(0x3D,"F10"),
    F_11		(0x40,"F11"),
    F_13_STEP	(0x43,"F13"),
    F_13		(0x44,"F13"),
    F_14		(0x45,"F14"),
    F_16		(0x48,"F16"),
    F_18		(0x4B,"F18"),
    F_19		(0x4C,"F19"),
    F_20		(0x4D,"F20"),
    F_22		(0x50,"F22"),
    F_25		(0x53,"F25"),
    F_27		(0x54,"F27"),
    F_29		(0x55,"F29"),
    F_32		(0x58,"F32"),
    F_36		(0x5B,"F36"),
    F_38		(0x5C,"F38"),
    F_40		(0x5D,"F40"),
    F_45		(0x60,"F45"),
    F_51		(0x63,"F51"),
    F_54		(0x64,"F54"),
    F_57		(0x65,"F57"),
    F_64		(0x68,"F64"),
    F_72		(0x6B,"F72"),
    F_76		(0x6C,"F76"),
    F_80		(0x6D,"F80"),
    F_91		(0x70,"F91"),
    NOT_VALID	(-1,"Not valid/no settings changes");

    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsAperture(int code, String line) {
        this.code = code;
        this.line = line;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsAperture fromCode(int code){
        for (EdsAperture type : values()) {
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
