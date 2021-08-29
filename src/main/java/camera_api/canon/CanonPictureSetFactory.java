package camera_api.canon;

import camera_api.canon.encodings.picturesets.CanonPictureSet;
import camera_api.interfaces.companies.PictureSetFactory;
import cameraweb.model.pictureset.inter.PictureSet;
import org.springframework.stereotype.Component;

@Component
public class CanonPictureSetFactory implements PictureSetFactory {

    @Override
    public PictureSet createPictureSet() {
        return new CanonPictureSet();
    }

    @Override
    public String getPictureSetClassName() {
        return CanonPictureSet.class.getName();
    }
}
