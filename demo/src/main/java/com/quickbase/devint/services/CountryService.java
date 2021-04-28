package com.quickbase.devint.services;

import com.quickbase.devint.model.Country;
import com.quickbase.devint.repository.CountryRepository;
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
import java.util.stream.StreamSupport;


@Service
public class CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StatService restService;

    @Autowired
    private StatisticsHelper statisticsHelper;

    public Map<String, Integer> getCountriesPopulationStatistics() {
        logger.debug("Retrieving all country population data from database.");

        List<Pair<String, Integer>> records = countryRepository.getAllCountriesPopulation().stream()
                .map( record -> new ImmutablePair<>(record.getCountry(), record.getPopulation()))
                .collect(Collectors.toList());

        return statisticsHelper.computeStatistics(records.stream(),
                restService.getCountryPopulationData().stream());
    }

    public Map<String, Integer> getCountryPopulationStatistics(Integer countryId) {
        logger.debug("Retrieving country {} population data from database.", countryId);

        List<Pair<String, Integer>> records = countryRepository.getCountryPopulation(countryId).stream()
                .map( record -> new ImmutablePair<>(record.getCountry(), record.getPopulation())).
                        collect(Collectors.toList());

        return statisticsHelper.computeStatistics(records.stream(),
                restService.getCountryPopulationData().stream());
    }

    public List<Country> getAllCountries() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
