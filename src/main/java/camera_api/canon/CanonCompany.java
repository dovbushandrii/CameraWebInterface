package camera_api.canon;

import camera_api.canon.encodings.photosessionparams.CanonPhotoSessionParams;
import camera_api.interfaces.camerasdk.CameraSDK;
import camera_api.interfaces.companies.Company;
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

    @Autowired
    public CanonCompany(CanonSDK sdk) {
        this.sdk = sdk;
    }

    @Override
    public boolean equals(Object object) {
        if (object != null) {
            if (object.getClass().getName().equals(this.getClass().getName())) {
                CanonCompany company = (CanonCompany) object;
                return company.companyName.equals(this.companyName);
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
    public String getPhotoSessionParamsClassName() {
        return CanonPhotoSessionParams.class.getName();
    }

    @Override
    public String[] getNamesOfAvailableCameras() {
        return sdk.getCameraNameList();
    }
}
