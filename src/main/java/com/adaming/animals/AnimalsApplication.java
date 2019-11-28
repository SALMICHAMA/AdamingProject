package com.adaming.animals;


import com.adaming.animals.entity.Animal;
import com.adaming.animals.entity.Organ;
import com.adaming.animals.service.animals.AnimalServiceImpl;
import com.adaming.animals.service.organs.OrganServiceImpl;
import com.adaming.animals.service.storage.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AnimalsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication.class, args);
    }

    @Autowired
    AnimalServiceImpl animalsService;
    @Autowired
    OrganServiceImpl organsService;
    @Autowired
    ImageServiceImpl imageServiceImpl;

    @Override
    public void run(String... args) throws Exception {
        List<Organ> list = new ArrayList<>();
        List<Organ> list2 = new ArrayList<>();
        imageServiceImpl.init();
        Organ organ = new Organ();
        organ.setName("lung");
        organ.setDescription("organe necessaire pour respirer");
        organ.setVital(true);

        Organ organ2 = new Organ();
        organ2.setName("heart");
        organ2.setDescription("pump the blood");
        organ2.setVital(true);

        Organ organ3 = new Organ();
        organ3.setName("fin");
        organ3.setDescription("give stability ans mobility to fishes");
        organ3.setVital(false);

        Organ organ4 = new Organ();
        organ4.setName("wings");
        organ4.setDescription("help birds to fly");
        organ4.setVital(false);

        list.add(organ);
        list.add(organ2);

        list2.add(organ2);
        list2.add(organ3);

        organsService.saveOrgan(organ);
        organsService.saveOrgan(organ2);
        organsService.saveOrgan(organ3);
        organsService.saveOrgan(organ4);

        Animal animal = new Animal("dog", "mammal", "terrestre", list);
        animal.setImageName("dog.jpg");
        animalsService.createAnimal(animal.getName(), animal.getCategory(), animal.getEnvironment(), animal.getImageUrl());

        Animal animal1 = new Animal("cat", "mammal", "terrestre");
        animal1.setImageName("cat.jpg");
        animalsService.createAnimal(animal1.getName(), animal1.getCategory(), animal1.getEnvironment(), animal1.getImageUrl());

        Animal animal2 = new Animal("heron", "bird", "lake/sky", list);
        animal2.setImageName("vaugrenier_heron.jpg");
        animalsService.createAnimal(animal2.getName(), animal2.getCategory(), animal2.getEnvironment(), animal2.getImageUrl(), animal2.getOrgans());

        Animal animal3 = new Animal("crocodile", "reptile", "swamp");
        animal3.setImageName("croco.jpg");
        animalsService.createAnimal(animal3.getName(), animal3.getCategory(), animal3.getEnvironment(), animal3.getImageUrl());

        Animal animal4 = new Animal("salmon", "fish", "river/sea", list2);
        animal4.setImageName("Pink_salmon.jpg");
        animalsService.createAnimal(animal4.getName(), animal4.getCategory(), animal4.getEnvironment(), animal4.getImageUrl(), animal4.getOrgans());

        Animal animal5 = new Animal("toad", "amphibian", "swamp");
        animal5.setImageName("toad.jpg");
        animalsService.createAnimal(animal5.getName(), animal5.getCategory(), animal5.getEnvironment(), animal5.getImageUrl(), animal5.getOrgans());

    }

}
