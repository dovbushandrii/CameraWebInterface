package webapp.controllers;

import camera_api.CameraLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class CameraLoadController {
    private final CameraLoader cameraLoader;

    @Autowired
    public CameraLoadController(CameraLoader cameraLoader) {
        this.cameraLoader = cameraLoader;
    }

    @GetMapping("/load")
    public void loadCamera(@RequestParam(value = "cameraID") int id) {
        this.cameraLoader.loadCamera(id);
    }
}
