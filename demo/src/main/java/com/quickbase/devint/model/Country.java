package com.quickbase.devint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    private Integer countryId;

    @Column
    private String countryName;

    @JsonIgnore
    @OneToMany(mappedBy="country")
    private Set<State> states;
}
