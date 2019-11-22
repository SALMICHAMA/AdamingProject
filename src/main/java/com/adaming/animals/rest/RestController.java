package com.adaming.animals.rest;

import com.adaming.animals.dto.CreateAnimalDto;
import com.adaming.animals.entity.Animals;
import com.adaming.animals.entity.Organs;
import com.adaming.animals.service.AnimalsServiceImpl;
import com.adaming.animals.service.ImageService;
import com.adaming.animals.service.OrgansServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    OrgansServiceImpl organsService;
    @Autowired
    AnimalsServiceImpl animalsService;
    @Autowired
    ImageService imageService;

    /**
     *
     * @param idToShow is the id of the animal that you want to display
     * @return the animal's characteristics
     */
    @GetMapping(path = "animals/{id}")
    public Animals animals_id(@PathVariable(name = "id") long idToShow) {
        Animals animals = animalsService.findById(idToShow);
        MultipartFile file=(MultipartFile) imageService.loadAsResource(animals.getImageUrl());
        return animals;
    }

    @GetMapping("/animals")
    public List<Animals> displayAllAnimals(Model model){
        List<Animals> list=animalsService.showAllAnimals();
        model.addAttribute("animalList",list);
        return list;
    }
    @PostMapping(path = "/animal/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Animals addAnimalSubmit(@RequestBody CreateAnimalDto createAnimalDto, @RequestParam(name = "picture") MultipartFile file) {
        List<Organs> organs=createAnimalDto.getOrgansList();
        for(int i=0;i<organs.size();i++){
            Organs organToTest=organsService.showSpecificOrgan(organs.get(i).getName());
            if(organToTest==null){
                organsService.addOrgan(organs.get(i).getName(), organs.get(i).getDescription(),organs.get(i).isVital());
            }
        }
        createAnimalDto.setImageUrl("/uploads/"+file.getOriginalFilename());
        imageService.storeAvatar(file);
        Animals animals=createAnimalDto.toAnimals();
        animalsService.createAnimal(animals.getName(), animals.getCategory(),animals.getEnvironment());
        return animals;
    }

    @PostMapping(path = "animals/{name}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Animals> deleteAnimal(@PathVariable(name = "name") String animalName){
        animalsService.deleteAnimalByName(animalName);
        List<Animals> animalsList=animalsService.showAllAnimals();
        return animalsList;
    }

    @GetMapping("/organ")
    public Organs nameOrgan(@RequestParam(name = "name", required = false)  String name, Model model) {
        Organs organ = organsService.showSpecificOrgan(name);

        if (organ == null) {
            model.addAttribute("organNotFound", true);
        } else {
            model.addAttribute("organ", organ);
        }
        return organ;
    }


    @PostMapping(path = "/organs/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Organs addOrganSubmit(@RequestBody Organs organ) {
        organsService.addOrgan(organ.getName(), organ.getDescription(),organ.isVital());
        return organ;
        }

    @GetMapping("/organs")
    public List<Organs> displayAllOrgans(Model model) {
        List<Organs> organList = organsService.showAllOrgans();
        model.addAttribute("organList", organList);
        return organList;
    }

    @PostMapping (path = "/organs/{name}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Organs> deleteOrgan(@PathVariable (name="name") String organName){
        Organs organ = organsService.deleteOrgan(organName);
        List<Organs> organsList = organsService.showAllOrgans();
        return organsList ;
    }



    @PostMapping("/uploads")
    public MultipartFile handleFileUpload(@RequestBody MultipartFile file){

        if (file == null) {
            System.out.println("File not found");
        } else {
            System.out.println("File " + file.getOriginalFilename() + " imported");
            System.out.println("Nothing to display");
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getName());
            System.out.println(file.getSize());
            System.out.println(file.getContentType());
            imageService.storeAvatar(file);
        }
        return file;
    }

    @GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = imageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+ file.getFilename()+"\"")
                .body(file);
    }

}
