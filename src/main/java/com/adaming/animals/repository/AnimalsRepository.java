package com.adaming.animals.repository;

import com.adaming.animals.entity.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsRepository extends CrudRepository<Animal, Long> {

    Animal getAnimalsByName(String name);
    void deleteAnimalsById(Long id);
    Animal getAnimalsById(Long id);



}
