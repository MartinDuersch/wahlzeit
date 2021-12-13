package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalPhoto extends Photo {
        
        private Animal animal;
        private Gender gender;

        public AnimalPhoto (Animal animal, Gender gender) {
            super();
            this.id = PhotoId.getNextId();
            this.animal=animal;
            this.gender=gender;
            assertClassInvariants();
            incWriteCount();
        }

        public AnimalPhoto (String animal, String gender) {
            super();
            this.id = PhotoId.getNextId();
            this.animal=Animal.getFromString(animal);
            this.gender=Gender.getFromString(gender);
            assertClassInvariants();
            incWriteCount();
        }

        public AnimalPhoto (Animal animal) {
            super();
            this.id = PhotoId.getNextId();
            this.animal=animal;
            this.gender=Gender.getFromInt(0);
            assertClassInvariants();
            incWriteCount();
        }

        public AnimalPhoto (Gender gender) {
            super();
            this.id = PhotoId.getNextId();
            this.gender=gender;
            this.animal=Animal.getFromInt(0);
            assertClassInvariants();
            incWriteCount();
        }


        @Override
        public void writeOn(ResultSet rset) throws SQLException {
                assertClassInvariants();
                rset.updateObject("gender", gender.asString());
                rset.updateObject("animal", animal.asString());
                super.writeOn(rset);
        }

        @Override
        public void readFrom(ResultSet rset) throws SQLException {
                animal = Animal.getFromString(rset.getString("animal"));
                gender = Gender.getFromString(rset.getString("gender"));
                assertClassInvariants();
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

    @Override
    public void assertClassInvariants() {
        super.assertClassInvariants();
        Animal.assertIsValidAnimalAsInt(this.animal.asInt());
        Gender.assertIsValidGenderAsInt(this.gender.asInt());
    }

}
