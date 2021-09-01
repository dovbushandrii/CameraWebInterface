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
    private final CompanyLoader companyLoader;

    @Autowired
    public CompanyLoadController(CompanyLoader companyLoader) {
        this.companyLoader = companyLoader;
    }

    @GetMapping("/load")
    public void loadCompany(@RequestParam(value = "companyName") String companyName) {
        this.companyLoader.loadCompanySoftware(companyName);
    }

}
