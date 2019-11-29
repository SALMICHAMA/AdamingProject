package com.adaming.animals.service.organs;

import com.adaming.animals.entity.Animal;
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
    public void deleteOrgan(String name) {
        Organ organ =organsRepository.getOrganByName(name);
        organsRepository.delete(organ);

    }

    @Override
    public void deleteOrgan(Long id) {
        organsRepository.deleteById(id);
    }

    @Override
    public List <Organ> showAllOrgans() {
        return (List<Organ>) organsRepository.findAll();
    }


    @Override
    public Organ showSpecificOrgan(String organName) {
        return organsRepository.getOrganByName(organName);
    }

    @Override
    public Organ showSpecificOrgan(Long id) {
        return organsRepository.getOrganById(id);
    }

    @Override
    public void updateDescription(Long id, String newDescription) {
        Organ organToUpdate=organsRepository.getOrganById(id);
        organToUpdate.setDescription(newDescription);
        organsRepository.save(organToUpdate);
    }
}
