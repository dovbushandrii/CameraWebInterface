package webapp.model.deviceInfo;

import camera_api.interfaces.camerasdk.Camera;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceInfoTest {
    @MockBean
    private Camera camera;

    @Test
    public void constructorTest() {
        DeviceInfo inf = new DeviceInfo(camera);
        Mockito.verify(camera, Mockito.times(1)).productName();
        Mockito.verify(camera, Mockito.times(1)).getOwnerName();
        Mockito.verify(camera, Mockito.times(1)).firmwareVersion();
        Mockito.verify(camera, Mockito.times(1)).lensName();
    }
}
