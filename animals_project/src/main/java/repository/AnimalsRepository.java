package repository;

import entity.Animals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalsRepository extends CrudRepository<Animals,Long> {


    void getAnimalsByName(String name);
    void deleteAnimal(Animals animals);
    List<Animals> findAll();
    void getAnimalByGroup(String group);
}
