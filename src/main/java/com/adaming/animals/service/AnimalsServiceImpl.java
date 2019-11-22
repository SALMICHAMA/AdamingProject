package com.adaming.animals.service;

import com.adaming.animals.entity.Animals;
import com.adaming.animals.entity.Organs;
import com.adaming.animals.repository.AnimalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AnimalsServiceImpl implements AnimalsService {
    @Autowired
    AnimalsRepository animalsRepository;

    @Override
    public void createAnimal(String name,String category,String environment) {
        Animals animal=new Animals(name,category,environment);
        animalsRepository.save(animal);
    }

    public void createAnimal(String name, String category, String environment, Set<Organs> organsList) {
        Animals animal=new Animals(name,category,environment,organsList);
        animalsRepository.save(animal);
    }

    @Override
    public void createAnimal(String name, String category, String environment, List<Organs> organsList) {

    }

    @Override
    public void deleteAnimalByName(String name) {
    Animals animals=animalsRepository.getAnimalsByName(name);
    Long idToDelete=animals.getId();
    animalsRepository.deleteAnimalsById(idToDelete);
    }

    @Override
    public List<Animals> showAllAnimals() {
        List<Animals> list=null;
        list=(List<Animals>) animalsRepository.findAll();
        return list;
    }

    //@Override
    //public List<Animals> showAnimalsByAnimalGroup(String animal_group) {
    //    List<Animals> list=null;
    //    list=(List<Animals>) animalsRepository.getAnimalsByAnimal_group(animal_group);
    //    return list;
   // }

    @Override
    public Animals showSpecificAnimal(String name) {
        Animals animal=animalsRepository.getAnimalsByName(name);
        return animal;
    }

    @Override
    public Animals findById(Long id) {
        Animals animals=animalsRepository.getAnimalsById(id);
        return animals;
    }
}
