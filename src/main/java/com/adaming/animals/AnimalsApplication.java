package com.adaming.animals;


import com.adaming.animals.entity.Animals;
import com.adaming.animals.entity.Organs;
import com.adaming.animals.service.AnimalsServiceImpl;
import com.adaming.animals.service.OrgansServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        Animals animals=new Animals("dog","mammal","terrestre");
        animalsService.createAnimal(animals.getName(),animals.getCategory(),animals.getEnvironment());
        Animals animal2=new Animals("cat","mammal","terrestre");
        animalsService.createAnimal(animal2.getName(),animal2.getCategory(),animal2.getEnvironment());
        Animals animal3=new Animals("crocodile","reptile","swamp");
        animalsService.createAnimal(animal3.getName(),animal3.getCategory(),animal3.getEnvironment());
        Organs organ = new Organs();
        organ.setName("lung");
        organ.setDescription("organe necessaire pour respirer");
        organ.setVital(true);
        organsService.saveOrgan(organ);

    }

}
