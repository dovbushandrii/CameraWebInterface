package cameraweb.modelDAO;

import camera_api.ProxyCameraFactory;
import cameraweb.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoDAO {

    private final ProxyCameraFactory camFac;

    @Autowired
    public DeviceInfoDAO(ProxyCameraFactory camFac) {
        this.camFac = camFac;
    }

    public DeviceInfo read() {
        return new DeviceInfo(this.camFac.getCamera());
    }
}
