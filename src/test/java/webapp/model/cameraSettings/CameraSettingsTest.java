package webapp.model.cameraSettings;

import camera_api.interfaces.camerasdk.Camera;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CameraSettingsTest {
    @MockBean
    private Camera camera;

    @Test
    public void constructorTest() {
        new CameraSettings(camera);
        Mockito.verify(camera, Mockito.times(1)).getISO();
        Mockito.verify(camera, Mockito.times(1)).getExposure();
        Mockito.verify(camera, Mockito.times(1)).getExposureComp();
        Mockito.verify(camera, Mockito.times(1)).getAperture();
        Mockito.verify(camera, Mockito.times(1)).getFocusSettings();
        Mockito.verify(camera, Mockito.times(1)).getWhiteBalance();
        Mockito.verify(camera, Mockito.times(1)).getDriveMode();
        Mockito.verify(camera, Mockito.times(1)).getColorSpace();

        Mockito.verify(camera, Mockito.times(1)).getPossibleISO();
        Mockito.verify(camera, Mockito.times(1)).getPossibleExposure();
        Mockito.verify(camera, Mockito.times(1)).getPossibleExposureComp();
        Mockito.verify(camera, Mockito.times(1)).getPossibleAperture();
        Mockito.verify(camera, Mockito.times(1)).getPossibleFocusSettings();
        Mockito.verify(camera, Mockito.times(1)).getPossibleWhiteBalance();
        Mockito.verify(camera, Mockito.times(1)).getPossibleDriveMode();
    }

    @Test
    public void applySetting() {
        CameraSettings settings = new CameraSettings(camera);
        settings.applyCameraSettings(camera);
        Mockito.verify(camera, Mockito.times(1))
                .setISO(settings.getIso());
        Mockito.verify(camera, Mockito.times(1))
                .setExposure(settings.getExposure());
        Mockito.verify(camera, Mockito.times(1))
                .setExposureComp(settings.getExposureComp());
        Mockito.verify(camera, Mockito.times(1))
                .setAperture(settings.getAperture());
        Mockito.verify(camera, Mockito.times(1))
                .setFocusSettings(settings.getAfSetting());
        Mockito.verify(camera, Mockito.times(1))
                .setWhiteBalance(settings.getWbSetting());
        Mockito.verify(camera, Mockito.times(1))
                .setDriveMode(settings.getDriveMode());
        Mockito.verify(camera, Mockito.times(1))
                .setColorSpace(settings.getColorSpace());
    }
}
