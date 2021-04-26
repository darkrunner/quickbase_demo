package com.quickbase.devint.repository;

import com.quickbase.devint.model.State;
import com.quickbase.devint.model.StatePopulation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {

    @Query(value = "SELECT s.STATE_NAME AS STATE , sum(c.POPULATION) AS POPULATION FROM STATE s \n" +
            "            INNER JOIN COUNTRY cntr ON cntr.COUNTRY_ID = s.COUNTRY_ID \n" +
            "            INNER JOIN CITY c ON c.STATE_ID = s.STATE_ID \n" +
            "            WHERE cntr.COUNTRY_NAME = :countryName" +
            "            AND c.POPULATION IS NOT NULL \n" +
            "            AND c.POPULATION > 0 \n" +
            "            GROUP BY s.STATE_ID \n" +
            "            ORDER BY s.STATE_NAME", nativeQuery = true)
    List<StatePopulation> getCountryStatesPopulation(String countryName);

    @Query(value = "SELECT s.STATE_NAME AS STATE, sum(c.POPULATION) AS POPULATION FROM STATE s \n" +
            "            INNER JOIN COUNTRY cntr ON cntr.COUNTRY_ID = s.COUNTRY_ID \n" +
            "            INNER JOIN CITY c ON c.STATE_ID = s.STATE_ID \n" +
            "            WHERE cntr.COUNTRY_NAME = :countryName" +
            "            AND s.STATE_NAME = :stateName" +
            "            AND c.POPULATION IS NOT NULL \n" +
            "            AND c.POPULATION > 0 \n" +
            "            GROUP BY s.STATE_ID \n" +
            "            ORDER BY s.STATE_NAME", nativeQuery = true)
    List<StatePopulation> getCountryStatePopulation(String countryName, String stateName);
}
