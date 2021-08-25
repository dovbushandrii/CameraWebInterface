package cameraweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/session")
public class PhotoSessionController {

    private PhotoSessionService service;

    @Autowired
    public PhotoSessionController(PhotoSessionService service) {
        this.service = service;
    }

    //TODO: JSON parsing

    @GetMapping("/start")
    public void startSession() {
        //service.startSession();
    }

}
