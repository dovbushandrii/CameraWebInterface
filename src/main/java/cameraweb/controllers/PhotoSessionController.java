package cameraweb.controllers;


import cameraweb.model.pictureset.dtos.PictureSetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/session")
public class PhotoSessionController {

    private PhotoSessionService service;

    @Autowired
    public PhotoSessionController(PhotoSessionService service) {
        this.service = service;
    }

    @PostMapping("/start")
    public void startSession(@RequestParam List<PictureSetDTO> sets) {
        service.startSession(sets);
    }
}
