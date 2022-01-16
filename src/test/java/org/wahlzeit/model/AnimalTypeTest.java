package org.wahlzeit.model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnimalTypeTest {
	AnimalManager animalManager;

	@Before
	public void setUp(){
		animalManager = AnimalManager.getInstance();
	}

	@After
	public void tearDown(){
		animalManager.instance = new AnimalManager();
	}

	@Test
	public void testCreateAnimalType(){
		AnimalType animalType = animalManager.createAnimalType("Dog");
		assertTrue(animalManager.getAnimalType("Dog") == animalType);
	}

	@Test
	public void testCreateAnimal(){
		AnimalType animalType = animalManager.createAnimalType("Dog");
		Animal animal = animalManager.createAnimal("Dog");
		assertTrue(animalManager.getAnimal(animal.getId()) == animal);
		assertTrue(animal.getAnimalType().getTypeName() == "Dog");
	}

	@Test
	public void testSubtype(){
		AnimalType dog = animalManager.createAnimalType("Dog");
		AnimalType labradorRetriever = animalManager.createAnimalType("Labrador Retriever");
		animalManager.addTypeInheritance(dog, labradorRetriever);

		assertTrue(labradorRetriever.isSubtype(dog));
		assertFalse(dog.isSubtype(labradorRetriever));
	}

	@Test
	public void testSupertype(){
		AnimalType dog = animalManager.createAnimalType("Dog");
		AnimalType labradorRetriever = animalManager.createAnimalType("Labrador Retriever");
		animalManager.addTypeInheritance(dog, labradorRetriever);

		assertTrue(dog.isSuperType(labradorRetriever));
		assertFalse(labradorRetriever.isSuperType(dog));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAnimalWithNotExistingType(){
		Animal animal = animalManager.createAnimal("Dog");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAnimalTypeWithExistingName(){
		AnimalType animalType1 = animalManager.createAnimalType("Dog");
		AnimalType animalType2 = animalManager.createAnimalType("Dog");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAnimalWithEmptyString(){
		Animal animal = animalManager.createAnimal("");
	}
}
