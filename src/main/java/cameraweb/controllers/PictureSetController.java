package cameraweb.controllers;

import cameraweb.model.pictureset.dbobjects.PictureSetForDB;
import cameraweb.modelDAO.PictureSetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session/sets")
public class PictureSetController {
    private final PictureSetDAO picDAO;

    @Autowired
    public PictureSetController(PictureSetDAO picDAO) {
        this.picDAO = picDAO;
    }

    @GetMapping()
    public List<PictureSetForDB> index(Model model) {
        return picDAO.read();
    }

    //TODO: create PictureSet

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.picDAO.delete(id);
    }

    @DeleteMapping ()
    public void delete() {
        this.picDAO.delete();
    }
}
