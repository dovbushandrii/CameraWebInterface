package cameraweb.controllers;

import cameraweb.model.pictureset.dtos.PictureSetDTO;
import cameraweb.model.pictureset.inter.PictureSet;
import cameraweb.model.pictureset.inter.PictureSetTransformer;
import cameraweb.modelDAO.PhotoSessionCAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class PhotoSessionService {
    PhotoSessionCAO cao;
    PictureSetTransformer transformer;

    @Autowired
    public PhotoSessionService(PhotoSessionCAO cao,
                               PictureSetTransformer transformer) {
        this.cao = cao;
        this.transformer = transformer;
    }

    public void startSession(List<PictureSetDTO> sets) {
        List<PictureSet> newSets = new ArrayList<>();
        for (PictureSetDTO set : sets) {
            newSets.add(transformer.transformFromDBO(set));
        }
        cao.startSession(newSets);
    }

}
