package camera_api.exceptions;

public class CameraNotFoundException extends RuntimeException {
    public CameraNotFoundException(String str) {
        super(str);
    }
}