package com.adaming.animals.rest;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return animals;
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
