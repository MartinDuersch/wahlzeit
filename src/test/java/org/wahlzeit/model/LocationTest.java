package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LocationTest {
        
        @Test
	public void testLocationConstructor () {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
		CartesianCoordinate coordinate2 = CartesianCoordinate.getCartesianCoordinate(-4, 6, 7);

                Location location1 = new Location(coordinate1);
                Location location2 = new Location(coordinate2);

                assertTrue(location1 instanceof Location);
                assertFalse(location1.equals(location2));
	}
}
