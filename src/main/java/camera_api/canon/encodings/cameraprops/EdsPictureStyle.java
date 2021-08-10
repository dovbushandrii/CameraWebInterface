package camera_api.canon.encodings.cameraprops;
/*
 * This property is valid only for models supporting picture styles.
 */

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsPictureStyle implements CameraProp {

    /*
    * Computer settings (1 and so on) refers to data that was
    * set by designating a picture style file to upload to the
    * camera from a host computer. Computer setting data is
    * registered in the corresponding user setting. (For example,
    * computer setting 1 corresponds to user setting 1).
    * As a user setting, it represents a picture style that
    * users can select.
    * */

    /*
    * Picture styles registered in computer settings
    * always have a base picture style. As for picture styles
    * other than presets, only base picture styles can be
    * retrieved by means of this property value.
    * */

    STANDARD        (0x0081,"Standard"),
    PORTRAIT        (0x0082,"Portrait"),
    LANDSCAPE       (0x0083,"Landscape"),
    NEUTRAL         (0x0084,"Neutral"),
    FAITHFUL        (0x0085,"Faithful"),
    MONOCHROME      (0x0086,"Monochrome"),
    AUTO            (0x0087,"Auto"),
    FINE_DETAIL     (0x0088,"Fine Detail"),
    COMPUTER_1      (0x0041,"Computer Setting 1"),
    COMPUTER_2      (0x0042,"Computer Setting 2"),
    COMPUTER_3      (0x0043,"Computer Setting 3"),
    USER_1          (0x0021,"User Setting 1"),
    USER_2          (0x0022,"User Setting 2"),
    USER_3          (0x0023,"User Setting 3");


    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsPictureStyle(int code, String line) {
        this.code = code;
        this.line = line;
    }

    public int getCode() {
        return this.code;
    }

    public static EdsPictureStyle fromCode(int code) throws NoSuchPropertyValueException{
        for (EdsPictureStyle type : values()) {
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
