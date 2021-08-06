package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsExposure implements CameraProp {
    BULB			(0x0C),
    SEC_30			(0x10),
    SEC_25			(0x13),
    SEC_20			(0x14),
    SEC_20_STEP		(0x15),
    SEC_15			(0x18),
    SEC_13			(0x1B),
    SEC_10			(0x1C),
    SEC_10_STEP		(0x1D),
    SEC_8			(0x20),
    SEC_6_STEP		(0x23),
    SEC_6			(0x24),
    SEC_5			(0x25),
    SEC_4			(0x28),
    SEC_3p2			(0x2B),
    SEC_3			(0x2C),
    SEC_2p5			(0x2D),
    SEC_2			(0x30),
    SEC_1p6			(0x33),
    SEC_1p5			(0x34),
    SEC_1p3			(0x35),
    SEC_1			(0x38),
    SEC_0p8			(0x3B),
    SEC_0p7			(0x3C),
    SEC_0p6			(0x3D),
    SEC_0p5			(0x40),
    SEC_0p4			(0x43),
    SEC_0p3			(0x44),
    SEC_0p3_STEP	(0x45),
    SEC_1_4			(0x48),
    SEC_1_5			(0x4B),
    SEC_1_6			(0x4C),
    SEC_1_6_STEP	(0x4D),
    SEC_1_8			(0x50),
    SEC_1_10_STEP	(0x53),
    SEC_1_10		(0x54),
    SEC_1_13		(0x55),
    SEC_1_15		(0x58),
    SEC_1_20_STEP	(0x5B),
    SEC_1_20		(0x5C),
    SEC_1_25		(0x5D),
    SEC_1_30		(0x60),
    SEC_1_40		(0x63),
    SEC_1_45		(0x64),
    SEC_1_50		(0x65),
    SEC_1_60		(0x68),
    SEC_1_80		(0x6B),
    SEC_1_90		(0x6C),
    SEC_1_100		(0x6D),
    SEC_1_125		(0x70),
    SEC_1_160		(0x73),
    SEC_1_180		(0x74),
    SEC_1_200		(0x75),
    SEC_1_250		(0x78),
    SEC_1_320		(0x7B),
    SEC_1_350		(0x7C),
    SEC_1_400		(0x7D),
    SEC_1_500		(0x80),
    SEC_1_640		(0x83),
    SEC_1_750		(0x84),
    SEC_1_800		(0x85),
    SEC_1_1000		(0x88),
    SEC_1_1250		(0x8B),
    SEC_1_1500		(0x8C),
    SEC_1_1600		(0x8D),
    SEC_1_2000		(0x90),
    SEC_1_2500		(0x93),
    SEC_1_3000		(0x94),
    SEC_1_3200		(0x95),
    SEC_1_4000		(0x98),
    SEC_1_5000		(0x9B),
    SEC_1_6000		(0x9C),
    SEC_1_6400		(0x9D),
    SEC_1_8000		(0xA0),
    NOT_VALID		(-1);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of exposure setting
     */
    EdsExposure(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsExposure fromCode(int code) {
        for (EdsExposure type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

}
