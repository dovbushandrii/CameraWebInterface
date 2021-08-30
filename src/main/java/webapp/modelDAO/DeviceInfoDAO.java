package webapp.modelDAO;

import camera_api.CameraLoader;
import webapp.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoDAO {

    private final CameraLoader cameraLoader;

    @Autowired
    public DeviceInfoDAO(CameraLoader cameraLoader) {
        this.cameraLoader = cameraLoader;
    }

    public DeviceInfo read() {
        return new DeviceInfo(this.cameraLoader.getCamera());
    }
}
