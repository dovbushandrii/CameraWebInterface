package camera_api.interfaces.companies;

import cameraweb.model.pictureset.inter.PictureSet;

public interface PictureSetFactory {
    PictureSet createPictureSet();
    String getPictureSetClassName();
}
