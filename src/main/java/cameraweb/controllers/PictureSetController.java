package cameraweb.controllers;

import cameraweb.modelDAO.PictureSetDAO;
import cameraweb.model.pictureset.inter.PictureSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sets")
public class PictureSetController {
    private final PictureSetDAO picDAO;

    @Autowired
    public PictureSetController(PictureSetDAO picDAO) {
        this.picDAO = picDAO;
    }

    @GetMapping()
    public List<PictureSet> index(Model model) {
        return picDAO.read();
    }

    @PostMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.picDAO.delete(id);
    }
}
