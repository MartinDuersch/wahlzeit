package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AnimalPhoto extends Photo {

    private Animal animal;
    private Date createdAt;
    public AnimalPhotoManager manager;

    public AnimalPhoto(){
        super();
    }

    public AnimalPhoto(Animal animal) {
        super();
        this.animal = animal;
        assertClassInvariants();
        incWriteCount();
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        super.writeOn(rset);
        animal.writeOn(rset);
        assertClassInvariants();
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        super.readFrom(rset);
        animal.readFrom(rset);
        assertClassInvariants();

    }

    /**
     * @methodtype constructor
     */
    public AnimalPhoto(PhotoId myId) {
        id = myId;
        incWriteCount();
    }

    /**
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
}
