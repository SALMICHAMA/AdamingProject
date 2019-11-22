package com.adaming.animals;


import com.adaming.animals.entity.Animals;
import com.adaming.animals.entity.Organs;
import com.adaming.animals.service.AnimalsServiceImpl;
import com.adaming.animals.service.ImageService;
import com.adaming.animals.service.OrgansServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AnimalsApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication.class, args);
    }

    @Autowired
    AnimalsServiceImpl animalsService;
    @Autowired
    OrgansServiceImpl organsService;
    @Autowired
    ImageService imageService;

    @Override
    public void run(String... args) throws Exception {
        List<Organs> list=new ArrayList<>();
        imageService.init();
        Organs organ = new Organs();
        organ.setName("lung");
        organ.setDescription("organe necessaire pour respirer");
        organ.setVital(true);

        Organs organ2 = new Organs();
        organ2.setName("heart");
        organ2.setDescription("pump the blood");
        organ2.setVital(true);
        list.add(organ);
        list.add(organ2);

        organsService.saveOrgan(organ);
        organsService.saveOrgan(organ2);
        Animals animals=new Animals("dog","mammal","terrestre");
        animalsService.createAnimal(animals.getName(),animals.getCategory(),animals.getEnvironment());
        Animals animal2=new Animals("cat","mammal","terrestre",list);
        animalsService.createAnimal(animal2.getName(),animal2.getCategory(),animal2.getEnvironment(),animal2.getOrgans());
        Animals animal3=new Animals("crocodile","reptile","swamp");
        animalsService.createAnimal(animal3.getName(),animal3.getCategory(),animal3.getEnvironment());



    }

}
