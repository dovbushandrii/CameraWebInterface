package camera_api.exceptions;

public class NoCameraInstanceWasLoadedException extends RuntimeException {
    public NoCameraInstanceWasLoadedException(String str) {
        super(str);
    }
}