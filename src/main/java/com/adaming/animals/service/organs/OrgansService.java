package com.adaming.animals.service.organs;

import com.adaming.animals.entity.Organ;

import java.util.List;

public interface OrgansService {

    public void saveOrgan(Organ organ);
    public void addOrgan(String organName, String organDescription, boolean isVital);

    public Organ deleteOrgan(String name);
    public List<Organ> showAllOrgans();
    public Organ showSpecificOrgan(String organName);
}
