package com.quickbase.devint.controllers;

import com.quickbase.devint.dto.CityDTO;
import com.quickbase.devint.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private CityService cityService;

    @GetMapping("/{stateId}")
    public List<CityDTO> getAllStateCities(@PathVariable Integer stateId) {
        return cityService.getAllStateCities(stateId).stream().map(CityDTO::fromEntity).collect(Collectors.toList());
    }

}
