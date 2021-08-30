package webapp.model.photosessionparams.inter;

import webapp.model.photosessionparams.dtos.PhotoSessionParamsDTO;

public interface PhotoSessionParamsTransformer {
    PhotoSessionParamsDTO transformToDBO(PhotoSessionParams set);

    PhotoSessionParams transformFromDBO(PhotoSessionParamsDTO dbo);
}
