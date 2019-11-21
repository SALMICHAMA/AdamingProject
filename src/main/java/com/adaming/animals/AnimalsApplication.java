package com.adaming.animals;


import com.adaming.animals.entity.Animals;
import com.adaming.animals.service.AnimalsService;
import com.adaming.animals.entity.Organs;
import com.adaming.animals.service.OrgansService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnimalsApplication{

    public static void main(String[] args) {
        SpringApplication.run(AnimalsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoAnimal(AnimalsService animalsService){
        return(args)->{
            Animals animals=new Animals();
            animals.setName("dog");
            animals.setEnvironment("terrestre");
            animals.setCategory("mammal");
            animalsService.createAnimal(animals);
        };
    }

    @Bean
    public CommandLineRunner demoOrgan (OrgansService organsService){
        return (args) -> {
            organsService.saveOrgan(new Organs("oreille","organe necessaire pour écouter",false));

        };
    }

}
