package com.quickbase.devint.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class StatisticsHelper {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsHelper.class);

    public static Map<String, Integer> computeStatistics(Stream<Pair<String, Integer>> dbData,
                                                   Stream<Pair<String, Integer>> restServiceData) {
        Map<String, Integer> statistics = new TreeMap<>();

        Stream.concat(dbData,restServiceData)
                .forEach(record ->
                        statistics.compute(record.getLeft(), (k, v) -> (v == null) ? record.getRight() : v));

        return statistics;
    }

    public static void showStatistics(Map<String, Integer> data) {
        data.forEach((key, value) -> logger.info(String.format("|%25s|%10d|", key, value)));
    }

}
