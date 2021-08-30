package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.encogings.CameraProp;

import java.util.Arrays;

public enum EdsExposure implements CameraProp {

    /* Note: Values labeled "STEP" represent
     * property values when the step set
     * in the Custom Function is 1/3.
     */
    BULB(0x0C, "Bulb"),
    SEC_30(0x10, "30\""),
    SEC_25(0x13, "25\""),
    SEC_20(0x14, "20\""),
    SEC_20_STEP(0x15, "20\""),
    SEC_15(0x18, "15\""),
    SEC_13(0x1B, "13\""),
    SEC_10(0x1C, "10\""),
    SEC_10_STEP(0x1D, "10\""),
    SEC_8(0x20, "8\""),
    SEC_6_STEP(0x23, "6\""),
    SEC_6(0x24, "6\""),
    SEC_5(0x25, "5\""),
    SEC_4(0x28, "4\""),
    SEC_3p2(0x2B, "3\"2"),
    SEC_3(0x2C, "3\""),
    SEC_2p5(0x2D, "2\"5"),
    SEC_2(0x30, "2\""),
    SEC_1p6(0x33, "1\"6"),
    SEC_1p5(0x34, "1\"5"),
    SEC_1p3(0x35, "1\"3"),
    SEC_1(0x38, "1\""),
    SEC_0p8(0x3B, "0\"8"),
    SEC_0p7(0x3C, "0\"7"),
    SEC_0p6(0x3D, "0\"6"),
    SEC_0p5(0x40, "0\"5"),
    SEC_0p4(0x43, "0\"4"),
    SEC_0p3(0x44, "0\"3"),
    SEC_0p3_STEP(0x45, "0\"3"),
    SEC_1_4(0x48, "1/4"),
    SEC_1_5(0x4B, "1/5"),
    SEC_1_6(0x4C, "1/6"),
    SEC_1_6_STEP(0x4D, "1/6"),
    SEC_1_8(0x50, "1/8"),
    SEC_1_10_STEP(0x53, "1/10"),
    SEC_1_10(0x54, "1/10"),
    SEC_1_13(0x55, "1/13"),
    SEC_1_15(0x58, "1/15"),
    SEC_1_20_STEP(0x5B, "1/20"),
    SEC_1_20(0x5C, "1/20"),
    SEC_1_25(0x5D, "1/25"),
    SEC_1_30(0x60, "1/30"),
    SEC_1_40(0x63, "1/40"),
    SEC_1_45(0x64, "1/45"),
    SEC_1_50(0x65, "1/50"),
    SEC_1_60(0x68, "1/60"),
    SEC_1_80(0x6B, "1/80"),
    SEC_1_90(0x6C, "1/90"),
    SEC_1_100(0x6D, "1/100"),
    SEC_1_125(0x70, "1/125"),
    SEC_1_160(0x73, "1/160"),
    SEC_1_180(0x74, "1/180"),
    SEC_1_200(0x75, "1/200"),
    SEC_1_250(0x78, "1/250"),
    SEC_1_320(0x7B, "1/320"),
    SEC_1_350(0x7C, "1/350"),
    SEC_1_400(0x7D, "1/400"),
    SEC_1_500(0x80, "1/500"),
    SEC_1_640(0x83, "1/640"),
    SEC_1_750(0x84, "1/750"),
    SEC_1_800(0x85, "1/800"),
    SEC_1_1000(0x88, "1/1000"),
    SEC_1_1250(0x8B, "1/1250"),
    SEC_1_1500(0x8C, "1/1500"),
    SEC_1_1600(0x8D, "1/1600"),
    SEC_1_2000(0x90, "1/2000"),
    SEC_1_2500(0x93, "1/2500"),
    SEC_1_3000(0x94, "1/3000"),
    SEC_1_3200(0x95, "1/3200"),
    SEC_1_4000(0x98, "1/4000"),
    SEC_1_5000(0x9B, "1/5000"),
    SEC_1_6000(0x9C, "1/6000"),
    SEC_1_6400(0x9D, "1/6400"),
    SEC_1_8000(0xA0, "1/8000");
    //NOT_VALID		(-1,"Not valid/no settings changes");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of exposure setting
     */
    EdsExposure(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return this.code;
    }

    public static CameraProp fromCode(int code) {
        return Arrays.stream(values())
                .filter(type -> type.getCode() == code)
                .findFirst()
                .orElseThrow(NoSuchPropertyValueException::new);
    }

    public static CameraProp fromValue(String value) {
        return Arrays.stream(values())
                .filter(type -> type.toString().equals(value))
                .findFirst()
                .orElseThrow(NoSuchPropertyValueException::new);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
