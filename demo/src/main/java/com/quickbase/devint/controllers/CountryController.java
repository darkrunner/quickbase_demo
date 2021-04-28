package com.quickbase.devint.controllers;

import com.quickbase.devint.dto.CountryDTO;
import com.quickbase.devint.dto.StateDTO;
import com.quickbase.devint.services.CountryService;
import com.quickbase.devint.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountries().stream().map(CountryDTO::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{countryId}")
    public List<StateDTO> getAllCountryStates(@PathVariable Integer countryId) {
        return stateService.getAllCountryStates(countryId).stream().map(StateDTO::fromEntity).collect(Collectors.toList());
    }

}
