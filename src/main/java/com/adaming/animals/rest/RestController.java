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

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(path = "/animal/{id}")
    public AnimalDto animals_id(@PathVariable(name = "id") Long idToShow) throws IOException {
        Animal animal = animalsService.findById(idToShow);
        AnimalDto animalDto=animal.toAnimalsDto();;
        return animalDto;
    }


    @GetMapping("/animals")
    public List<CreateAnimalDto> displayAllAnimals() {
        List<Animal> list=animalsService.showAllAnimals();
        List<CreateAnimalDto> animalDtos = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            CreateAnimalDto animalDtoTemp=new CreateAnimalDto();
            animalDtoTemp.setName(list.get(i).getName());
            animalDtoTemp.setImageUrl(list.get(i).getImageUrl());
            animalDtoTemp.setCategory(list.get(i).getCategory());
            animalDtoTemp.setEnvironment(list.get(i).getEnvironment());
            animalDtoTemp.setOrganList(list.get(i).getOrgans());
            animalDtos.add(animalDtoTemp);
        }
        return animalDtos;
    }

    @PostMapping(path = "/animal/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public AnimalDto addAnimalSubmit(@ModelAttribute CreateAnimalDto createAnimalDto) {
        imageServiceImpl.storeAvatar(createAnimalDto.getFile());
        createAnimalDto.setImageUrl(createAnimalDto.getFile().getOriginalFilename());
        animalsService.createAnimal(createAnimalDto.getName(), createAnimalDto.getCategory(), createAnimalDto.getEnvironment(), createAnimalDto.getImageUrl(), createAnimalDto.getOrganList());
        Animal animal =animalsService.showSpecificAnimal(createAnimalDto.getName());
        return animal.toAnimalsDto();
    }
    @GetMapping("/animals/{filter}")
    public List<CreateAnimalDto> displayByFilter(@PathVariable(name = "filter") String filter) throws IOException {
        List<Animal> listCategory=animalsService.showAnimalsByCategory(filter);
        List<Animal> listEnvironment=animalsService.showAnimalsByEnvironment(filter);
        List<CreateAnimalDto> animalDtos=new ArrayList<>();
        if(listCategory!=null){
            for(int i=0;i<listCategory.size();i++){
                CreateAnimalDto animalDtoTemp=new CreateAnimalDto();
                animalDtoTemp.setName(listCategory.get(i).getName());
                animalDtoTemp.setImageUrl(listCategory.get(i).getImageUrl());
                animalDtoTemp.setCategory(listCategory.get(i).getCategory());
                animalDtoTemp.setEnvironment(listCategory.get(i).getEnvironment());
                animalDtoTemp.setOrganList(listCategory.get(i).getOrgans());
                animalDtos.add(animalDtoTemp);
            }

        }
        if(listEnvironment!=null){
            for(int i=0;i<listEnvironment.size();i++){
                CreateAnimalDto animalDtoTemp=new CreateAnimalDto();
                animalDtoTemp.setName(listEnvironment.get(i).getName());
                animalDtoTemp.setImageUrl(listEnvironment.get(i).getImageUrl());
                animalDtoTemp.setCategory(listEnvironment.get(i).getCategory());
                animalDtoTemp.setEnvironment(listEnvironment.get(i).getEnvironment());
                animalDtoTemp.setOrganList(listEnvironment.get(i).getOrgans());
                animalDtos.add(animalDtoTemp);
            }
        }
        return animalDtos;
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
    @GetMapping("animals/{name}/organs")
    public List<OrganDto> displayOrgansOfSpecificAnimal(@PathVariable(name = "name") String animalName) {
        Animal animal=animalsService.showSpecificAnimal(animalName);
        List<Organ> organs=animal.getOrgans();
        List<OrganDto> organDtos=new ArrayList<>();
        for(int i=0;i<organs.size();i++){
            Organ organTemp=organs.get(i);
            organDtos.add(organTemp.toDto());
        }
        return organDtos;
    }

    @PostMapping(path = "/organs/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Organ addOrganSubmit(@RequestBody Organ organ) {
        organsService.addOrgan(organ.getName(), organ.getDescription(), organ.isVital());
        return organ;
    }

//    @GetMapping("/organs")
//    public List<Organ> displayAllOrgans(Model model) {
//        List<Organ> organList = organsService.showAllOrgans();
//        model.addAttribute("organList", organList);
//        return organList;
//
//    }

    @GetMapping("/organs")
    public List<Organ> displayAllOrgans() {
        return organsService.showAllOrgans();
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
