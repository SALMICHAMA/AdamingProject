package com.adaming.animals.repository;

import com.adaming.animals.entity.Animals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsRepository extends CrudRepository<Animals, Long> {

    Animals getAnimalsByName(String name);

}
