package cameraweb.modelDAO;

import camera_api.ProxyCamera;
import cameraweb.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoDAO {

    private final ProxyCamera camFac;

    @Autowired
    public DeviceInfoDAO(ProxyCamera camFac) {
        this.camFac = camFac;
    }

    public DeviceInfo read() {
        return new DeviceInfo(this.camFac.getCamera());
    }
}
