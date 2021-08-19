package cameraweb.controllers;

import cameraweb.modelDAO.PhotoSessionCAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/command")
public class PhotoSessionController {
    PhotoSessionCAO cao;

    @Autowired
    public PhotoSessionController(PhotoSessionCAO cao){
        this.cao = cao;
    }

    @GetMapping("/takePicture")
    public String takePicture(@RequestParam(name="time",defaultValue="-1.0") double time){
        if(time == -1.0){
            cao.takePicture();
        }
        else{
            cao.takePicture(time);
        }
        return "redirect:/session";
    }

    public void load(int id){
        this.cao.setCamera(id);
    }
}
