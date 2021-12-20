package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CartesianCoordinateTest {
     
        @Test
	public void testIsEqualAndEquals() {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
		CartesianCoordinate coordinate2 = CartesianCoordinate.getCartesianCoordinate(-4, 6, 7);
                CartesianCoordinate coordinate3 = CartesianCoordinate.getCartesianCoordinate(-4, 6, 7);
                
                assertTrue(coordinate1.isEqual(coordinate1));
                assertTrue(coordinate1.equals(coordinate1));
                
                assertTrue(coordinate2.isEqual(coordinate3));
                assertTrue(coordinate2.equals(coordinate3));
                
                assertFalse(coordinate1.isEqual(coordinate2));
                assertFalse(coordinate1.equals(coordinate2));
	}
        
        @Test
	public void testHashcode() {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
		CartesianCoordinate coordinate2 = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
                
                assertTrue(coordinate1.hashCode() == coordinate2.hashCode());
	}
        
        @Test
        public void testGetCartesianDistance () {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(0, 0, 0);
                CartesianCoordinate coordinate2 = CartesianCoordinate.getCartesianCoordinate(-4, 6, 7);
                CartesianCoordinate coordinate3 = CartesianCoordinate.getCartesianCoordinate(10, -11, 12);
                try {
                        
                        assertTrue(CartesianCoordinate.checkEqualDoubles(coordinate1.getCartesianDistance(coordinate2),Math.sqrt(101)));
                        assertTrue(CartesianCoordinate.checkEqualDoubles(coordinate1.getCartesianDistance(coordinate3),Math.sqrt(365)));
                        assertTrue(CartesianCoordinate.checkEqualDoubles(coordinate2.getCartesianDistance(coordinate3),Math.sqrt(510)));
                } catch (Exception e) {
                        //TODO: handle exception
                }
        }

        @Test
	public void testAsSphericCoordinate() {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(5, 5, 5);
		SphericCoordinate coordinate2 = SphericCoordinate.getSphericCoordinate(8.6602540378444, 0.95531661812451, 0.78539816339745);
                
                assertTrue(coordinate1.asSphericCoordinate().isEqual(coordinate2));
	}

        @Test
	public void testIsEqualAsCartesianCoordinate() {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
		CartesianCoordinate coordinate2 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
                
                assertTrue(coordinate1.asCartesianCoordinate().isEqual(coordinate2.asCartesianCoordinate()));
	}

        @Test
	public void testIsEqualAsSphericCoordinate() {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
		CartesianCoordinate coordinate2 = CartesianCoordinate.getCartesianCoordinate(1, 2, 3);
                
                assertTrue(coordinate1.asSphericCoordinate().isEqual(coordinate2.asSphericCoordinate()));
	}   

        @Test
	public void testGetCentralAngle() throws CoordinateException{
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(-4, 6, 7);
                CartesianCoordinate coordinate2 = CartesianCoordinate.getCartesianCoordinate(10, -11, 12);

                try {   
                        assertTrue(CartesianCoordinate.checkEqualDoubles(coordinate1.getCentralAngle(coordinate2),2.981677));
                } catch (Exception e) {
                        throw e;
                }
	}

        @Test(expected = IllegalArgumentException.class)
	public void testClassInvariantsNaN() {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(Double.NaN, 0, 0);
	}

        @Test(expected = IllegalArgumentException.class)
	public void testClassInvariantsInfinite() {
                CartesianCoordinate coordinate1 = CartesianCoordinate.getCartesianCoordinate(Double.NEGATIVE_INFINITY, 0, 0);
	}
}
