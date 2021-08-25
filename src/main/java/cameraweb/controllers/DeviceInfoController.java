package cameraweb.controllers;

import cameraweb.model.DeviceInfo;
import cameraweb.modelDAO.DeviceInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/session/info")
public class DeviceInfoController {
    private final DeviceInfoDAO infoDAO;

    @Autowired
    public DeviceInfoController(DeviceInfoDAO infoDAO) {
        this.infoDAO = infoDAO;
    }

    @GetMapping()
    public DeviceInfo getData() {
        return infoDAO.read();
    }
    
}
