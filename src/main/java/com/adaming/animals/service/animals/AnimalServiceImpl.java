package com.adaming.animals.service.animals;

import com.adaming.animals.entity.Animal;
import com.adaming.animals.entity.Organ;
import com.adaming.animals.repository.AnimalsRepository;
import com.adaming.animals.service.organs.OrganServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalsService {
    @Autowired
    AnimalsRepository animalsRepository;
    @Autowired
    OrganServiceImpl organsService;

    @Override
    public void createAnimal(String name, String category, String environment) {
        Animal animal = new Animal(name, category, environment);
        animalsRepository.save(animal);
    }

    @Override
    public void createAnimal(String name, String category, String environment, String imageUrl) {
        imageUrl = "http://localhost:8080/api/uploads/" + imageUrl;
        Animal animal = new Animal(name, category, environment, imageUrl);
        animalsRepository.save(animal);
    }

    @Override
    public void createAnimal(String name, String category, String environment, List<Organ> organList) {
        Animal animal = new Animal(name, category, environment, organList);
        if (organList == null) {
            animalsRepository.save(animal);
        } else {
            for (int i = 0; i < organList.size(); i++) {
                Organ organToTest = organsService.showSpecificOrgan(organList.get(i).getName());
                if (organToTest != null) {
                    organList.set(i, organToTest);
                } else {
                    organsService.saveOrgan(organList.get(i));
                }
            }
            animal = new Animal(name, category, environment, organList);
            animalsRepository.save(animal);
        }


    }

    @Override
    public void createAnimal(String name, String category, String environment, String imageUrl, List<Organ> organList) {
        imageUrl = "http://localhost:8080/api/uploads/" + imageUrl;
        Animal animal = new Animal(name, category, environment, imageUrl, organList);
        if (organList == null) {
            animalsRepository.save(animal);
        } else {
            for (int i = 0; i < organList.size(); i++) {
                Organ organToTest = organsService.showSpecificOrgan(organList.get(i).getName());
                if (organToTest != null) {
                    organList.set(i, organToTest);
                } else {
                    organsService.saveOrgan(organList.get(i));
                }
            }
            animal = new Animal(name, category, environment, imageUrl, organList);
            animalsRepository.save(animal);
        }
    }

    @Override
    public List<Animal> showAllAnimals() {
        List<Animal> list = new ArrayList<>();
        list = (List<Animal>) animalsRepository.findAll();
        return list;
    }

    @Override
    public List<Animal> showAnimalsByCategory(String category) {
        List<Animal> list = new ArrayList<>();
        list = (List<Animal>) animalsRepository.getAnimalsByCategory(category);
        return list;
    }

    @Override
    public List<Animal> showAnimalsByEnvironment(String environment) {
        List<Animal> list = new ArrayList<>();
        list = (List<Animal>) animalsRepository.getAnimalsByEnvironment(environment);
        return list;
    }

    @Override
    public Animal showSpecificAnimal(String name) {
        Animal animal = animalsRepository.getAnimalsByName(name);
        return animal;
    }

    @Override
    public Animal findById(Long id) {
        Animal animal = animalsRepository.getAnimalsById(id);
        return animal;
    }

    @Override
    public void deleteById(Long idToDelete) {
        animalsRepository.deleteById(idToDelete);
    }
}
