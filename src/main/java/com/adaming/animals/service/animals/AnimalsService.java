package com.adaming.animals.service.animals;

import com.adaming.animals.entity.Animal;
import com.adaming.animals.entity.Organ;

import java.util.List;

public interface AnimalsService {


    void createAnimal(String name,String category,String environment);
    void createAnimal(String name, String category, String environment, List<Organ> organList);
    Animal createAnimal(String name, String category, String environment, String imageUrl, List<Organ> organList);
    void deleteAnimalByName(String name);
    List<Animal> showAllAnimals();
    //List<Animal> showAnimalsByAnimalGroup(String animal_group);
    Animal showSpecificAnimal(String name);
    Animal findById(Long id);

}
