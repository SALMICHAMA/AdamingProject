package com.adaming.animals.service.organs;

import com.adaming.animals.entity.Organs;

import java.util.List;

public interface OrgansService {

    public void saveOrgan(Organs organs);
    public void addOrgan(String organName, String organDescription, boolean isVital);

    public Organs deleteOrgan(String name);
    public List<Organs> showAllOrgans();
    public Organs showSpecificOrgan(String organName);
}