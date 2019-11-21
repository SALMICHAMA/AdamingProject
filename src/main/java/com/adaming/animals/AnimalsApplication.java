package com.adaming.animals;


import com.adaming.animals.entity.Animals;
import com.adaming.animals.service.AnimalsService;
import com.adaming.animals.entity.Organs;
import com.adaming.animals.service.AnimalsServiceImpl;
import com.adaming.animals.service.OrgansService;
import com.adaming.animals.service.OrgansServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnimalsApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication.class, args);
    }

    @Autowired
    AnimalsServiceImpl animalsService;
    @Autowired
    OrgansServiceImpl organsService;

    @Override
    public void run(String... args) throws Exception {
        Animals animals=new Animals();
        animals.setName("dog");
        animals.setEnvironment("terrestre");
        animals.setCategory("mammal");
        animalsService.createAnimal(animals);

        organsService.saveOrgan(new Organs("oreille","organe necessaire pour écouter",false));
    }

}
