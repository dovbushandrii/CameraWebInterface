package webapp.modelDAO;

import camera_api.ProxyCamera;
import webapp.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoDAO {

    private final ProxyCamera camera;

    @Autowired
    public DeviceInfoDAO(ProxyCamera camera) {
        this.camera = camera;
    }

    public DeviceInfo read() {
        return new DeviceInfo(this.camera.getCamera());
    }
}
