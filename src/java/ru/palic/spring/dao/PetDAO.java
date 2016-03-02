package ru.palic.spring.dao;

import java.util.List;
import ru.palic.spring.domain.Pet;

public interface PetDAO {
    List<Pet> listAllPets();
    Pet getPetById(Integer petId);
    int addPet(Pet pet);
    boolean editPet(Pet pet);
    boolean deletePet(Pet pet);
}
