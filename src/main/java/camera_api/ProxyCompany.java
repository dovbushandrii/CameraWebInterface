package camera_api;

import camera_api.exceptions.CompanyNotFoundException;
import camera_api.interfaces.companies.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProxyCompany {

    private final List<Company> companies;
    private Company company = null;

    @Autowired
    public ProxyCompany(List<Company> companies) {
        this.companies = companies;
    }

    public void loadCompanySoftware(String companyName) {
        Optional<Company> foundCompany = companies.stream()
                .filter(comp -> comp.getCompanyName().equals(companyName))
                .findFirst();
        if(foundCompany.isPresent()){
            if(!foundCompany.get().equals(company)) {
                if (this.company != null) {
                    this.company.getCameraSDK().terminateSDK();
                }
                this.company = foundCompany.get();
                this.company.getCameraSDK().initializeSDK();
            }
        }
        else{
            throw new CompanyNotFoundException("Cannot load company with name: " + companyName);
        }
    }

    public Company getCompany() {
        if (company == null) {
            throw new CompanyNotFoundException("Company classes was not loaded");
        } else {
            return company;
        }
    }
}
