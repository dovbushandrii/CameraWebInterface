package cameraweb.controllers;

import cameraweb.model.CamSettings;
import cameraweb.modelDAO.CamSettingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class CamSettingsController {
    private final CamSettingsDAO camDAO;

    @Autowired
    public CamSettingsController(CamSettingsDAO camDAO) {
        this.camDAO = camDAO;
    }

    @PostMapping()
    public void update(@ModelAttribute("camSettings") CamSettings settings) {
        this.camDAO.update(settings);
    }
}
