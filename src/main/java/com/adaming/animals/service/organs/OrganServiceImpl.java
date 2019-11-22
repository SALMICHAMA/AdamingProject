package com.adaming.animals.service.organs;

import com.adaming.animals.entity.Organ;
import com.adaming.animals.repository.OrgansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganServiceImpl implements OrgansService{

    @Autowired
    OrgansRepository organsRepository;

    @Override
    public void saveOrgan(Organ organ) {
        organsRepository.save(organ);
    }


    @Override
    public void addOrgan(String organName, String organDescription,boolean isVital) {
        Organ organ = new Organ(organName, organDescription, isVital);
        organsRepository.save(organ);
    }


    @Override
    public Organ deleteOrgan(String name) {
        Organ organ =organsRepository.getOrganByName(name);
        organsRepository.delete(organ);

        return organ;
    }

    @Override
    public List <Organ> showAllOrgans() {
        return (List<Organ>) organsRepository.findAll();
    }


    @Override
    public Organ showSpecificOrgan(String organName) {
        return organsRepository.getOrganByName(organName);
    }


    }
