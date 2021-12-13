package org.wahlzeit.model;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SphericCoordinateTest {

        
        @Test
	public void testIsEqualAndEquals() {
                SphericCoordinate coordinate2 = new CartesianCoordinate(-4, 6, 7).asSphericCoordinate();
                SphericCoordinate coordinate3 = new CartesianCoordinate(10, -11, 12).asSphericCoordinate();
                
                assertTrue(coordinate2.isEqual(coordinate2));
                assertTrue(coordinate2.equals(coordinate2));
                
                assertFalse(coordinate2.isEqual(coordinate3));
                assertFalse(coordinate2.equals(coordinate3));
                
	}
        
        @Test
	public void testHashcode() {
                SphericCoordinate coordinate1 = new SphericCoordinate(1, 0, 0);
		SphericCoordinate coordinate2 = new SphericCoordinate(1, 6, 7);
                
                assertTrue(coordinate1.hashCode() != coordinate2.hashCode());
	}
        
        @Test
	public void testIsEqualAsSphericCoordinate() {
                SphericCoordinate coordinate1 = new SphericCoordinate(1, 2, 3);
		SphericCoordinate coordinate2 = new SphericCoordinate(1, 2, 3);
                
                assertTrue(coordinate1.asSphericCoordinate().isEqual(coordinate2.asSphericCoordinate()));
	}
        
        @Test
	public void testIsEqualAsCartesianCoordinate() {
                SphericCoordinate coordinate1 = new SphericCoordinate(1, 2, 3);
		SphericCoordinate coordinate2 = new SphericCoordinate(1, 2, 3);
                
                assertTrue(coordinate1.asCartesianCoordinate().isEqual(coordinate2.asCartesianCoordinate()));
	}
        
        @Test
        public void testGetCartesianDistance() {
                SphericCoordinate coordinate1 = new CartesianCoordinate(-4, 6, 7).asSphericCoordinate();
                SphericCoordinate coordinate2 = new CartesianCoordinate(10, -11, 12).asSphericCoordinate();
                try {
                        
                        assertTrue(CartesianCoordinate.checkEqualDoubles(coordinate1.getCartesianDistance(coordinate2),Math.sqrt(510)));
                } catch (Exception e) {
                        //TODO: handle exception
                }
        }
        
        @Test
	public void testAsCartesianCoordinate() {
		SphericCoordinate coordinate1 = new SphericCoordinate(5, 30, 60);
                CartesianCoordinate coordinate2 = new CartesianCoordinate(4.705070719, 1.505812665, 0.7712572494);
                
                assertTrue(coordinate1.asCartesianCoordinate().isEqual(coordinate2));
	}

        @Test
	public void testGetCentralAngle() {
                SphericCoordinate coordinate1 = new SphericCoordinate(5, 30, 60);
                SphericCoordinate coordinate2 = new SphericCoordinate(5, 60, 30);
                try {
                        
                        assertTrue(CartesianCoordinate.checkEqualDoubles(coordinate1.getCentralAngle(coordinate2),1.288563));
                } catch (Exception e) {
                        //TODO: handle exception
                }
	}

        
        @Test(expected = IllegalArgumentException.class)
	public void testClassInvariantsNaN() {
                SphericCoordinate coordinate1 = new SphericCoordinate(Double.NaN, 0, 0);
	}

        @Test(expected = IllegalArgumentException.class)
	public void testClassInvariantsRadius() {
                SphericCoordinate coordinate1 = new SphericCoordinate(-1, 0, 0);
	}

}
