package repository;

import entity.Animals;

import java.util.List;

public interface AnimalsRepository  {

    void saveNewAnimal(Animals animals);
    void findAnimalByName(String animalName);
    void deleteAnimal(Animals animals);
    List<Animals> getAllAnimals (Animals animals);
    void findAnimalByGroup();
}
