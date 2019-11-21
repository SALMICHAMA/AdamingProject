package com.adaming.animals.repository;

import com.adaming.animals.entity.Animals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface AnimalsRepository extends CrudRepository<Animals, Long> {

    Animals getAnimalsByName(String name);
    void deleteAnimalsById(Long id);
    Animals getAnimalsById(Long id);



}
