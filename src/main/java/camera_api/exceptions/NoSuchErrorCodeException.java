package camera_api.exceptions;

public class NoSuchErrorCodeException extends RuntimeException{
    public NoSuchErrorCodeException(String str){
        super(str);
    }
}
