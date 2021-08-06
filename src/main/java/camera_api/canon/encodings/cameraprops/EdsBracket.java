package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsBracket implements CameraProp {

    AE              (0x01),
    ISO             (0x02),
    WB              (0x04),
    FE              (0x08),
    AE_ISO          (0x03),
    AE_WB           (0x05),
    AE_FE           (0x09),
    ISO_WB          (0x06),
    ISO_FE          (0x0A),
    WB_FE           (0x0C),
    AE_ISO_WB       (0x07),
    AE_ISO_FE       (0x0B),
    AE_WB_FE        (0x0D),
    ISO_WB_FE       (0x0E),
    AE_ISO_WB_FE    (0x0F);


    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsBracket(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsBracket fromCode(int code) {
        for (EdsBracket type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
