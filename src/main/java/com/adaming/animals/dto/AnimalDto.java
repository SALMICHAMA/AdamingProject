package com.adaming.animals.dto;

import com.adaming.animals.entity.Organ;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class AnimalDto {

    private Long id;
    private String name;
    private String category;
    private String environment;
    private String imageUrl;
    private List<OrganDto> organsList;
    private Resource file;
    public AnimalDto(Long id, String name, String category, String environment, String imageUrl, List<OrganDto> organDtos) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.environment = environment;
        this.imageUrl = imageUrl;
        this.organsList = organDtos;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<OrganDto> getOrgansList() {
        return organsList;
    }

    public void setOrgansList(List<OrganDto> organsList) {
        this.organsList = organsList;
    }

    public Resource getFile() {
        return file;
    }

    public void setFile(Resource file) {
        this.file = file;
    }
}
