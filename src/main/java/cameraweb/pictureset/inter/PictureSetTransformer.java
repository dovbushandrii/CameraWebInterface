package cameraweb.pictureset.inter;

import cameraweb.pictureset.dbobjects.PictureSetForDB;

public interface PictureSetTransformer {
    PictureSetForDB transformToDBO(PictureSet set);
    PictureSet transformFromDBO(PictureSetForDB dbo);
}
