package com.adaming.animals.service;

import com.adaming.animals.entity.Animals;

import java.util.List;

public interface AnimalsService {


    void createAnimal(String name,String category,String environment);
    void deleteAnimalByName(String name);
    List<Animals> showAllAnimals();
    //List<Animals> showAnimalsByAnimalGroup(String animal_group);
    Animals showSpecificAnimal(String name);
    Animals findById(Long id);

}
