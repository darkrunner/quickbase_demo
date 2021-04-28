package com.quickbase.devint.model;

import javax.persistence.*;

@Entity
public class City {
    @Id
    private Integer cityId;

    @Column
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    private State state;

    @Column
    private Integer population;


    public City() {
        // NO-Ops
    }


    public City(Integer cityId, String cityName, State state, Integer population) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.state = state;
        this.population = population;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
