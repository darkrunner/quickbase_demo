package com.quickbase.devint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class State {
    @Id
    private Integer stateId;

    @Column
    private String stateName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="COUNTRY_ID")
    private Country country;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
    private Set<City> cities;

    public State() {
        //NO-Ops
    }

    public State(Integer stateId, String stateName, Country country, Set<City> cities) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.country = country;
        this.cities = cities;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
