package com.adaming.animals.rest;

import com.adaming.animals.dto.OrganDto;
import com.adaming.animals.entity.Organ;
import com.adaming.animals.service.organs.OrganServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class OrgansController {
    @Autowired
    OrganServiceImpl organsService;


    @GetMapping(path = "/organs/{id}")
    public OrganDto nameOrgan(@PathVariable(name = "id") Long idToShow) {
        Organ organ = organsService.showSpecificOrgan(idToShow);
        return organ.toDto();
    }

    @PostMapping(path = "/organs/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Organ addOrganSubmit(@RequestBody Organ organ) {
        organsService.addOrgan(organ.getName(), organ.getDescription(), organ.isVital());
        return organ;
    }


    @GetMapping("/organs")
    public List<Organ> displayAllOrgans() {
        return organsService.showAllOrgans();
    }


    @PostMapping(path = "/organs/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Organ> deleteOrgan(@PathVariable(name = "id") Long idToDelete) {
        organsService.deleteOrgan(idToDelete);
        List<Organ> organList = organsService.showAllOrgans();
        return organList;
    }

}
