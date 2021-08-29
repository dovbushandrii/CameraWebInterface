package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.encogings.CameraProp;

public enum EdsISO implements CameraProp {
    ISO_AUTO(0x00000000, "ISO Auto"),
    ISO_6(0x00000028, "ISO 6"),
    ISO_12(0x00000030, "ISO 12"),
    ISO_25(0x00000038, "ISO 25"),
    ISO_50(0x00000040, "ISO 50"),
    ISO_100(0x00000048, "ISO 100"),
    ISO_125(0x0000004b, "ISO 125"),
    ISO_160(0x0000004d, "ISO 160"),
    ISO_200(0x00000050, "ISO 200"),
    ISO_250(0x00000053, "ISO 250"),
    ISO_320(0x00000055, "ISO 320"),
    ISO_400(0x00000058, "ISO 400"),
    ISO_500(0x0000005b, "ISO 500"),
    ISO_640(0x0000005d, "ISO 640"),
    ISO_800(0x00000060, "ISO 800"),
    ISO_1000(0x00000063, "ISO 1000"),
    ISO_1250(0x00000065, "ISO 1250"),
    ISO_1600(0x00000068, "ISO 1600"),
    ISO_2000(0x0000006b, "ISO 2000"),
    ISO_2500(0x0000006d, "ISO 2500"),
    ISO_3200(0x00000070, "ISO 3200"),
    ISO_4000(0x00000073, "ISO 4000"),
    ISO_5000(0x00000075, "ISO 5000"),
    ISO_6400(0x00000078, "ISO 6400"),
    ISO_8000(0x0000007b, "ISO 8000"),
    ISO_10000(0x0000007d, "ISO 10000"),
    ISO_12800(0x00000080, "ISO 12800"),
    ISO_16000(0x00000083, "ISO 16000"),
    ISO_20000(0x00000085, "ISO 20000"),
    ISO_25600(0x00000088, "ISO 25600"),
    ISO_32000(0x0000008b, "ISO 32000"),
    ISO_40000(0x0000008d, "ISO 40000"),
    ISO_51200(0x00000090, "ISO 51200"),
    ISO_64000(0x00000093, "ISO 64000"),
    ISO_80000(0x00000095, "ISO 80000"),
    ISO_102400(0x00000098, "ISO 102400"),
    ISO_204800(0x000000a0, "ISO 204800"),
    ISO_409600(0x000000a8, "ISO 409600"),
    ISO_819200(0x000000b0, "ISO 819200");
    //NOT_VALID	(-1,"Not valid/no settings changes");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of ISO setting
     */
    EdsISO(int code, String value) {
        this.code = code;
        this.value = value;
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
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    public static EdsISO fromValue(String value) {
        for (EdsISO type : values()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new NoSuchPropertyValueException("Invalid settings value");
    }

    @Override
    public String toString() {
        return this.value;
    }
}
