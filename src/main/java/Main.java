import camera_api.*;
import camera_api.interfaces.Camera;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static{
        System.loadLibrary("EDSDK");
        System.loadLibrary("CameraForJava");
    }
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                CamFacConfig.class
        );

        CameraFactory fac = context.getBean("cameraFactory", CameraFactory.class);

        if(fac.getDeviceCount() > 0) {
            Camera cam = fac.getCamera(0);
            cam.openSession();
            cam.autoFocus();
            cam.takePicture(1);
        }

        context.close();
    }
}
