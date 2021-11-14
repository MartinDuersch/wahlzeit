package org.wahlzeit.model;

import org.wahlzeit.utils.EnumValue;

public enum Animal implements EnumValue{

        UNDEFINED(0), DOG(1), CAT(2), OTHER(3);

        private static Animal[] allValues = {
		UNDEFINED, DOG, CAT, OTHER
	};

        	/**
	 * 
	 */
	private Animal(int myValue) {
		value = myValue;
	}
	
        	/**
	 * @methodtype conversion
	 */
	public static Animal getFromString(String myAnimal) throws IllegalArgumentException {
		for (Animal animal : Animal.values()) {
			if (valueNames[animal.asInt()].equals(myAnimal)) {
				return animal;
			}
		}
		
		throw new IllegalArgumentException("invalid Animal string: " + myAnimal);
	}

        /**
	 * 
	 */
	private static final String[] valueNames = {
		"undefined", "dog", "cat", "other"
	};

        private int value;

        /**
	 * 
	 */
	public int asInt() {
		return value;
	}
	
	/**
	 * 
	 */
	public String asString() {
		return valueNames[value];
	}
	
	/**
	 * 
	 */
	public Animal[] getAllValues() {
		return allValues;
	}
	
	/**
	 * 
	 */
	public String getTypeName() {
		return "Animal";
	}
}
