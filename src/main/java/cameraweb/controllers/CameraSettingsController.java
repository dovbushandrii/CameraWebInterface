package cameraweb.controllers;

import cameraweb.model.CameraSettings;
import cameraweb.modelDAO.CameraSettingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PatchMapping()
    public void update(@RequestParam CameraSettings settings) {
        this.camDAO.update(settings);
    }
}
