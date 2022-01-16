package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.net.*;

public class AnimalType {
    int Id;
    protected AnimalType superType = null;
    protected Set<AnimalType> subTypes = new HashSet<AnimalType>();
    private String typeName;

    public AnimalType(String name) {
        this.typeName = name;
    }

    public Animal createInstance(int id) {
        return new Animal(id, this);
    }

    public Iterator<AnimalType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public void addSubType(AnimalType at) {
        assert (at != null) : "tried to set null sub-type";
        at.setSuperType(this);
        subTypes.add(at);
    }

    public boolean isSuperType(AnimalType at) {
        if (at.superType == this) {
            return true;
        }
        return false;
    }

    public boolean isSubtype(AnimalType at) {
        if (at == this) {
            return false;
        }
        Iterator<AnimalType> iterator = at.getSubTypeIterator();
        while (iterator.hasNext()) {
            AnimalType tmp = iterator.next();
            if (tmp.equals(this)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasInstance(Animal animal) {
        assert (animal != null) : "asked about null object";
        if (animal.getAnimalType() == this) {
            return true;
        }
        for (AnimalType type : subTypes) {
            if (type.hasInstance(animal)) {
                return true;
            }
        }
        return false;
    }

    public AnimalType getSuperType() {
        return superType;
    }

    public void setSuperType(AnimalType at) {
        this.superType = at;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
