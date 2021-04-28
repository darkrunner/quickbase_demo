package com.quickbase.devint.controllers;

import com.quickbase.devint.dto.CityDTO;
import com.quickbase.devint.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/{cityId}")
    public CityDTO getCity(Integer cityId) {
        return cityService.getCity(cityId)
                .map(CityDTO::fromEntity)
                .orElse(null);
    }

}
