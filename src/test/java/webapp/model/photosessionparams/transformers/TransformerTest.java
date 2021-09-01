package webapp.model.photosessionparams.transformers;

import camera_api.canon.encodings.cameraprops.EdsAperture;
import camera_api.canon.encodings.cameraprops.EdsExposure;
import camera_api.canon.encodings.cameraprops.EdsISO;
import camera_api.canon.encodings.photosessionparams.CanonPhotoSessionParams;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import webapp.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import webapp.model.photosessionparams.inter.PhotoSessionParams;
import webapp.model.photosessionparams.inter.PhotoSessionParamsTransformer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransformerTest {
    @Autowired
    private PhotoSessionParamsTransformer transformer;

    @Test
    public void transformToDBO() {
        PhotoSessionParams params = createParams();
        PhotoSessionParamsDTO dto = transformer.transformToDBO(createParams());
        Assert.assertEquals(dto.getEncodingsType(), params.getClass().getName());
        Assert.assertEquals(dto.getCount(), params.getCount());
        Assert.assertEquals(dto.getApertureValue(), params.getAperture().toString());
        Assert.assertEquals(dto.getExposureValue(), params.getExposure().toString());
        Assert.assertEquals(dto.getIsoValue(), params.getIso().toString());
        Assert.assertTrue(dto.getExposureTime() - params.getExposureTime() == 0);
        Assert.assertTrue(dto.getPause() - params.getPause() == 0);
        Assert.assertEquals(dto.getPictureName(), params.getPictureName());
    }

    @Test
    public void transformFromDBO() {
        PhotoSessionParamsDTO dto = createDTO();
        PhotoSessionParams params = transformer.transformFromDBO(dto);
        Assert.assertEquals(dto.getEncodingsType(), params.getClass().getName());
        Assert.assertEquals(dto.getCount(), params.getCount());
        Assert.assertEquals(dto.getApertureValue(), params.getAperture().toString());
        Assert.assertEquals(dto.getExposureValue(), params.getExposure().toString());
        Assert.assertEquals(dto.getIsoValue(), params.getIso().toString());
        Assert.assertTrue(dto.getExposureTime() - params.getExposureTime() == 0);
        Assert.assertTrue(dto.getPause() - params.getPause() == 0);
        Assert.assertEquals(dto.getPictureName(), params.getPictureName());
    }

    private PhotoSessionParams createParams() {
        PhotoSessionParams params = new CanonPhotoSessionParams();
        params.setAperture(EdsAperture.F_4.getCode());
        params.setExposure(EdsExposure.BULB.getCode());
        params.setIso(EdsISO.ISO_100.getCode());
        params.setExposureTime(2.2);

        params.setCount(5);
        params.setPause(2.2);
        params.setPictureName("Test");
        params.setId(4);
        return params;
    }

    private PhotoSessionParamsDTO createDTO() {
        PhotoSessionParamsDTO dto = new PhotoSessionParamsDTO();
        dto.setApertureValue(EdsAperture.F_4.toString());
        dto.setExposureValue(EdsExposure.BULB.toString());
        dto.setIsoValue(EdsISO.ISO_100.toString());
        dto.setExposureTime(2.2);

        dto.setCount(5);
        dto.setPause(2.2);
        dto.setPictureName("Test");
        dto.setEncodingsType(CanonPhotoSessionParams.class.getName());
        return dto;
    }

}
