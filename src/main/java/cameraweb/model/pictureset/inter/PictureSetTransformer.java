package cameraweb.model.pictureset.inter;

import cameraweb.model.pictureset.dbobjects.PictureSetForDB;

public interface PictureSetTransformer {
    PictureSetForDB transformToDBO(PictureSet set);

    PictureSet transformFromDBO(PictureSetForDB dbo);
}
