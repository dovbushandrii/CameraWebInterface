package cameraweb.controllers;

import cameraweb.modelDAO.PictureSetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sets")
public class PictureSetController {
    private final PictureSetDAO picDAO;

    @Autowired
    public PictureSetController(PictureSetDAO picDAO){
        this.picDAO = picDAO;
    }

    public void load(Model model){
        model.addAttribute("picSets",picDAO.read());
    }
}
