package com.adaming.animals.service;

import com.adaming.animals.entity.Animals;
import com.adaming.animals.entity.Organs;
import com.adaming.animals.repository.AnimalsRepository;
import com.adaming.animals.repository.OrgansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalsServiceImpl implements AnimalsService {
    @Autowired
    AnimalsRepository animalsRepository;
    @Autowired
    OrgansServiceImpl organsService;
    @Override
    public void createAnimal(String name,String category,String environment) {
        Animals animal=new Animals(name,category,environment);
        animalsRepository.save(animal);
    }

    @Override
    public void createAnimal(String name, String category, String environment, List<Organs> organsList) {
        Animals animal=new Animals(name,category,environment,organsList);
        if(organsList==null){
            animalsRepository.save(animal);
        } else {
            for (int i = 0; i < organsList.size(); i++) {
                Organs organToTest = organsService.showSpecificOrgan(organsList.get(i).getName());
                if (organToTest != null) {
                    organsList.set(i, organToTest);
                } else {
                    organsService.saveOrgan(organsList.get(i));
                }
            }
            animal=new Animals(name,category,environment,organsList);
            animalsRepository.save(animal);
        }


    }

    @Override
    public void createAnimal(String name, String category, String environment, String imageUrl, List<Organs> organsList) {
        Animals animal=new Animals(name,category,environment,imageUrl,organsList);
        if(organsList==null){
            animalsRepository.save(animal);
        } else {
            for (int i = 0; i < organsList.size(); i++) {
                Organs organToTest = organsService.showSpecificOrgan(organsList.get(i).getName());
                if (organToTest != null) {
                    organsList.set(i, organToTest);
                } else {
                    organsService.saveOrgan(organsList.get(i));
                }
            }
            animal=new Animals(name,category,environment,imageUrl,organsList);
            animalsRepository.save(animal);
        }
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
