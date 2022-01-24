package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;

import javax.naming.NameNotFoundException;

//Singleton Pattern
public class AnimalManager extends ObjectManager {
    //public for unit tests
    public static AnimalManager instance = new AnimalManager();

    private int IdCounter = 0;
    private HashMap<Integer, Animal> animals = new HashMap<Integer, Animal>();
    private HashMap<String, AnimalType> animalTypes = new HashMap<String, AnimalType>();

    public static AnimalManager getInstance() {
        return instance;
    }

    public Animal createAnimal(String typeName) {
        assertIsValidString(typeName);
        assertAnimalTypeNameExists(typeName);
        AnimalType at = getAnimalType(typeName);
        Animal result = at.createInstance(IdCounter++);
        animals.put(result.getId(), result);
        return result;
    }

    public AnimalType createAnimalType (String typeName) {
        assertIsValidString(typeName);
        assertIsAnimalTypeNameDoesNotAlreadyExist(typeName);
        AnimalType at = new AnimalType(typeName);
        animalTypes.put(typeName, at);
        return at;
    }

    public void addTypeInheritance (AnimalType superAt, AnimalType subAt) {
        superAt.addSubType(subAt);
    }

    private void assertIsValidString(String string){
        if (string == null || string.isEmpty() || string.isBlank()) {
            throw new IllegalArgumentException("Given string is not valid");
        }
    }

    private void assertIsAnimalTypeNameDoesNotAlreadyExist(String typeName) {
        if (animalTypes.get(typeName) != null) {
            throw new IllegalArgumentException("Given typeName already exists");
        }
    }

    private void assertAnimalTypeNameExists(String typeName) {
        if (animalTypes.get(typeName) == null) {
            throw new IllegalArgumentException("Given typeName not found");
        }
    }

    public AnimalType getAnimalType(String typeName) {
        return animalTypes.get(typeName);
    }

    public Animal getAnimal(int Id) {
        return animals.get(Id);
    }

    @Override
    protected Persistent createObject(ResultSet rset) throws SQLException {
        return null;
    }

}
