import camera_api.*;
import camera_api.canon.CanonSDK;

import java.util.concurrent.TimeUnit;

public class Main {
    static{
        System.loadLibrary("EDSDK");
        System.loadLibrary("CameraForJava");
    }
    public static void main(String[] args){
        //TimeUnit.SECONDS.sleep(0);
        CameraFactory fac = new CameraFactory(new CanonSDK());
        System.out.println(fac.initializeAll());
        if(fac.getDeviceCount() > 0){
            Camera cam = fac.getCamera(0);
            if(cam != null){
                System.out.println(cam.openSession());
                System.out.println(cam.autoFocus());
                System.out.println(cam.takePicture(1));
            }
        }
        System.out.println(fac.terminateAll());
    }
}
