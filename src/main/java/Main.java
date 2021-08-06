import camera_api.*;
import camera_api.canon.CanonSDK;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class Main {
    static{
        System.loadLibrary("EDSDK");
        System.loadLibrary("CameraForJava");
    }
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "cameraFactory.xml"
        );

        CameraFactory fac = context.getBean("cameraFactory", CameraFactory.class);
        fac.initializeAll();
        if(fac.getDeviceCount() > 0) {
            Camera cam = fac.getCamera(0);
            cam.openSession();
            System.out.println(cam.takePicture(1));
        }
        fac.terminateAll();
    }
}
