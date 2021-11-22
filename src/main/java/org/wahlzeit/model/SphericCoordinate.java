package org.wahlzeit.model;
import org.wahlzeit.services.DataObject;
import java.util.Objects;
import java.sql.*;

public class SphericCoordinate implements Coordinate{

        private final static double EPSILON = 1e-6;

        private int id;
	private double radius;
        private double phi;
	private double theta;

        public SphericCoordinate(double radius, double phi, double theta) {
		this.radius = radius;
		this.phi = phi;
		this.theta = theta;
	}
                    /**
	 * 
	 * @methodtype constructor
	 */
        public SphericCoordinate asSphericCoordinate(){
		return new SphericCoordinate(this.radius, this.phi, this.theta);
	}

        public CartesianCoordinate asCartesianCoordinate(){
		double x = this.radius * Math.sin(this.phi) * Math.cos(this.theta);
		double y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
		double z = this.radius * Math.cos(this.phi);

		return new CartesianCoordinate(x,y,z);
	}

        public double getCartesianDistance(Coordinate coordinate){
		return this.asCartesianCoordinate().getDistance(coordinate.asCartesianCoordinate());
	}

        public double getCentralAngle(Coordinate coordinate){
                //via chord length
		double deltaX = Math.cos(this.theta) * Math.cos(this.phi) - Math.cos(coordinate.asSphericCoordinate().getTheta()) * Math.cos(coordinate.asSphericCoordinate().getPhi());
		double deltaY = Math.cos(this.theta) * Math.sin(this.phi) - Math.cos(coordinate.asSphericCoordinate().getTheta()) * Math.sin(coordinate.asSphericCoordinate().getPhi());
		double deltaZ = Math.sin(this.theta) - Math.sin(coordinate.asSphericCoordinate().getTheta());

		double centralAngle = 2 * Math.asin(Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ)/2);
		return centralAngle;
	}

        @Override
	public boolean equals(Object coordinateObject) {
		return isEqual((SphericCoordinate) coordinateObject);
	}

        @Override
        public int hashCode() {
            return Objects.hash(radius, phi, theta);
        }

	public boolean isEqual(Coordinate coordinate) {
		if(coordinate.asSphericCoordinate() instanceof SphericCoordinate == false){
			return false;
		}
                if (this == coordinate) {
                        return true;
                    }
                    
                var coordinateToCheck = coordinate.asSphericCoordinate();
                return (checkEqualDoubles(this.getRadius(), coordinateToCheck.getRadius()) &&
                        checkEqualDoubles(this.getPhi(), coordinateToCheck.getPhi()) &&
                        checkEqualDoubles(this.getTheta(), coordinateToCheck.getTheta()));
	}
        private static boolean checkEqualDoubles(double d1, double d2) {
                return EPSILON > Math.abs(d1-d2);
            }

        public double getPhi() {
		return phi;
	}

        public double getTheta() {
		return theta;
	}
    /**
     * @return double return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @param phi the phi to set
     */
    public void setPhi(double phi) {
        this.phi = phi;
    }

    /**
     * @param theta the theta to set
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        stmt.setInt(pos, id);
}

public static synchronized int getNextIdAsInt() {
        CartesianCoordinate.currentId += 1;
        return CartesianCoordinate.currentId;
}

public static synchronized int getLastCoordinateId() {
        return CartesianCoordinate.currentId;
}

public static synchronized void setLastCoordinateId(int id) {
        CartesianCoordinate.currentId = id;
}
        	/**
	 * 
	 * @methodtype get
	 */
	public String getIdAsString() {
		return String.valueOf(id);
	}


        public int getId() {
		return id;
	}

    public void setId(int id) {
		this.id = id;
	}



}
