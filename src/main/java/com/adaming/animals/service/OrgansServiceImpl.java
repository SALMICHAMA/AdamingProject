package com.adaming.animals.service;

import com.adaming.animals.entity.Organs;
import com.adaming.animals.repository.OrgansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgansServiceImpl implements OrgansService{

    @Autowired
    OrgansRepository organsRepository;

    @Override
    public void saveOrgan(Organs organ) {
        organsRepository.save(organ);
    }


    @Override
    public void addOrgan(String organName, String organDescription) {
        Organs organ = new Organs();
        organ.setName(organName);
        organ.setDescription(organDescription);
        organ.setVital(false);

        organsRepository.save(organ);
    }


    @Override
    public void deleteOrgan(String name) {
        Organs organs =organsRepository.getOrganByName(name);
        organsRepository.delete(organs);

    }

    @Override
    public List <Organs> showAllOrgans() {
        return (List<Organs>) organsRepository.findAll();
    }


    @Override
    public Organs showSpecificOrgan(String organName) {
        return organsRepository.getOrganByName(organName);
    }


    }
