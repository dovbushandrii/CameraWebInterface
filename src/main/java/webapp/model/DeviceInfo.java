package webapp.model;

import camera_api.interfaces.camerasdk.Camera;
import lombok.Getter;

public class DeviceInfo {
    @Getter
    private final String deviceName;

    @Getter
    private final String ownersName;

    @Getter
    private final String firmwareVersion;

    @Getter
    private final String lensName;

    public DeviceInfo(Camera cam) {
        this.deviceName = cam.productName();
        this.ownersName = cam.getOwnerName();
        this.firmwareVersion = cam.firmwareVersion();
        this.lensName = cam.lensName();
    }
}
