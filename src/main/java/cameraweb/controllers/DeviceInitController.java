package cameraweb.controllers;

import camera_api.CameraFactory;
import camera_api.interfaces.Camera;
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
    PhotoSessionController photoSesControl;

    @Autowired
    public DeviceInitController(CamSettingsController camSetControl,
                                DeviceInfoController devInfoControl,
                                PictureSetController  picSetControl,
                                PhotoSessionController photoSesControl){

        this.camSetControl      = camSetControl;
        this.devInfoControl     = devInfoControl;
        this.picSetControl      = picSetControl;
        this.photoSesControl    = photoSesControl;
    }


    @GetMapping()
    public String startPage(){
        return "start";
    }

    @GetMapping("/session")
    public String sessionPage(){
        return "session";
    }

    @GetMapping("/load/{id}")
    public String loadData(@PathVariable("id") int id, Model model){
        try {
            //this.camSetControl.load(model, id);
            //this.devInfoControl.load(model, id);
            this.photoSesControl.load(id);
            //this.picSetControl.load(model);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Cannot find camera with id: " + String.valueOf(id));
        }
        return "redirect:/session";
    }


}
