package cameraweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeviceInitController {

    @Autowired
    public DeviceInitController() {
    }


    @GetMapping()
    public String startPage() {
        return "start";
    }

    @GetMapping("/session")
    public String sessionPage() {
        return "session";
    }


}
