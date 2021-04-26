package com.quickbase.devint.services;

import com.quickbase.devint.repository.StateRepository;
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
public class StateService {

    private static final Logger logger = LoggerFactory.getLogger(StateService.class);

    @Autowired
    private StateRepository stateRepository;

    private List<Pair<String, Integer>> getStatePopulationData(String countryName) {
        logger.debug("Retrieving state population data from database.");
        return stateRepository.getCountryStatesPopulation(countryName)
                .stream().map( record ->
                        new ImmutablePair<>(record.getState(), record.getPopulation())).collect(Collectors.toList());
    }

    private List<Pair<String, Integer>> getCountryStatePopulationData(String countryName, String stateName) {
        logger.debug("Retrieving state population data from database.");
        return stateRepository.getCountryStatePopulation(countryName, stateName)
                .stream().map( record ->
                        new ImmutablePair<>(record.getState(), record.getPopulation())).collect(Collectors.toList());
    }

    public  Map<String, Integer> getCountryStatesPopulation(String countryName) {
        return StatisticsHelper.computeStatistics(getStatePopulationData(countryName).stream(),
                Stream.empty());
    }

    public  Map<String, Integer> getCountryStatePopulation(String countryName, String stateName) {
        return StatisticsHelper.computeStatistics(getCountryStatePopulationData(countryName, stateName).stream(),
                Stream.empty());
    }
}
