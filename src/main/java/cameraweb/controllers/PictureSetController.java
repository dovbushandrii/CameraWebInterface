package cameraweb.controllers;

import cameraweb.modelDAO.PictureSetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sets")
public class PictureSetController {
    private final PictureSetDAO picDAO;

    @Autowired
    public PictureSetController(PictureSetDAO picDAO){
        this.picDAO = picDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("pictureSets",picDAO.read());
        return "picturesetcontroller/index";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable int id){
        this.picDAO.delete(id);
        return "redirect:/sets";
    }

    public void load(Model model){
        model.addAttribute("picSets",picDAO.read());
    }
}
