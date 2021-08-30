package camera_api.canon;

import camera_api.interfaces.camerasdk.CameraSDK;
import camera_api.interfaces.companies.Company;
import camera_api.interfaces.companies.PictureSetFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:companies.properties")
public class CanonCompany implements Company {

    @Value("${canon.company.name}")
    private String companyName;
    private final CameraSDK sdk;
    private final PictureSetFactory pictureSetFactory;

    @Autowired
    public CanonCompany(CanonSDK sdk, CanonPictureSetFactory pictureSetFactory){
        this.sdk = sdk;
        this.pictureSetFactory = pictureSetFactory;
    }

    @Override
    public boolean equals(Object object) {
        if(object != null) {
            if (object.getClass().getName().equals(this.getClass().getName())) {
                CanonCompany company = (CanonCompany) object;
                if (company.companyName.equals(this.companyName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return companyName.hashCode();
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public CameraSDK getCameraSDK() {
        return sdk;
    }

    @Override
    public PictureSetFactory getPictureSetFactory() {
        return pictureSetFactory;
    }

    @Override
    public String[] getNamesOfAvailableCameras() {
        return sdk.getCameraNameList();
    }
}
