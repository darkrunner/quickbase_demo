package com.quickbase.devint.dto;

import com.quickbase.devint.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private Integer cityId;
    private String cityName;
    private Integer cityPopulation;

    public static CityDTO fromEntity(City city) {
        return CityDTO.builder()
                .cityId(city.getCityId())
                .cityName(city.getCityName())
                .cityPopulation(city.getPopulation())
                .build();
    }
}
