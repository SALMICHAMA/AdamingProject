package com.adaming.animals.rest;

import com.adaming.animals.dto.OrganDto;
import com.adaming.animals.entity.Organ;
import com.adaming.animals.service.organs.OrganServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("/organs/filter/{valueFilter}")
    public List<OrganDto> displayByFilter(@PathVariable(name = "valueFilter") String filter){
        Organ organ = organsService.showSpecificOrgan(filter);
        List<OrganDto> organDtos = new ArrayList<>();
        if (organ != null) {
                OrganDto organDto = new OrganDto(organ.getId(),organ.getName(),organ.getDescription(),organ.isVital());
            organDtos.add(organDto);
        }
        return organDtos;
    }
    @PostMapping(path = "/organs/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrganDto addOrganSubmit(@RequestBody OrganDto organdto) {
        organsService.addOrgan(organdto.getName(), organdto.getDescription(), organdto.isVital());
        Organ organ=organsService.showSpecificOrgan(organdto.getName());
        return organ.toDto();
    }

    @GetMapping("/organs")
    public List<OrganDto> displayAllOrgans() {
        List<Organ> list=organsService.showAllOrgans();
        List<OrganDto> organDtos=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OrganDto organDtoTemp = list.get(i).toDto();
            organDtos.add(organDtoTemp);
        }
        return organDtos;
    }

    @DeleteMapping(path = "/organs/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganDto> deleteOrgan(@PathVariable(name = "id") Long idToDelete) {
        organsService.deleteOrgan(idToDelete);
        return displayAllOrgans();
    }

    @PatchMapping(path = "/organs/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrganDto updateOrgan(@PathVariable(name = "id") Long idToUpdate, @RequestBody String descriptionToUpdate){
        organsService.updateDescription(idToUpdate,descriptionToUpdate);
        Organ organToUpdate=organsService.showSpecificOrgan(idToUpdate);
        OrganDto organDto=organToUpdate.toDto();
        return organDto;
    }

}