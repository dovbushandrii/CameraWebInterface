package webapp.modelDAO;

import camera_api.CameraLoader;
import webapp.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoDAO {

    private final CameraLoader camera;

    @Autowired
    public DeviceInfoDAO(CameraLoader camera) {
        this.camera = camera;
    }

    public DeviceInfo read() {
        return new DeviceInfo(this.camera.getCamera());
    }
}
