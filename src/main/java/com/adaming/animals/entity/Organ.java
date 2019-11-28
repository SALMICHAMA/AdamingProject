package com.adaming.animals.entity;

import com.adaming.animals.dto.OrganDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "organs")
public class Organ implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean isVital;

    @ManyToMany(mappedBy = "organs", fetch = FetchType.EAGER)
    List<Animal> animals;


    public Organ() {
    }

    public Organ(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public Organ(String name, String description, boolean isVital) {
        this.name = name;
        this.description = description;
        this.isVital = isVital;
    }
    public Organ(String name, String description, boolean isVital, List<Animal> animalList) {
        this.name = name;
        this.description = description;
        this.isVital = isVital;
        this.animals=animalList;
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

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    public OrganDto toDto() {
        return new OrganDto(this.id, this.name, this.description, this.isVital);
    }
}
