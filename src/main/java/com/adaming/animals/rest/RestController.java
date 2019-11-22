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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class RestController {

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
    @GetMapping(path = "animals/{id}")
    public Animal animals_id(@PathVariable(name = "id") long idToShow) {
        Animal animal = animalsService.findById(idToShow);
        MultipartFile file = (MultipartFile) imageServiceImpl.loadAsResource(animal.getImageUrl());
        return animal;
    }

    @GetMapping("/animals")
    public List<Animal> displayAllAnimals(Model model) {
        List<Animal> list = animalsService.showAllAnimals();
        model.addAttribute("animalList", list);
        return list;
    }

    @PostMapping(path = "/animal/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public AnimalDto addAnimalSubmit(@ModelAttribute CreateAnimalDto createAnimalDto) {
        imageServiceImpl.storeAvatar(createAnimalDto.getFile());
        createAnimalDto.setImageUrl("/uploads/" + createAnimalDto.getFile().getOriginalFilename());

        Animal animal = animalsService.createAnimal(createAnimalDto.getName(), createAnimalDto.getCategory(), createAnimalDto.getEnvironment(), createAnimalDto.getImageUrl(), createAnimalDto.getOrganList());
        return animal.toAnimalsDto();
    }

    @PostMapping(path = "animals/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Animal> deleteAnimal(@PathVariable(name = "name") String animalName) {
        animalsService.deleteAnimalByName(animalName);
        List<Animal> animalList = animalsService.showAllAnimals();
        return animalList;
    }

    @GetMapping("/organs/{name}")
    public OrganDto nameOrgan(@PathVariable(name = "name") String name) {
        Organ organ = organsService.showSpecificOrgan(name);
        return organ.toDto();
    }


    @PostMapping(path = "/organs/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Organ addOrganSubmit(@RequestBody Organ organ) {
        organsService.addOrgan(organ.getName(), organ.getDescription(), organ.isVital());
        return organ;
    }

    @GetMapping("/organs")
    public List<Organ> displayAllOrgans(Model model) {
        List<Organ> organList = organsService.showAllOrgans();
        model.addAttribute("organList", organList);
        return organList;
    }

    @PostMapping(path = "/organs/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Organ> deleteOrgan(@PathVariable(name = "name") String organName) {
        Organ organ = organsService.deleteOrgan(organName);
        List<Organ> organList = organsService.showAllOrgans();
        return organList;
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
