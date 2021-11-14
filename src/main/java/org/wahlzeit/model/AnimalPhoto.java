package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalPhoto extends Photo {
        
        private Animal animal;
        private Gender gender;

        public AnimalPhoto (Animal animal, Gender gender) {
                Photo photo = new Photo();
                AnimalPhoto animalPhoto = (AnimalPhoto) photo;
                animalPhoto.animal = animal;
                animalPhoto.gender = gender;
        }

        @Override
        public void writeOn(ResultSet rset) throws SQLException {
                rset.updateObject("gender", gender.asString());
                rset.updateObject("animal", animal.asString());
                super.writeOn(rset);
        }

        @Override
        public void readFrom(ResultSet rset) throws SQLException {
                animal = Animal.getFromString(rset.getString("animal"));
                gender = Gender.getFromString(rset.getString("gender"));
                super.readFrom(rset);
        }

        public AnimalPhoto() {
		id = PhotoId.getNextId();
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public AnimalPhoto(PhotoId myId) {
		id = myId;
		incWriteCount();
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public AnimalPhoto(ResultSet rset) throws SQLException {
		readFrom(rset);
	}


    /**
     * @return Animal return the animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * @param animal the animal to set
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * @return Gender return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
