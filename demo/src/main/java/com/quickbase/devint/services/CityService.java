package com.quickbase.devint.services;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private CityRepository cityRepository;

    private List<Pair<String, Integer>> getCitiesPopulationData(String stateName) {
        logger.debug("Retrieving cities population data for state {} from database.", stateName);
        return cityRepository.getStateCitiesPopulation(stateName)
                .stream().map( record ->
                        new ImmutablePair<>(record.getCity(), record.getPopulation())).collect(Collectors.toList());
    }

    private List<Pair<String, Integer>> getStateCityPopulationData(String stateName, String cityName) {
        logger.debug("Retrieving state {} city {} population data from database.", stateName, cityName);
        return cityRepository.getStateCityPopulation(stateName, cityName)
                .stream().map( record ->
                        new ImmutablePair<>(record.getCity(), record.getPopulation())).collect(Collectors.toList());
    }

    public  Map<String, Integer> getCountryStatesPopulation(String stateName) {
        return StatisticsHelper.computeStatistics(getCitiesPopulationData(stateName).stream(),
                Stream.empty());
    }

    public  Map<String, Integer> getCountryStatePopulation(String stateName, String cityName) {
        return StatisticsHelper.computeStatistics(getStateCityPopulationData(stateName, cityName).stream(),
                Stream.empty());
    }
}
