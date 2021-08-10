package camera_api.exceptions;

public class NoSuchPropertyValueException extends RuntimeException{
    public NoSuchPropertyValueException(String str){
        super(str);
    }
}
