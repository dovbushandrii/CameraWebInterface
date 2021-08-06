package camera_api;

import camera_api.canon.CanonSDK;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CamFacConfig {
    @Bean
    public CanonSDK canonSDK(){
        return new CanonSDK();
    }

    @Bean (initMethod = "initialize", destroyMethod= "terminate")
    public CameraFactory cameraFactory(){
        return new CameraFactory(canonSDK());
    }
}
