package cameraweb.controllers;

import cameraweb.modelDAO.DeviceInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* TODO: Decide if it is useful
* */

@Controller
@RequestMapping("/info")
public class DeviceInfoController {
    private final DeviceInfoDAO infoDAO;

    @Autowired
    public DeviceInfoController(DeviceInfoDAO infoDAO){
        this.infoDAO = infoDAO;
    }

    public void load(Model model, int id){
        this.infoDAO.setCamera(id);
        model.addAttribute("devInfo",this.infoDAO.read());
    }
}
