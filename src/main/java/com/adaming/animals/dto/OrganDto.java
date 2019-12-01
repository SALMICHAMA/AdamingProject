package com.adaming.animals.dto;

import com.adaming.animals.entity.Animal;

import java.util.List;

public class OrganDto {

    private Long id;
    private String name;
    private String description;
    private boolean isVital;
    //private List<Animal> animals;

    public OrganDto(Long id, String name, String description, boolean isVital) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isVital = isVital;
    }
    public OrganDto(String name, String description, boolean isVital) {
        this.name = name;
        this.description = description;
        this.isVital = isVital;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
