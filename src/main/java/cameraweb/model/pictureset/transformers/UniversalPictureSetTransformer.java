package cameraweb.model.pictureset.transformers;

import cameraweb.model.pictureset.canonEncodings.CanonPictureSet;
import cameraweb.model.pictureset.dbobjects.PictureSetForDB;
import cameraweb.model.pictureset.inter.PictureSet;
import cameraweb.model.pictureset.inter.PictureSetTransformer;
import org.springframework.stereotype.Component;

@Component
public class UniversalPictureSetTransformer implements PictureSetTransformer {

    @Override
    public PictureSetForDB transformToDBO(PictureSet set) {
        PictureSetForDB dbo = new PictureSetForDB();
        dbo.setEncodingsType(set.getClass().getName());
        dbo.setCount(set.getCount());
        dbo.setExposureValue(set.getExposure().toString());
        dbo.setExposureTime(set.getExposureTime());
        dbo.setIsoValue(set.getIso().toString());
        dbo.setApertureValue(set.getAperture().toString());
        dbo.setPictureName(set.getPictureName());
        dbo.setPause(set.getPause());
        return dbo;
    }

    @Override
    public PictureSet transformFromDBO(PictureSetForDB dbo) {
        try {
            Class picSetClass = Class.forName(dbo.getEncodingsType());
            PictureSet set = (PictureSet) picSetClass.newInstance();
            set.setId(dbo.getId());
            set.setCount(dbo.getCount());
            set.setExposure(dbo.getExposureValue());
            set.setExposureTime(dbo.getExposureTime());
            set.setIso(dbo.getIsoValue());
            set.setAperture(dbo.getApertureValue());
            set.setPictureName(dbo.getPictureName());
            set.setPause(dbo.getPause());
            return set;
        } catch (Exception e) {
            throw new IllegalArgumentException("Picture set class is not existing:" + dbo.getEncodingsType());
        }
    }
}
