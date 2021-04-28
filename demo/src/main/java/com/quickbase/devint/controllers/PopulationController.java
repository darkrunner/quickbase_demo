package com.quickbase.devint.controllers;

import com.quickbase.devint.services.CityService;
import com.quickbase.devint.services.CountryService;
import com.quickbase.devint.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/population")
public class PopulationController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @GetMapping
    public Map<String, Integer> getAllCountriesPopulation() {
        return countryService.getCountriesPopulationStatistics();
    }

    @GetMapping("/{countryId}")
    public Map<String, Integer> getAllCountriesPopulation(@PathVariable Integer countryId) {
        return countryService.getCountryPopulationStatistics(countryId);
    }

    @GetMapping("/{countryId}/state")
    public Map<String, Integer> getAllCountryStatesPopulation(@PathVariable Integer countryId) {
        return stateService.getCountryStatesPopulation(countryId);
    }

    @GetMapping("/{countryId}/state/{stateId}")
    public Map<String, Integer> getAllCountryStatesPopulation(@PathVariable Integer countryId,
                                                              @PathVariable Integer stateId) {
        return stateService.getCountryStatePopulation(countryId, stateId);
    }


    @GetMapping("/state/{stateId}/city/{cityId}")
    public Map<String, Integer> getAllStateCitiesPopulation(@PathVariable Integer stateId,
                                                            @PathVariable Integer cityId) {
        return cityService.getStateCityPopulation(stateId, cityId);
    }

}
