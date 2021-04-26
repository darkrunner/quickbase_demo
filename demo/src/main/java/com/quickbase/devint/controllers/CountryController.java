package com.quickbase.devint.controllers;

import com.quickbase.devint.model.Country;
import com.quickbase.devint.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAlCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{countryName}/population")
    public Map<String, Integer> getCountryPopulation(@PathVariable String countryName){
        return countryService.getCountryPopulationStatistics(countryName);
    }

}
