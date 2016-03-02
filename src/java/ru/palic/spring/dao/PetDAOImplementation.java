package ru.palic.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.palic.spring.domain.Pet;

@Repository
public class PetDAOImplementation implements PetDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Pet> listAllPets() {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM pet";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            List<Pet> pets = new ArrayList<>();
            while (res.next()) {
                Pet pet = new Pet();
                pet.setId(res.getInt(1));
                pet.setName(res.getString(2));
                pet.setOwner(res.getString(3));
                pet.setSpecies(res.getString(4));
                pet.setSex(res.getString(5));
                pet.setBirth(res.getDate(6));
                pet.setDeath(res.getDate(7));
                pets.add(pet);

            }
            return pets;
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured in lisPets methos", ex);
        }

    }
    
    @Override
    public  Pet getPetById(Integer petId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "Select name, owner, species, sex, birth, death FROM pet where id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, petId);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                Pet pet = new Pet();
                pet.setId(petId);
                pet.setName(res.getString(1));
                pet.setOwner(res.getString(2));
                pet.setSpecies(res.getString(3));
                pet.setSex(res.getString(4));
                pet.setBirth(res.getDate(5));
                pet.setDeath(res.getDate(6));
                return pet;
            } else {
                return null;
            }

        } catch (Exception ex) {
            throw new RuntimeException("An error has occured in getPetById method", ex);
        }
    }

    @Override
    public int addPet(Pet pet){
        try(Connection connection = dataSource.getConnection()){
            String query = "INSERT INTO PET "
                    + "(name, owner, species, sex, birth, death)"
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecies());
            stmt.setString(3, pet.getOwner());
            stmt.setString(4, pet.getSex());
            stmt.setDate(5, pet.getBirth());
            stmt.setDate(6, pet.getDeath());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
            else{
                throw new RuntimeException("An error has occured in adding data to database");
            }
        }catch(Exception ex){
            throw new RuntimeException("Ann error has occured in addPet method", ex);
        }
    }
    

    @Override
    public boolean editPet(Pet pet) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE pet SET name=?, owner=?, species=?, sex=?, birth=?, death=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecies());
            stmt.setString(3, pet.getSex());
            stmt.setDate(4, pet.getBirth());
            stmt.setDate(5, pet.getDeath());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured in editPet method", ex);
        }
    }

    @Override
    public boolean deletePet(Pet pet) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM pet where id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pet.getId());
            statement.execute();
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured in deletePet method", ex);
        }
    }
}
