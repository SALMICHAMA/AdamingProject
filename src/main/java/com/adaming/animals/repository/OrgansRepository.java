package com.adaming.animals.repository;


import com.adaming.animals.entity.Organ;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrgansRepository extends CrudRepository<Organ,Long> {

    Organ getOrganByName(String organName);
    Organ getOrganById(Long id);
}
