package com.quickbase.devint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class State {
    @Id
    private Integer stateId;

    @Column
    private String stateName;

    @ManyToOne
    @JoinColumn(name="COUNTRY_ID", nullable=false)
    private Country country;

    @JsonIgnore
    @OneToMany(mappedBy="state")
    private Set<City> cities;
}
