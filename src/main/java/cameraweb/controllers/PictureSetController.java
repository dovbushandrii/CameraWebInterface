package cameraweb.controllers;

import cameraweb.model.pictureset.dtos.PictureSetDTO;
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
    public List<PictureSetDTO> index() {
        return picDAO.read();
    }

    @PostMapping()
    public void create(@RequestParam PictureSetDTO picSet) {
        this.picDAO.create(picSet);
    }

    @PatchMapping()
    public void update(@RequestParam PictureSetDTO picSet) {
        this.picDAO.update(picSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.picDAO.delete(id);
    }

    @DeleteMapping()
    public void delete() {
        this.picDAO.delete();
    }
}
