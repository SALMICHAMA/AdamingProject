package com.adaming.animals.service.organs;

import com.adaming.animals.entity.Organ;

import java.util.List;

public interface OrgansService {

    void saveOrgan(Organ organ);
    void addOrgan(String organName, String organDescription, boolean isVital);
    void deleteOrgan(Long id);
    void deleteOrgan(String name);
    List<Organ> showAllOrgans();
    Organ showSpecificOrgan(String organName);
    Organ showSpecificOrgan(Long id);
    void updateDescription(Long id, String newDescription);
}
