package com.quickbase.devint.model;

import javax.persistence.*;

@Entity
@Table(name = "CORRECTION_MAP")
public class Correction {
    @Id
    private String source;

    @Column
    private String target;

    public Correction() {
        // NO-Ops
    }

    public Correction(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
