package com.quickbase.devint.repository;

import com.quickbase.devint.model.City;
import com.quickbase.devint.model.CityPopulation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    @Query(value = "SELECT c.CITY_NAME AS CITY, sum(c.POPULATION) AS POPULATION FROM CITY s \n" +
            "            INNER JOIN STATE s ON s.STATE_ID = c.STATE_ID \n" +
            "            WHERE s.STATE_NAME = :stateName" +
            "            AND c.POPULATION IS NOT NULL \n" +
            "            AND c.POPULATION > 0 \n" +
            "            GROUP BY s.STATE_ID \n" +
            "            ORDER BY s.STATE_NAME", nativeQuery = true)
    List<CityPopulation> getStateCitiesPopulation(String stateName);

    @Query(value = "SELECT c.CITY_NAME AS CITY, sum(c.POPULATION) AS POPULATION FROM CITY s \n" +
            "            INNER JOIN STATE s ON s.STATE_ID = c.STATE_ID \n" +
            "            WHERE s.STATE_NAME = :stateName" +
            "            AND c.CITY_NAME = :cityName" +
            "            AND c.POPULATION IS NOT NULL \n" +
            "            AND c.POPULATION > 0 \n" +
            "            GROUP BY s.STATE_ID \n" +
            "            ORDER BY s.STATE_NAME", nativeQuery = true)
    List<CityPopulation> getStateCityPopulation(String stateName, String cityName);

}
