package cameraweb.model.pictureset.inter;

import cameraweb.model.pictureset.dtos.PictureSetDTO;

public interface PictureSetTransformer {
    PictureSetDTO transformToDBO(PictureSet set);

    PictureSet transformFromDBO(PictureSetDTO dbo);
}
