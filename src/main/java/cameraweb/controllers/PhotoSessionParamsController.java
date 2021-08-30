package cameraweb.controllers;

import cameraweb.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import cameraweb.modelDAO.PhotoSessionParamsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session/sets")
public class PictureSetController {
    private final PhotoSessionParamsDAO picDAO;

    @Autowired
    public PictureSetController(PhotoSessionParamsDAO picDAO) {
        this.picDAO = picDAO;
    }

    @GetMapping()
    public List<PhotoSessionParamsDTO> index() {
        return picDAO.read();
    }

    @PostMapping()
    public void create(@RequestParam PhotoSessionParamsDTO picSet) {
        this.picDAO.create(picSet);
    }

    @PatchMapping()
    public void update(@RequestParam PhotoSessionParamsDTO picSet) {
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
