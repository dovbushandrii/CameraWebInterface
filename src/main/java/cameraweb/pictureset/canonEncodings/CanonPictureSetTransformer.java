package cameraweb.pictureset.canonEncodings;

import cameraweb.pictureset.dbobjects.PictureSetForDB;
import cameraweb.pictureset.inter.PictureSet;
import cameraweb.pictureset.inter.PictureSetTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:transformer.properties")
public class CanonPictureSetTransformer implements PictureSetTransformer {

    @Value("${canon.encodingName}")
    private String canonEncoding;

    @Override
    public PictureSetForDB transformToDBO(PictureSet set) {
        PictureSetForDB dbo = new PictureSetForDB();
        dbo.setEncodingsType(this.canonEncoding);
        dbo.setCount(set.getCount());
        dbo.setExposureCode(set.getExposure().getCode());
        dbo.setExposureTime(set.getExposureTime());
        dbo.setIsoCode(set.getIso().getCode());
        dbo.setApertureCode(set.getAperture().getCode());
        dbo.setPictureName(set.getPictureName());
        dbo.setPause(set.getPause());
        return dbo;
    }

    @Override
    public PictureSet transformFromDBO(PictureSetForDB dbo) {
        if(dbo.getEncodingsType().equals(this.canonEncoding)) {
            PictureSet set = new CanonPictureSet();
            set.setCount(dbo.getCount());
            set.setExposure(dbo.getExposureCode());
            set.setExposureTime(dbo.getExposureTime());
            set.setIso(dbo.getIsoCode());
            set.setAperture(dbo.getApertureCode());
            set.setPictureName(dbo.getPictureName());
            set.setPause(dbo.getPause());
            return set;
        }
        throw new IllegalArgumentException("Picture set is not for Canon camera's");
    }
}

