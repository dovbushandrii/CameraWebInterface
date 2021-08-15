package cameraweb.model;

import camera_api.interfaces.Camera;

public class DeviceInfo {
    private String deviceName;
    private String ownersName;
    private String firmwareVersion;
    private String lensName;

    public DeviceInfo(Camera cam){
        this.deviceName         = cam.productName();
        this.ownersName         = cam.getOwnerName();
        this.firmwareVersion    = cam.firmwareVersion();
        this.lensName           = cam.lensName();
    }

    public String getDeviceName()       {return this.deviceName;}
    public String getOwnersName()       {return this.ownersName;}
    public String getFirmwareVersion()  {return this.firmwareVersion;}
    public String getLensName()         {return this.lensName;}
}
