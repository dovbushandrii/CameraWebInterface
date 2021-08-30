package cameraweb.controllers;

import cameraweb.model.CameraSettings;
import cameraweb.modelDAO.CameraSettingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/session/settings")
public class CameraSettingsController {
    private final CameraSettingsDAO cameraSettingsDAO;

    @Autowired
    public CameraSettingsController(CameraSettingsDAO cameraSettingsDAO) {
        this.cameraSettingsDAO = cameraSettingsDAO;
    }

    @GetMapping()
    public CameraSettings getData() {
        return cameraSettingsDAO.read();
    }

    @PatchMapping()
    public void update(@RequestParam CameraSettings settings) {
        this.cameraSettingsDAO.update(settings);
    }
}
