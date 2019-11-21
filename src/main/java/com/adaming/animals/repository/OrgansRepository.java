package com.adaming.animals.repository;


import com.adaming.animals.entity.Organs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrgansRepository extends CrudRepository<Organs,Long> {

    Organs getOrganByName(String organName);
}
