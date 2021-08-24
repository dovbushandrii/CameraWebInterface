package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;


public enum EdsAEMode implements CameraProp {

    PROGRAM(0, "Program AE"),
    SHUTTER_SPEED_PRIORITY(1, "Shutter-Speed Priority AE"),
    APERTURE_PRIORITY(2, "Aperture Priority AE"),
    MANUAL_EXPOSURE(3, "Manual Exposure"),
    BULB(4, "Bulb"),
    AUTO_DoF(5, "Auto Depth-of-Field AE"),
    DEPTH_OF_FIELD(6, "Depth-of-Field AE"),
    CAM_SETTINGS_REG(7, "Camera settings registered"),
    LOCK(8, "Lock"),
    AUTO(9, "Auto"),
    NIGHT_SCENE_PORTRAIT(10, "Night Scene Portrait"),
    SPORTS(11, "Sports"),
    PORTRAIT(12, "Portrait"),
    LANDSCAPE(13, "Landscape"),
    CLOSE_UP(14, "Close-up"),
    FLASH_OFF(15, "Flash Off"),
    CREATIVE_AUTO(19, "Creative Auto"),
    MOVIES(20, "Movies"),
    PHOTO_IN_MOVIE(21, "Photo In Movie"),
    SCENE_INTEL_AUTO(22, "Scene Intelligent Auto"),
    NIGHT_SCENES(23, "Night Scenes"),
    BACKLIT_SCENES(24, "Backlit Scenes"),
    KIDS(26, "Kids"),
    FOOD(27, "Food"),
    CANDLELIGHT(28, "Candlelight"),
    GRAINY_BW(30, "Grainy B/W"),
    SOFT_FOCUS(31, "Soft focus"),
    TOY_CAMERA(32, "Toy camera effect"),
    FISH_EYE(33, "Fish-eye effect"),
    WATER_PAINING(34, "Water painting effect"),
    MINIATURE(35, "Miniature effect");

    private final int code;
    private final String value;

    /**
     * Constructor to initialize the instance variable
     *
     * @param code Code of aperture setting
     */
    EdsAEMode(int code, String value) {
        this.code = code;
        this.value = value;
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
        throw new NoSuchPropertyValueException("Invalid code/camera session is not opened");
    }

    @Override
    public String toString() {
        return this.value;
    }
}
