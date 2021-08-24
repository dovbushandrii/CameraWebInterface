package cameraweb.model;

import camera_api.interfaces.Camera;
import lombok.Getter;

public class DeviceInfo {
    @Getter
    private String deviceName;

    @Getter
    private String ownersName;

    @Getter
    private String firmwareVersion;

    @Getter
    private String lensName;

    public DeviceInfo(Camera cam) {
        this.deviceName = cam.productName();
        this.ownersName = cam.getOwnerName();
        this.firmwareVersion = cam.firmwareVersion();
        this.lensName = cam.lensName();
    }
}
