package camera_api.canon.encodings.cameraprops;

import camera_api.interfaces.CameraProp;

public enum EdsAEMode implements CameraProp {

    PROGRAM                 (0),
    SHUTTER_SPEED_PRIORITY  (1),
    APERTURE_PRIORITY       (2),
    MANUAL_EXPOSURE         (3),
    BULB                    (4),
    AUTO_DoF                (5),
    DEPTH_OF_FIELD          (6),
    CAM_SETTINGS_REG        (7),
    LOCK                    (8),
    AUTO                    (9),
    NIGHT_SCENE_PORTRAIT    (10),
    SPORTS                  (11),
    PORTRAIT                (12),
    LANDSCAPE               (13),
    CLOSE_UP                (14),
    FLASH_OFF               (15),
    CREATIVE_AUTO           (19),
    MOVIES                  (20),
    SCENE_INTEL_AUTO        (22),
    NIGHT_SCENES            (23),
    BACKLIT_SCENES          (24),
    KIDS                    (26),
    FOOD                    (27),
    CANDLELIGHT             (28),
    GRAINY_BW               (30),
    SOFT_FOCUS              (31),
    TOY_CAMERA              (32),
    FISH_EYE                (33),
    WATER_PAINING           (34),
    MINIATURE               (35);

    private final int code;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsAEMode(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsAEMode fromCode(int code) {
        for (EdsAEMode type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
