package webapp.controllers;

import camera_api.ProxyCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class CameraLoadController {
    private final ProxyCamera camera;

    @Autowired
    public CameraLoadController(ProxyCamera camera) {
        this.camera = camera;
    }

    @GetMapping("/load")
    public void loadCamera(@RequestParam(value = "cameraID") int id) {
        this.camera.loadCamera(id);
    }
}
