package com.adaming.animals.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="animals")
public class Animals implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String category;
    private String environment;
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "animals_organs", joinColumns = @JoinColumn(name = "animals_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "organs_id",referencedColumnName = "id"))
    private List<Organs> organsList;

    public Animals(){}

    public Animals(String name,String category,String environment){
        this.name=name;
        this.category=category;
        this.environment=environment;
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

    public List<Organs> getOrgansList() {
        return organsList;
    }

    public void setOrgansList(List<Organs> organsList) {
        this.organsList = organsList;
    }
}
