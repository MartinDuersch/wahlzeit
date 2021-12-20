package org.wahlzeit.model;
import java.sql.*;
import java.util.HashMap;

class SphericCoordinate extends AbstractCoordinate{

        private static HashMap<Integer, SphericCoordinate> valueObjects = new HashMap<Integer, SphericCoordinate>();

	protected final double radius;
        protected final double phi;
	protected final double theta;

        public static SphericCoordinate getSphericCoordinate(double radius, double phi, double theta) {
		SphericCoordinate newSphericCoordinate = new SphericCoordinate(radius, phi, theta);
		int hash = newSphericCoordinate.hashCode();

		if(valueObjects.containsKey(hash)) {
			return valueObjects.get(hash);
		} else {			
			valueObjects.put(hash, newSphericCoordinate);
			return newSphericCoordinate;
		}
	}
        
        private SphericCoordinate(double radius, double phi, double theta) {
                this.radius = radius;
                this.phi = phi;
                this.theta = theta;

                assertClassInvariants();
                incWriteCount();
        }

        public void delete() {
		int hash = this.hashCode();
		if(valueObjects.containsKey(hash)) {
			valueObjects.remove(hash);
		}
	}


        @Override
        //converts spheric to cartesian representation
        public CartesianCoordinate asCartesianCoordinate(){
                //check vor valid SphericCoordinate
                assertClassInvariants();
                
                double x = this.radius * Math.sin(this.phi) * Math.cos(this.theta);
                double y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
                double z = this.radius * Math.cos(this.phi);
                return  CartesianCoordinate.getCartesianCoordinate(x,y,z);
	}

        //returns centralAngle of 2 Coordinates
        @Override
        public double getCentralAngle(Coordinate coordinate) throws CoordinateException{
                try {
                        //check precondition:
                        assertNotNull(coordinate);
                        assertInstanceOfCoordinate(coordinate);                 

                        SphericCoordinate coordinateSpheric;

                        coordinateSpheric = coordinate.asSphericCoordinate();
                        //calculates centra angle via chord length
                        double deltaX = Math.cos(this.getTheta()) * Math.cos(this.getPhi()) - Math.cos(coordinateSpheric.getTheta()) * Math.cos(coordinateSpheric.getPhi());
                        double deltaY = Math.cos(this.getTheta()) * Math.sin(this.getPhi()) - Math.cos(coordinateSpheric.getTheta()) * Math.sin(coordinateSpheric.getPhi());
                        double deltaZ = Math.sin(this.getTheta()) - Math.sin(coordinateSpheric.getTheta());
                        
                        double centralAngle = 2 * Math.asin(Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ)/2);
                        
                        //check postcondition
                        assertValidDouble(centralAngle);
                        return centralAngle;

                } catch (Exception e) {
                        throw new CoordinateException(e, e.getMessage());
                }
                        
        }

        @Override
	public void assertClassInvariants() {
                assertValidDouble(this.radius);
                assertValidDouble(this.phi);
                assertValidDouble(this.theta);
	        assertValidRadius(this.radius);
	        return;
	}

	private void assertValidRadius(double radius) {
		if (radius <= 0) {
			throw new IllegalArgumentException("Radius must be > 0");
		}
	}

        public double getPhi() {
		return phi;
	}

        public double getTheta() {
		return theta;
	}

        public double getRadius() {
                return radius;
        }
        

        public void writeId(PreparedStatement stmt, int pos) throws SQLException {
                stmt.setInt(pos, id);
        }

        public int getId() {
		return id;
	}

        public void setId(int id) {
		this.id = id;
                assertClassInvariants();
	}

}
