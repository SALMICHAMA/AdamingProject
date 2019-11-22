package com.adaming.animals.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="organs")
public class Organs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean isVital;

    @ManyToMany(mappedBy = "organs")
    Set<Animals> animals;
    public Organs() {
    }

    public Organs(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public Organs(String name, String description, boolean isVital) {
        this.name = name;
        this.description = description;
        this.isVital = isVital;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVital() {
        return isVital;
    }

    public void setVital(boolean vital) {
        isVital = vital;
    }
}
