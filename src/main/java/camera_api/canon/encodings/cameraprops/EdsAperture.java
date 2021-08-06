package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsAperture implements CameraProp {

    F_1			(0x08),
    F_1p1		(0x0B),
    F_1p2		(0x0C),
    F_1p2_1_3	(0x0D),
    F_1p4		(0x10),
    F_1p6		(0x13),
    F_1p8		(0x14),
    F_1p8_1_3	(0x15),
    F_2			(0x18),
    F_2p2		(0x1B),
    F_2p5		(0x1C),
    F_2p5_1_3	(0x1D),
    F_2p8		(0x20),
    F_3p2		(0x23),
    F_3p4		(0x85),
    F_3p5		(0x24),
    F_3p5_1_3	(0x25),
    F_4			(0x28),
    F_4p5		(0x2B),
    F_4p5_v2    (0x2C),
    F_5p0		(0x2D),
    F_5p6		(0x30),
    F_6p3		(0x33),
    F_6p7		(0x34),
    F_7p1		(0x35),
    F_8			(0x38),
    F_9			(0x3B),
    F_9p5		(0x3C),
    F_10		(0x3D),
    F_11		(0x40),
    F_13_1_3	(0x43),
    F_13		(0x44),
    F_14		(0x45),
    F_16		(0x48),
    F_18		(0x4B),
    F_19		(0x4C),
    F_20		(0x4D),
    F_22		(0x50),
    F_25		(0x53),
    F_27		(0x54),
    F_29		(0x55),
    F_32		(0x58),
    F_36		(0x5B),
    F_38		(0x5C),
    F_40		(0x5D),
    F_45		(0x60),
    F_51		(0x63),
    F_54		(0x64),
    F_57		(0x65),
    F_64		(0x68),
    F_72		(0x6B),
    F_76		(0x6C),
    F_80		(0x6D),
    F_91		(0x70),
    NOT_VALID	(-1);

    private int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsAperture(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsAperture fromCode(int code) {
        for (EdsAperture type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
