package com.adaming.animals.rest;

import com.adaming.animals.dto.AnimalDto;
import com.adaming.animals.dto.CreateAnimalDto;
import com.adaming.animals.dto.OrganDto;
import com.adaming.animals.entity.Animal;
import com.adaming.animals.entity.Organ;
import com.adaming.animals.service.animals.AnimalServiceImpl;
import com.adaming.animals.service.organs.OrganServiceImpl;
import com.adaming.animals.service.storage.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AnimalsController {
    @Autowired
    OrganServiceImpl organsService;
    @Autowired
    AnimalServiceImpl animalsService;
    @Autowired
    ImageServiceImpl imageServiceImpl;

    /**
     * @param idToShow is the id of the animal that you want to display
     * @return the animal's characteristics
     */

    @GetMapping(path = "/animals/{id}")
    public AnimalDto animals_id(@PathVariable(name = "id") Long idToShow) throws IOException {
        Animal animal = animalsService.findById(idToShow);
        AnimalDto animalDto = animal.toAnimalsDto();
        return animalDto;
    }


    @GetMapping("/animals")
    public List<AnimalDto> displayAllAnimals() {
        List<Animal> list = animalsService.showAllAnimals();
        List<AnimalDto> animalDtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AnimalDto animalDtoTemp = list.get(i).toAnimalsDto();
            animalDtos.add(animalDtoTemp);
        }
        return animalDtos;
    }

    @PostMapping(path = "/animal/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public AnimalDto addAnimalSubmit(@ModelAttribute CreateAnimalDto createAnimalDto) {
        if (createAnimalDto.getFile() != null) {
            imageServiceImpl.storeAvatar(createAnimalDto.getFile());
            createAnimalDto.setImageUrl(createAnimalDto.getFile().getOriginalFilename());
        }
        animalsService.createAnimal(createAnimalDto.getName(), createAnimalDto.getCategory(), createAnimalDto.getEnvironment(), createAnimalDto.getImageUrl(), createAnimalDto.getOrganList());
        Animal animal = animalsService.showSpecificAnimal(createAnimalDto.getName());
        return animal.toAnimalsDto();
    }

    @GetMapping("/animals/filter/{valueFilter}")
    public List<CreateAnimalDto> displayByFilter(@PathVariable(name = "valueFilter") String filter) throws IOException {
        List<Animal> listCategory = animalsService.showAnimalsByCategory(filter);
        List<Animal> listEnvironment = animalsService.showAnimalsByEnvironment(filter);
        Animal animal=animalsService.showSpecificAnimal(filter);
        CreateAnimalDto createAnimalDto=new CreateAnimalDto();
        List<CreateAnimalDto> animalDtos = new ArrayList<>();
        if (listCategory != null) {
            for (int i = 0; i < listCategory.size(); i++) {
                CreateAnimalDto animalDtoTemp = new CreateAnimalDto();
                animalDtoTemp.setName(listCategory.get(i).getName());
                animalDtoTemp.setImageUrl(listCategory.get(i).getImageUrl());
                animalDtoTemp.setCategory(listCategory.get(i).getCategory());
                animalDtoTemp.setEnvironment(listCategory.get(i).getEnvironment());
                animalDtoTemp.setOrganList(listCategory.get(i).getOrgans());
                animalDtos.add(animalDtoTemp);
            }

        }
        if (listEnvironment != null) {
            for (int i = 0; i < listEnvironment.size(); i++) {
                CreateAnimalDto animalDtoTemp = new CreateAnimalDto();
                animalDtoTemp.setName(listEnvironment.get(i).getName());
                animalDtoTemp.setImageUrl(listEnvironment.get(i).getImageUrl());
                animalDtoTemp.setCategory(listEnvironment.get(i).getCategory());
                animalDtoTemp.setEnvironment(listEnvironment.get(i).getEnvironment());
                animalDtoTemp.setOrganList(listEnvironment.get(i).getOrgans());
                animalDtos.add(animalDtoTemp);
            }
        }
        if (animal != null) {
                CreateAnimalDto animalDtoTemp = new CreateAnimalDto();
                animalDtoTemp.setName(animal.getName());
                animalDtoTemp.setImageUrl(animal.getImageUrl());
                animalDtoTemp.setCategory(animal.getCategory());
                animalDtoTemp.setEnvironment(animal.getEnvironment());
                animalDtoTemp.setOrganList(animal.getOrgans());
                animalDtos.add(animalDtoTemp);
        }
        return animalDtos;
    }

    @DeleteMapping(path = "animals/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Animal> deleteAnimal(@PathVariable(name = "id") Long idToDelete) {
        animalsService.deleteById(idToDelete);
        List<Animal> animalList = animalsService.showAllAnimals();
        return animalList;
    }

    @GetMapping("animals/{id}/organs")
    public List<OrganDto> displayOrgansOfSpecificAnimal(@PathVariable(name = "id") Long id) {
        Animal animal = animalsService.findById(id);
        List<Organ> organs = animal.getOrgans();
        List<OrganDto> organDtos = new ArrayList<>();
        for (int i = 0; i < organs.size(); i++) {
            Organ organTemp = organs.get(i);
            organDtos.add(organTemp.toDto());
        }
        return organDtos;
    }
    @PatchMapping(path = "animals/{id}/moreorgan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AnimalDto updateAnimalOrgan(@PathVariable(name = "id") Long idToUpdate, @RequestBody OrganDto organ){
        Organ organToUpdate=new Organ(organ.getName(),organ.getDescription(),organ.isVital());
        animalsService.updateOrgans(idToUpdate,organToUpdate);
        Animal animal=animalsService.findById(idToUpdate);
        AnimalDto animalDto=animal.toAnimalsDto();
        return animalDto;
    }


    @PostMapping("/uploads")
    public MultipartFile handleFileUpload(@RequestBody MultipartFile file) {

        if (file == null) {
            System.out.println("File not found");
        } else {
            System.out.println("File " + file.getOriginalFilename() + " imported");
            System.out.println("Nothing to display");
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getName());
            System.out.println(file.getSize());
            System.out.println(file.getContentType());
            imageServiceImpl.storeAvatar(file);
        }
        return file;
    }

    @GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = imageServiceImpl.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
