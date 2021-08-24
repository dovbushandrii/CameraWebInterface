package cameraweb.controllers;

import cameraweb.model.pictureset.inter.PictureSetTransformer;
import cameraweb.modelDAO.PhotoSessionCAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
@RequestMapping("/command")
public class PhotoSessionService {
    PhotoSessionCAO cao;
    PictureSetTransformer transformer;

    @Autowired
    public PhotoSessionService(PhotoSessionCAO cao,
                               PictureSetTransformer transformer) {
        this.cao = cao;
        this.transformer = transformer;
    }

    public void load(int id) {
        this.cao.setCamera(id);
    }
}
