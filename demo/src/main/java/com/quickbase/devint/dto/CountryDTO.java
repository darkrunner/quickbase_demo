package com.quickbase.devint.dto;

import com.quickbase.devint.model.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private Integer countryId;
    private String countryName;

    public static CountryDTO fromEntity(Country country) {
        return CountryDTO.builder()
                .countryId(country.getCountryId())
                .countryName(country.getCountryName())
                .build();
    }
}
