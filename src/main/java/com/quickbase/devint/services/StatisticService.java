package com.quickbase.devint.services;

import com.quickbase.devint.db.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class StatisticService {
    private static final Logger logger = LoggerFactory.getLogger(StatisticService.class);

    private DBManager dbManager;
    private StatService restService;

    public StatisticService(DBManager dbManager, StatService restService) {
        this.dbManager = dbManager;
        this.restService = restService;
    }

    private  Map<String, Integer> mergeStatistics() {
        Map<String, Integer> statistics = new TreeMap<>();

        Stream.concat(dbManager.getData().stream(), restService.getCountryPopulations().stream())
                .forEach(record ->
                        statistics.compute(record.getLeft(), (k, v) -> (v == null) ? record.getRight() : v));

        return statistics;

    }

    public void showStatistics() {
        mergeStatistics()
                .forEach((key, value) -> logger.info(String.format("|%25s|%10d|", key, value)));
    }
}
