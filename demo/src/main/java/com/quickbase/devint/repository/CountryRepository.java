package com.quickbase.devint.repository;

import com.quickbase.devint.model.Country;
import com.quickbase.devint.model.CountryPopulation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

    @Query(value = "SELECT cntr.COUNTRY_NAME AS COUNTRY, sum(c.POPULATION) AS POPULATION FROM COUNTRY cntr \n" +
            "           INNER JOIN STATE s ON  s.COUNTRY_ID = cntr.COUNTRY_ID \n" +
            "            INNER JOIN CITY c ON c.STATE_ID = s.STATE_ID \n" +
            "            WHERE c.POPULATION IS NOT NULL \n" +
            "            AND c.POPULATION > 0 \n" +
            "            GROUP BY cntr.COUNTRY_ID \n" +
            "            ORDER BY cntr.COUNTRY_NAME", nativeQuery = true)
    List<CountryPopulation> getAllCountriesPopulation();

    @Query(value = "SELECT cntr.COUNTRY_NAME AS COUNTRY, sum(c.POPULATION) AS POPULATION FROM COUNTRY cntr \n" +
            "           INNER JOIN STATE s ON  s.COUNTRY_ID = cntr.COUNTRY_ID \n" +
            "            INNER JOIN CITY c ON c.STATE_ID = s.STATE_ID \n" +
            "            WHERE cntr.COUNTRY_NAME = :countryName" +
            "            AND c.POPULATION IS NOT NULL \n" +
            "            AND c.POPULATION > 0 \n" +
            "            GROUP BY cntr.COUNTRY_ID \n" +
            "            ORDER BY cntr.COUNTRY_NAME", nativeQuery = true)
    List<CountryPopulation> getCountryPopulation(String countryName);
}
