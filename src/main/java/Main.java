import camera_api.*;
import camera_api.canon.*;

import java.util.concurrent.TimeUnit;

public class Main {
    static{
        System.loadLibrary("EDSDK");
        System.loadLibrary("CameraForJava");
    }
    public static void main(String[] args){
        try {
            ErrorCode code = CanonSDK.initializeSDK();

            Camera cam = CanonSDK.getCamera(0);
            //TimeUnit.SECONDS.sleep(2);

            if(cam != null) {
                cam.openSession();
                //TimeUnit.SECONDS.sleep(0);

            }
            CanonSDK.terminateSDK();
        }
        catch (Throwable t){
            System.out.println(t.getMessage());
            CanonSDK.terminateSDK();
        }
    }
}
