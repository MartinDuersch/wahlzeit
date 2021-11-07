package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PhotoTest {

        @Test
	public void testIsWiderThanHigher() {
		Photo photo1 = new Photo();
                photo1.setWidthAndHeight(200, 100);

                Photo photo2 = new Photo();
                photo2.setWidthAndHeight(100, 200);
                
                Photo photo3 = new Photo();
                photo3.setWidthAndHeight(200, 200);

                assertTrue(photo1.isWiderThanHigher());
                assertFalse(photo2.isWiderThanHigher());
                assertFalse(photo2.isWiderThanHigher());
	}

        @Test
	public void testGetThumbWidth() {
		Photo photo1 = new Photo();
                photo1.setWidthAndHeight(200, 100);

                Photo photo2 = new Photo();
                photo2.setWidthAndHeight(200, 1000);

                assertTrue(photo1.getThumbWidth() == Photo.MAX_THUMB_PHOTO_WIDTH);
                assertTrue(photo2.getThumbWidth() == 30);
	}

        @Test
	public void testGetThumbHeight() {
		Photo photo1 = new Photo();
                photo1.setWidthAndHeight(100, 200);

                Photo photo2 = new Photo();
                photo2.setWidthAndHeight(1000, 200);

                assertTrue(photo1.getThumbHeight() == Photo.MAX_THUMB_PHOTO_HEIGHT);
                assertTrue(photo2.getThumbHeight() == 21);
	}

        @Test
	public void testAddPraiseGetPraise() {
		Photo photo1 = new Photo();

                photo1.addToPraise(10);

                assertTrue(photo1.getPraise() == 10.0);
	}

}
