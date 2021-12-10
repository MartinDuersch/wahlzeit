package org.wahlzeit.model;

import org.junit.Test;

public class AbstractCoordinateTest {
        
        @Test(expected = IllegalArgumentException.class)
	public void testAssertNotNull() {
                CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1.0, 1.0, 1.0);
                cartesianCoordinate.assertNotNull(null);
	}

        @Test(expected = IllegalArgumentException.class)
	public void testAssertInstanceOfCoordinate() {
                User user = new User();
                CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1.0, 1.0, 1.0);
                cartesianCoordinate.assertInstanceOfCoordinate(user);
	}
}