package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CartesianCoordinateTest {

        @Test
	public void testGetCartesianDistance () {
                CartesianCoordinate coordinate1 = new CartesianCoordinate(0, 0, 0);
		CartesianCoordinate coordinate2 = new CartesianCoordinate(-4, 6, 7);
                CartesianCoordinate coordinate3 = new CartesianCoordinate(10, -11, 12);

                assertTrue(coordinate1.getDistance(coordinate2) == Math.sqrt(101));
                assertTrue(coordinate1.getDistance(coordinate3) == Math.sqrt(365));
                assertTrue(coordinate2.getDistance(coordinate3) == Math.sqrt(510));
	}

        @Test
	public void testIsEqualAndEquals() {
                CartesianCoordinate coordinate1 = new CartesianCoordinate(0, 0, 0);
		CartesianCoordinate coordinate2 = new CartesianCoordinate(-4, 6, 7);
                CartesianCoordinate coordinate3 = new CartesianCoordinate(-4, 6, 7);
                
                assertTrue(coordinate1.isEqual(coordinate1));
                assertTrue(coordinate1.equals(coordinate1));

                assertTrue(coordinate2.isEqual(coordinate3));
                assertTrue(coordinate2.equals(coordinate3));

                assertFalse(coordinate1.isEqual(coordinate2));
                assertFalse(coordinate1.equals(coordinate2));
	}

        @Test
	public void testHashcode() {
                CartesianCoordinate coordinate1 = new CartesianCoordinate(0, 0, 0);
		CartesianCoordinate coordinate2 = new CartesianCoordinate(0, 0, 0);
                
                assertTrue(coordinate1.hashCode() == coordinate2.hashCode());
	}

        @Test
	public void testAsSphericCoordinate() {
                CartesianCoordinate coordinate1 = new CartesianCoordinate(5, 5, 5);
		SphericCoordinate coordinate2 = new SphericCoordinate(8.6602540378444, 0.78539816339745, 0.95531661812451);
                
                assertTrue(coordinate1.asSphericCoordinate().isEqual(coordinate2));
	}

        @Test
	public void testIsEqualAsCartesianCoordinate() {
                CartesianCoordinate coordinate1 = new CartesianCoordinate(1, 2, 3);
		CartesianCoordinate coordinate2 = new CartesianCoordinate(1, 2, 3);
                
                assertTrue(coordinate1.asCartesianCoordinate().isEqual(coordinate2.asCartesianCoordinate()));
	}

        @Test
	public void testIsEqualAsSphericCoordinate() {
                CartesianCoordinate coordinate1 = new CartesianCoordinate(1, 2, 3);
		CartesianCoordinate coordinate2 = new CartesianCoordinate(1, 2, 3);
                
                assertTrue(coordinate1.asSphericCoordinate().isEqual(coordinate2.asSphericCoordinate()));
	}   
}
