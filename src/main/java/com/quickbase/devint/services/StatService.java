package com.quickbase.devint.services;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public interface StatService {
	List<Pair<String, Integer>> getCountryPopulations();
}
