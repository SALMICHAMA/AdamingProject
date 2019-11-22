package com.adaming.animals.dto;

import java.util.List;

public class OrganDto {

    private Long id;
    private String name;
    private String description;
    private boolean isVital;
    private List<AnimalDto> animals;

    public OrganDto(Long id, String name, String description, boolean isVital, List<AnimalDto> animalsDto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isVital = isVital;
        this.animals = animalsDto;
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

    public List<AnimalDto> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalDto> animals) {
        this.animals = animals;
    }
}
