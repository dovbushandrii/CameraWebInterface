package cameraweb.controllers;

import cameraweb.model.CameraSettings;
import cameraweb.modelDAO.CameraSettingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/session/settings")
public class CameraSettingsController {
    private final CameraSettingsDAO camDAO;

    @Autowired
    public CameraSettingsController(CameraSettingsDAO camDAO) {
        this.camDAO = camDAO;
    }

    @GetMapping()
    public CameraSettings getData() {
        return camDAO.read();
    }

    //TODO: JSON parsing
    @PostMapping()
    public void update() {
        //this.camDAO.update(settings);
    }
}
