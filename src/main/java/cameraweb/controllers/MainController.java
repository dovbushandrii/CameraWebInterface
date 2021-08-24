package cameraweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping()
    public String startPage() {
        return "start";
    }

    @GetMapping("/session")
    public String sessionPage() {
        return "session/sessionPage";
    }
}
