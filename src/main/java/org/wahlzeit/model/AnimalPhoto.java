package org.wahlzeit.model;

public class AnimalPhoto extends Photo {

        public static enum AnimalType {
                Dog, Cat, Other
        }
        
        private AnimalType animalType;
        private Gender gender;

        public AnimalPhoto (AnimalType animalType, Gender gender) {
                Photo photo = new Photo();
                AnimalPhoto animalPhoto = (AnimalPhoto) photo;
                animalPhoto.animalType = animalType;
                animalPhoto.gender = gender;
        }

}
