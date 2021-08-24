package cameraweb.controllers;

import cameraweb.model.CameraSettings;
import cameraweb.modelDAO.CameraSettingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class CameraSettingsController {
    private final CameraSettingsDAO camDAO;

    @Autowired
    public CameraSettingsController(CameraSettingsDAO camDAO) {
        this.camDAO = camDAO;
    }

    @PostMapping()
    public void update(@ModelAttribute("camSettings") CameraSettings settings) {
        this.camDAO.update(settings);
    }
}
