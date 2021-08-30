package webapp.controllers;

import camera_api.CompanyLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/start")
public class CompanyLoadController {
    private final CompanyLoader company;

    @Autowired
    public CompanyLoadController(CompanyLoader company) {
        this.company = company;
    }

    @GetMapping("/load")
    public void loadCompany(@RequestParam(value = "companyName") String companyName) {
        this.company.loadCompanySoftware(companyName);
    }

}
