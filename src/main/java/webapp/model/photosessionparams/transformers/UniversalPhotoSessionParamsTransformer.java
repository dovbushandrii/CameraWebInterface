package webapp.model.photosessionparams.transformers;

import webapp.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import webapp.model.photosessionparams.inter.PhotoSessionParams;
import webapp.model.photosessionparams.inter.PhotoSessionParamsTransformer;
import org.springframework.stereotype.Component;

@Component
public class UniversalPhotoSessionParamsTransformer implements PhotoSessionParamsTransformer {

    @Override
    public PhotoSessionParamsDTO transformToDBO(PhotoSessionParams set) {
        PhotoSessionParamsDTO dbo = new PhotoSessionParamsDTO();
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
    public PhotoSessionParams transformFromDBO(PhotoSessionParamsDTO dbo) {
        try {
            Class photoSessionParamsClass = Class.forName(dbo.getEncodingsType());
            PhotoSessionParams params = (PhotoSessionParams) photoSessionParamsClass.newInstance();
            params.setId(dbo.getId());
            params.setCount(dbo.getCount());
            params.setExposure(dbo.getExposureValue());
            params.setExposureTime(dbo.getExposureTime());
            params.setIso(dbo.getIsoValue());
            params.setAperture(dbo.getApertureValue());
            params.setPictureName(dbo.getPictureName());
            params.setPause(dbo.getPause());
            return params;
        } catch (Exception e) {
            throw new IllegalArgumentException("PhotoSessionParams class is not existing:" + dbo.getEncodingsType());
        }
    }
}
