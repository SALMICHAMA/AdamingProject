package com.adaming.animals.service;

import com.adaming.animals.entity.Animals;
import com.adaming.animals.entity.Organs;

import java.util.List;

public interface AnimalsService {


    void createAnimal(String name,String category,String environment);
    void createAnimal(String name, String category, String environment, List<Organs> organsList);
    void deleteAnimalByName(String name);
    List<Animals> showAllAnimals();
    //List<Animals> showAnimalsByAnimalGroup(String animal_group);
    Animals showSpecificAnimal(String name);
    Animals findById(Long id);

}
