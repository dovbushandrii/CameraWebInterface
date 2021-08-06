package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsISO implements CameraProp {
    ISO_AUTO	(0x00000000),
    ISO_6		(0x00000028),
    ISO_12		(0x00000030),
    ISO_25		(0x00000038),
    ISO_50		(0x00000040),
    ISO_100		(0x00000048),
    ISO_125		(0x0000004b),
    ISO_160		(0x0000004d),
    ISO_200		(0x00000050),
    ISO_250		(0x00000053),
    ISO_320		(0x00000055),
    ISO_400		(0x00000058),
    ISO_500		(0x0000005b),
    ISO_640		(0x0000005d),
    ISO_800		(0x00000060),
    ISO_1000	(0x00000063),
    ISO_1250	(0x00000065),
    ISO_1600	(0x00000068),
    ISO_2000	(0x0000006b),
    ISO_2500	(0x0000006d),
    ISO_3200	(0x00000070),
    ISO_4000	(0x00000073),
    ISO_5000	(0x00000075),
    ISO_6400	(0x00000078),
    ISO_8000	(0x0000007b),
    ISO_10000	(0x0000007d),
    ISO_12800	(0x00000080),
    ISO_16000	(0x00000083),
    ISO_20000	(0x00000085),
    ISO_25600	(0x00000088),
    ISO_32000	(0x0000008b),
    ISO_40000	(0x0000008d),
    ISO_51200	(0x00000090),
    ISO_64000	(0x00000093),
    ISO_80000	(0x00000095),
    ISO_102400	(0x00000098),
    ISO_204800	(0x000000a0),
    ISO_409600	(0x000000a8),
    ISO_819200	(0x000000b0),
    NOT_VALID	(-1);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of ISO setting
     */
    EdsISO(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsISO fromCode(int code) {
        for (EdsISO type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
