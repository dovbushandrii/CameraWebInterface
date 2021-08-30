package cameraweb.model.pictureset.inter;

import cameraweb.model.pictureset.dtos.PictureSetDTO;

public interface PhotoSessionParamsTransformer {
    PictureSetDTO transformToDBO(PhotoSessionParams set);

    PhotoSessionParams transformFromDBO(PictureSetDTO dbo);
}
