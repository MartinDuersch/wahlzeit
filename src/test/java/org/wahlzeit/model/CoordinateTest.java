package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordinateTest {

        @Test
	public void testGetDistance () {
                Coordinate coordinate1 = new Coordinate(0, 0, 0);
		Coordinate coordinate2 = new Coordinate(-4, 6, 7);
                Coordinate coordinate3 = new Coordinate(10, -11, 12);

                assertTrue(coordinate1.getDistance(coordinate2) == Math.sqrt(101));
                assertTrue(coordinate1.getDistance(coordinate3) == Math.sqrt(365));
                assertTrue(coordinate2.getDistance(coordinate3) == Math.sqrt(510));
	}

        @Test
	public void testIsEqualAndEquals() {
                Coordinate coordinate1 = new Coordinate(0, 0, 0);
		Coordinate coordinate2 = new Coordinate(-4, 6, 7);
                Coordinate coordinate3 = new Coordinate(-4, 6, 7);
                
                assertTrue(coordinate1.isEqual(coordinate1));
                assertTrue(coordinate1.equals(coordinate1));

                assertTrue(coordinate2.isEqual(coordinate3));
                assertTrue(coordinate2.equals(coordinate3));

                assertFalse(coordinate1.isEqual(coordinate2));
                assertFalse(coordinate1.equals(coordinate2));
	}



        
}
