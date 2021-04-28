package com.quickbase.devint.services;

import com.quickbase.devint.model.State;
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

    @Autowired
    private StatisticsHelper statisticsHelper;

    public List<State> getAllCountryStates(Integer countryId) {
        return stateRepository.findAllByCountry_CountryId(countryId);
    }

    public  Map<String, Integer> getCountryStatesPopulation(Integer countryId) {
        logger.debug("Retrieving country {} state population data from database.", countryId);

        List<Pair<String, Integer>> records = stateRepository.getCountryStatesPopulation(countryId).stream()
                .map( record -> new ImmutablePair<>(record.getState(), record.getPopulation()))
                .collect(Collectors.toList());

        return statisticsHelper.computeStatistics(records.stream(),
                Stream.empty());
    }

    public  Map<String, Integer> getCountryStatePopulation(Integer countryId, Integer stateId) {
        logger.debug("Retrieving country {} state {} population data from database.", countryId, stateId);

        List<Pair<String, Integer>> records = stateRepository.getCountryStatePopulation(countryId, stateId).stream()
                .map( record -> new ImmutablePair<>(record.getState(), record.getPopulation()))
                .collect(Collectors.toList());

        return statisticsHelper.computeStatistics(records.stream(),
                Stream.empty());
    }
}
