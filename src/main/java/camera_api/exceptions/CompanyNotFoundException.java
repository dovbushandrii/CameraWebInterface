package camera_api.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String str) {
        super(str);
    }
    public CompanyNotFoundException() {
        super();
    }
}
