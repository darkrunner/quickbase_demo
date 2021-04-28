package com.quickbase.devint.services;

import com.quickbase.devint.model.City;
import com.quickbase.devint.repository.CityRepository;
import com.quickbase.devint.utils.StatisticsHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StatisticsHelper statisticsHelper;

    public  Map<String, Integer> getStateCityPopulation(Integer stateId, Integer cityId) {
        logger.debug("Retrieving state {} city {} population data from database.", stateId, cityId);

        List<Pair<String, Integer>> records = cityRepository.getStateCityPopulation(stateId, cityId).stream()
                .map( record -> new ImmutablePair<>(record.getCity(), record.getPopulation()))
                .collect(Collectors.toList());

        return statisticsHelper.computeStatistics(records.stream(),
                Stream.empty());
    }

    public List<City> getAllStateCities(Integer stateId) {
        return cityRepository.findAllByState_StateId(stateId);
    }

    public Optional<City> getCity(Integer cityId) {
        return cityRepository.findById(cityId);
    }
}
