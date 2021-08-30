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
    private final CameraLoader camera;

    @Autowired
    public CameraLoadController(CameraLoader camera) {
        this.camera = camera;
    }

    @GetMapping("/load")
    public void loadCamera(@RequestParam(value = "cameraID") int id) {
        this.camera.loadCamera(id);
    }
}
