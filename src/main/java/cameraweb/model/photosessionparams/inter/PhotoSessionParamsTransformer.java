package cameraweb.model.photosessionparams.inter;

import cameraweb.model.photosessionparams.dtos.PhotoSessionParamsDTO;

public interface PhotoSessionParamsTransformer {
    PhotoSessionParamsDTO transformToDBO(PhotoSessionParams set);

    PhotoSessionParams transformFromDBO(PhotoSessionParamsDTO dbo);
}
