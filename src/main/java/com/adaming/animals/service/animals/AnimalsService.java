package com.adaming.animals.service.animals;

import com.adaming.animals.entity.Animal;
import com.adaming.animals.entity.Organ;

import java.util.List;

public interface AnimalsService {


    void createAnimal(String name, String category, String environment);

    void createAnimal(String name, String category, String environment, String imageUrl);

    void createAnimal(String name, String category, String environment, List<Organ> organList);

    void createAnimal(String name, String category, String environment, String imageUrl, List<Organ> organList);

    List<Animal> showAllAnimals();

    List<Animal> showAnimalsByCategory(String category);

    List<Animal> showAnimalsByEnvironment(String environment);

    Animal showSpecificAnimal(String name);

    Animal findById(Long id);

    void deleteById(Long idToDelete);
}
