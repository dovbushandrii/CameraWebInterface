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
        try {
            CameraFactory fac = context.getBean("cameraFactory", CameraFactory.class);


            if (fac.getDeviceCount() > 0) {
                Camera cam = fac.getCamera(0);
                cam.openSession();
                /* YOUR CODE STARTS HERE*/
                cam.getExposure();
                /* YOUR CODE ENDS HERE */
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        context.close();

    }
}
