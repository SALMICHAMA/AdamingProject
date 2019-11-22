package com.adaming.animals.dto;

import com.adaming.animals.entity.Animals;
import com.adaming.animals.entity.Organs;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class CreateAnimalDto {
    private String name;
    private String category;
    private String environment;
    private List<Organs> organsList;
    private MultipartFile file;
    private String imageUrl;

    public CreateAnimalDto(){
    }

    public CreateAnimalDto(String name, String category, String environment, List<Organs> organsList,MultipartFile file) {
        this.name = name;
        this.category = category;
        this.environment = environment;
        this.organsList = organsList;
        this.file=file;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Animals toAnimals(){
        return new Animals(this.name,this.category,this.environment,this.imageUrl,this.organsList);
    }
}
