package webapp.controllers;

import webapp.model.deviceInfo.DeviceInfo;
import webapp.modelDAO.DeviceInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/session/info")
public class DeviceInfoController {
    private final DeviceInfoDAO deviceInfoDAO;

    @Autowired
    public DeviceInfoController(DeviceInfoDAO deviceInfoDAO) {
        this.deviceInfoDAO = deviceInfoDAO;
    }

    @GetMapping()
    public DeviceInfo getData() {
        return deviceInfoDAO.read();
    }

}
