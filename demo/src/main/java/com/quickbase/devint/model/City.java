package com.quickbase.devint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    private Integer cityId;

    @Column
    private String cityName;

    @ManyToOne
    @JoinColumn(name="STATE_ID", nullable=false)
    private State state;

    @Column
    private Integer population;
}
