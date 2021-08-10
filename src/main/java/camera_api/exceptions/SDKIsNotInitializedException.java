package camera_api.exceptions;

public class SDKIsNotInitializedException extends RuntimeException{
    public SDKIsNotInitializedException(String str){
        super(str);
    }
}
