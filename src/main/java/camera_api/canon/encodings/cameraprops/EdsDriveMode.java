package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

public enum EdsDriveMode implements CameraProp {

    SINGLE_SHOOT            (0x00000000,"Single shooting"),
    CONTIN_SHOOT            (0x00000001,"Continuous shooting"),
    VIDEO                   (0x00000002,"Video"),
    HIGH_SPD_CONTIN         (0x00000004,"High speed continuous"),
    LOW_SPD_CONTIN          (0x00000005,"Low speed continuous"),
    SINGLE_SILENT_SHOOT     (0x00000006,"Single Silent shooting"),
    SELF_TIMER_CONTIN       (0x00000007,"Self-timer: Continuous"),
    SELF_TIMER_10_SEC       (0x00000010,"Self-timer: 10 sec"),
    SELF_TIMER_2_SEC        (0x00000011,"Self-timer: 2 sec"),
    FPS_14_SUPER_HIGH       (0x00000012,"14fps super high speed"),
    SILENT_SINGLE_SHOOT     (0x00000013,"Silent single shooting"),
    SILENT_CONTIN_SHOOT     (0x00000014,"Silent contin shooting"),
    SILENT_HS_CONTIN        (0x00000015,"Silent HS continuous"),
    SILENT_LS_CONTIN        (0x00000016,"Silent LS continuous");


    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsDriveMode(int code, String line) {
        this.code = code;
        this.line = line;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsDriveMode fromCode(int code) throws NoSuchPropertyValueException{
        for (EdsDriveMode type : values()) {
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
