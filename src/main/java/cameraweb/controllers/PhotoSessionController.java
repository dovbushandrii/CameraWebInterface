package cameraweb.controllers;


import cameraweb.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/session")
public class PhotoSessionController {

    private PhotoSessionService photoSessionService;

    @Autowired
    public PhotoSessionController(PhotoSessionService service) {
        this.photoSessionService = service;
    }

    @PostMapping("/start")
    public void startSession(@RequestParam List<PhotoSessionParamsDTO> sets) {
        photoSessionService.startSession(sets);
    }
}
