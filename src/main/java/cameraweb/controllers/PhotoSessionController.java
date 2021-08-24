package cameraweb.controllers;

import cameraweb.modelDAO.PhotoSessionCAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
@RequestMapping("/command")
public class PhotoSessionController {
    PhotoSessionCAO cao;

    @Autowired
    public PhotoSessionController(PhotoSessionCAO cao) {
        this.cao = cao;
    }

    public void load(int id) {
        this.cao.setCamera(id);
    }
}
