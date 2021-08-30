package camera_api.exceptions;

public class CameraSessionCloseException extends RuntimeException {
    public CameraSessionCloseException(String str) {
        super(str);
    }
    public CameraSessionCloseException() {
        super();
    }
}
