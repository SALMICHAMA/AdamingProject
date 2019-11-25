package com.adaming.animals;


import com.adaming.animals.entity.Animal;
import com.adaming.animals.entity.Organ;
import com.adaming.animals.service.animals.AnimalServiceImpl;
import com.adaming.animals.service.storage.ImageServiceImpl;
import com.adaming.animals.service.organs.OrganServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AnimalsApplication implements CommandLineRunner{

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
        List<Organ> list=new ArrayList<>();
        imageServiceImpl.init();
        Organ organ = new Organ();
        organ.setName("lung");
        organ.setDescription("organe necessaire pour respirer");
        organ.setVital(true);

        Organ organ2 = new Organ();
        organ2.setName("heart");
        organ2.setDescription("pump the blood");
        organ2.setVital(true);
        list.add(organ);
        list.add(organ2);

        organsService.saveOrgan(organ);
        organsService.saveOrgan(organ2);

        Animal animal =new Animal("dog","mammal","terrestre");
        animalsService.createAnimal(animal.getName(), animal.getCategory(), animal.getEnvironment());

        Animal animal2=new Animal("cat","mammal","terrestre",list);
        animal2.setImageUrl("http://localhost:8080/api/uploads/vaugrenier heron.jpg");
        animalsService.createAnimal(animal2.getName(),animal2.getCategory(),animal2.getEnvironment(),animal2.getImageUrl(),animal2.getOrgans());

        Animal animal3=new Animal("crocodile","reptile","swamp");
        animalsService.createAnimal(animal3.getName(),animal3.getCategory(),animal3.getEnvironment());



    }

}
