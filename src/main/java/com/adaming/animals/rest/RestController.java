package com.adaming.animals.rest;

import com.adaming.animals.entity.Animals;
import com.adaming.animals.entity.Organs;
import com.adaming.animals.service.AnimalsServiceImpl;
import com.adaming.animals.service.OrgansServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    OrgansServiceImpl organsService;
    @Autowired
    AnimalsServiceImpl animalsService;

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



}
