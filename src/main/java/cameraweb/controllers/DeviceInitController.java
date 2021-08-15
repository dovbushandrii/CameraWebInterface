package cameraweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeviceInitController {
    CamSettingsController camSetControl;
    DeviceInfoController devInfoControl;
    PictureSetController  picSetControl;

    @Autowired
    public DeviceInitController(CamSettingsController camSetControl,
                                DeviceInfoController devInfoControl,
                                PictureSetController  picSetControl){

        this.camSetControl      = camSetControl;
        this.devInfoControl     = devInfoControl;
        this.picSetControl      = picSetControl;
    }

    @GetMapping("/session")
    public String sessionPage(){
        System.out.println("There");
        return "session";
    }

    @GetMapping("/load/{id}")
    public String loadData(@PathVariable("id") int id, Model model){
        this.camSetControl.load(model, id);
        this.devInfoControl.load(model, id);
        this.picSetControl.load(model);
        return "redirect:/session";
    }

    @GetMapping()
    public String startPage(){
        System.out.println("There");
        return "start";
    }
}
