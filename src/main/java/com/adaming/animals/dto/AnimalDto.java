package com.adaming.animals.dto;

import com.adaming.animals.entity.Organs;

import java.util.List;


public class AnimalDto {

    private Long id;
    private String name;
    private String category;
    private String environment;
    private List<Organs> organsList;
    private String imageUrl;

    public AnimalDto(Long id, String name, String category, String environment, List<Organs> organsList, String imageUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.environment = environment;
        this.organsList = organsList;
        this.imageUrl = imageUrl;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public List<Organs> getOrgansList() {
        return organsList;
    }

    public void setOrgansList(List<Organs> organsList) {
        this.organsList = organsList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
