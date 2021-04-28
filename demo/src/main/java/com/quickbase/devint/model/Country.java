package com.quickbase.devint.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country {
    @Id
    private Integer countryId;

    @Column
    private String countryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<State> states;

    public Country() {
        //NO-Ops
    }

    public Country(Integer countryId, String countryName, Set<State> states) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.states = states;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }
}
