package org.wahlzeit.model;


public class AnimalPhotoManager extends PhotoManager {
	
	static AnimalPhotoManager instance = new AnimalPhotoManager();

	public AnimalPhotoManager() {
		super();
	}

	public static AnimalPhotoManager getInstance() {
		return instance;
	}

}