package org.wahlzeit.model;

import org.wahlzeit.utils.EnumValue;
import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.net.*;

public class Animal {
    private int id;
    private AnimalType animalType;
    private String name;
    private Gender gender;
    private Set<AnimalPhoto> photos;
    public AnimalManager manager;
    private Location location;

    public Animal(int id, AnimalType at) {
        this.id = id;
        this.animalType = at;
    }

    public Animal(int id, AnimalType at, String name) {
        this.id = id;
        this.animalType = at;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<AnimalPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<AnimalPhoto> photos) {
        this.photos = photos;
    }

    public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", id);
		rset.updateInt("animal_type_id", animalType.getId());
		rset.updateString("gender", gender.asString());
		rset.updateInt("location_id", location.getId());
	}

    public void readFrom(ResultSet rset) throws SQLException {
		id = rset.getInt("id");
        gender = gender.getFromString(rset.getString("gender"));
        //TODO: implement read from different table to set location object
        //location = rset.getObjectByObjectId("location_id")
        rset.updateInt("animal_type_id", animalType.getId());

	}
}
