package webapp.controllers;

import webapp.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import webapp.model.photosessionparams.inter.PhotoSessionParams;
import webapp.model.photosessionparams.inter.PhotoSessionParamsTransformer;
import webapp.modelDAO.PhotoSessionCAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class PhotoSessionService {
    PhotoSessionCAO photoSessionCAO;
    PhotoSessionParamsTransformer transformer;

    @Autowired
    public PhotoSessionService(PhotoSessionCAO photoSessionCAO,
                               PhotoSessionParamsTransformer transformer) {
        this.photoSessionCAO = photoSessionCAO;
        this.transformer = transformer;
    }

    public void startSession(List<PhotoSessionParamsDTO> params) {
        List<PhotoSessionParams> newSets = new ArrayList<>();
        for (PhotoSessionParamsDTO param : params) {
            newSets.add(transformer.transformFromDBO(param));
        }
        photoSessionCAO.startSession(newSets);
    }

}
