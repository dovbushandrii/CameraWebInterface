import camera_api.*;
import camera_api.canon.encodings.cameraprops.EdsExposure;
import camera_api.interfaces.Camera;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

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
                cam.setExposure(EdsExposure.SEC_1_4000);

                for (int i = 0; i < 50; i++) {
                    cam.takePicture();
                    System.out.println(i);

                }
                /* YOUR CODE ENDS HERE */
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        context.close();

    }
}
