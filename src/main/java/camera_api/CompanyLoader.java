package camera_api;

import camera_api.exceptions.CompanyNotFoundException;
import camera_api.interfaces.companies.Company;
import camera_api.interfaces.patterns.Observed;
import camera_api.interfaces.patterns.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CompanyLoader implements Observed {

    private final List<Company> companies;
    private Company company = null;
    private final List<Observer> observers = new ArrayList<>();

    @Autowired
    public CompanyLoader(List<Company> companies) {
        this.companies = companies;
    }

    public void loadCompanySoftware(String companyName) {
        Optional<Company> foundCompany = companies.stream()
                .filter(comp -> comp.getCompanyName().equals(companyName))
                .findFirst();
        if (foundCompany.isPresent()) {
            if (!foundCompany.get().equals(company)) {
                if (this.company != null) {
                    this.company.getCameraSDK().terminateSDK();
                    this.notifyAllObservers();
                }
                this.company = foundCompany.get();
                this.company.getCameraSDK().initializeSDK();
            }
        } else {
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

    @Override
    public void notifyAllObservers() {
        for (Observer obs : observers) {
            obs.handleEvent();
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
