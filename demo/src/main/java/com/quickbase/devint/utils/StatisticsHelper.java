package com.quickbase.devint.utils;

import com.quickbase.devint.model.Correction;
import com.quickbase.devint.repository.CorrectionRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
public class StatisticsHelper {

    @Autowired
    private CorrectionRepository correctionRepository;

    public Map<String, Integer> computeStatistics(Stream<Pair<String, Integer>> dbData,
                                                   Stream<Pair<String, Integer>> restServiceData) {
        Map<String, Integer> statistics = new TreeMap<>();
        List<Correction> correctionsList = getCorrectionsList();
        Stream.concat(dbData,restServiceData)
                .forEach(record ->
                        statistics.compute(processKey(record.getLeft(), correctionsList), (k, v) -> (v == null) ? record.getRight() : v));

        return statistics;
    }

    private List<Correction> getCorrectionsList() {
       return StreamSupport.stream(correctionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    private String processKey(String key, List<Correction> corrections) {
        Optional<Correction> correction = corrections.stream()
                                                .filter(record -> record.getSource().equals(key)).findFirst();
        if (correction.isPresent()) {
            return correction.get().getTarget();
        } else {
            return key;
        }
    }

}
