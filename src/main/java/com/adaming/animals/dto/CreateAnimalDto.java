package com.adaming.animals.dto;

import com.adaming.animals.entity.Organ;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class CreateAnimalDto {

    private String name;
    private String category;
    private String environment;
    private List<Organ> organList;
    private MultipartFile file;
    private String imageUrl;

    public CreateAnimalDto() {
    }

    public CreateAnimalDto(String name, String category, String environment, List<Organ> organList, MultipartFile file) {
        this.name = name;
        this.category = category;
        this.environment = environment;
        this.organList = organList;
        this.file = file;
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

    public List<Organ> getOrganList() {
        return organList;
    }

    public void setOrganList(List<Organ> organList) {
        this.organList = organList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
