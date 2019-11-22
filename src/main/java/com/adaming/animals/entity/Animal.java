package com.adaming.animals.entity;

import com.adaming.animals.dto.AnimalDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "animals")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String category;
    private String environment;
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "animals_organs",
            joinColumns = @JoinColumn(name = "animals_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "organs_id", referencedColumnName = "id"))
    private List<Organ> organs;

    public Animal() {
    }

    public Animal(String name, String category, String environment) {
        this.name = name;
        this.category = category;
        this.environment = environment;
    }

    public Animal(String name, String category, String environment, List<Organ> organs) {
        this.name = name;
        this.category = category;
        this.environment = environment;
        this.organs = organs;
    }

    public Animal(String name, String category, String environment, String imageUrl, List<Organ> organs) {
        this.name = name;
        this.category = category;
        this.environment = environment;
        this.organs = organs;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public AnimalDto toAnimalsDto() {
        return new AnimalDto(this.id, this.name, this.category, this.environment, this.imageUrl);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Organ> getOrgans() {
        return organs;
    }

    public void setOrgans(List<Organ> organs) {
        this.organs = organs;
    }
}
