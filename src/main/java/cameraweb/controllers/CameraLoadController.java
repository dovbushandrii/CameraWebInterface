package cameraweb.controllers;

import camera_api.ProxyCameraFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class CameraLoadController {
    private final ProxyCameraFactory camFac;

    @Autowired
    public CameraLoadController(ProxyCameraFactory camFac){
        this.camFac = camFac;
    }

    @GetMapping("/load")
    public void loadCamera(@RequestParam(value = "cameraID") int id){
        this.camFac.loadCamera(id);
    }
}
