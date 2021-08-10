package camera_api.canon.encodings.cameraprops;

import camera_api.exceptions.NoSuchPropertyValueException;
import camera_api.interfaces.CameraProp;

import java.rmi.NoSuchObjectException;

public enum EdsSaveTo implements CameraProp {

    MEM_CARD        (1,"Save on a memory card"),
    HOST_COMPUTER   (2,"Save on a host computer"),
    BOTH            (3,"Save both way");

    private final int code;
    private final String line;

    /**
     * Constructor to initialize the instance variable
     * @param code Code of aperture setting
     */
    EdsSaveTo(int code, String line) {
        this.code = code;
        this.line = line;
    }
    public int getCode() {
        return this.code;
    }

    public static EdsSaveTo fromCode(int code) throws NoSuchPropertyValueException{
        for (EdsSaveTo type : values()) {
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
