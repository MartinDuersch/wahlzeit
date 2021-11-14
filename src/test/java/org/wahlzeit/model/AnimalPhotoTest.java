package org.wahlzeit.model;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class AnimalPhotoTest {

        @Test
	public void testConstructorWithAttributesInt() {
                Animal animal = Animal.getFromInt(1);
                Gender gender = Gender.getFromInt(1);

		AnimalPhoto animalPhoto = new AnimalPhoto(animal, gender);
	
	        assertTrue(animalPhoto.getAnimal().asString() == "dog");
                assertTrue(animalPhoto.getGender().asString() == "male");
	}

        @Test
	public void testConstructorWithGenderString() {
                Gender gender = Gender.getFromString("female");
		AnimalPhoto animalPhoto = new AnimalPhoto(gender);
	
                assertTrue(animalPhoto.getGender().asInt() == 2);
	        assertTrue(animalPhoto.getAnimal().asString() == "undefined");
	}

        @Test
	public void testConstructorWithAnimalString() {
                Animal animal = Animal.getFromString("cat");

		AnimalPhoto animalPhoto = new AnimalPhoto(animal);
	
	        assertTrue(animalPhoto.getAnimal().asInt() == 2);
                assertTrue(animalPhoto.getGender().asString() == "undefined");
	}

        @Test(expected = IllegalArgumentException.class)
	public void testConstructorGenderException() {
		AnimalPhoto animalPhoto = new AnimalPhoto("clownfish", "hybrid");
	}
}
