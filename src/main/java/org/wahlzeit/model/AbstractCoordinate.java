package org.wahlzeit.model;
import org.wahlzeit.services.DataObject;
import java.util.Objects;
import java.sql.*;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

        protected final static double EPSILON = 1e-6;
        protected static int currentId = 0;
        protected int id;

        //casts if dynamic type is already cartesian
        public CartesianCoordinate asCartesianCoordinate() {
		return (CartesianCoordinate) this;
	}

        //casts if dynamic type is already spheric
        public SphericCoordinate asSphericCoordinate() {
		return (SphericCoordinate) this;
	}

        @Override
        public boolean equals(Object coordinate) {
                return isEqual((Coordinate) coordinate);
        }

        //hashes a coordinate via its cartesian representation
        @Override
        public int hashCode() {
                CartesianCoordinate cartCoord = this.asCartesianCoordinate();
                return Objects.hash(cartCoord.x, cartCoord.y, cartCoord.z);
        }
        
        //compares 2 Coordinates independently of their subtypes
        public boolean isEqual(Coordinate coordinate) {
                if (coordinate.asCartesianCoordinate() instanceof CartesianCoordinate == false) {
                        return false;
                }
                
                if (this == coordinate) {
                        return true;
                }
                var coordinateThis = this.asCartesianCoordinate();
                var coordinateToCompare = coordinate.asCartesianCoordinate();
                
                return (checkEqualDoubles(coordinateThis.getX(), coordinateToCompare.getX()) &&
                checkEqualDoubles(coordinateThis.getY(), coordinateToCompare.getY()) &&
                checkEqualDoubles(coordinateThis.getZ(), coordinateToCompare.getZ()));
        }
        
        //checks if double values are "close enough"
        public static boolean checkEqualDoubles(double d1, double d2) {
                return EPSILON > Math.abs(d1-d2);
        }

        //returns cartesianDistance of 2 Coordinates independently of their subtypes
        public double getCartesianDistance(Coordinate coordinate) {
                CartesianCoordinate coordinateThis = this.asCartesianCoordinate();
                CartesianCoordinate coordinateCartesian = coordinate.asCartesianCoordinate();

                return Math.sqrt(
                        Math.pow(coordinateThis.getX() - coordinateCartesian.getX(), 2) +
                        Math.pow(coordinateThis.getY() - coordinateCartesian.getY(), 2) +
                        Math.pow(coordinateThis.getZ() - coordinateCartesian.getZ(), 2));
        }

        //returns centralAngle of 2 Coordinates independently of their subtypes
        public double getCentralAngle(Coordinate coordinate){
                SphericCoordinate coordinateThis = this.asSphericCoordinate();
                SphericCoordinate coordinateSpheric = coordinate.asSphericCoordinate();

                //calculates centra angle via chord length
		double deltaX = Math.cos(coordinateThis.getTheta()) * Math.cos(coordinateThis.getPhi()) - Math.cos(coordinateSpheric.getTheta()) * Math.cos(coordinateSpheric.getPhi());
		double deltaY = Math.cos(coordinateThis.getTheta()) * Math.sin(coordinateThis.getPhi()) - Math.cos(coordinateSpheric.getTheta()) * Math.sin(coordinateSpheric.getPhi());
		double deltaZ = Math.sin(coordinateThis.getTheta()) - Math.sin(coordinateSpheric.getTheta());

		double centralAngle = 2 * Math.asin(Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ)/2);
		return centralAngle;
	}

        //loading and saving data from DB will only happen in cartesian representation
        public void readFrom(ResultSet rset) throws SQLException {
                CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		thisCartesian.id = rset.getInt("id");
                thisCartesian.x = rset.getDouble("x");
                thisCartesian.y = rset.getDouble("y");
                thisCartesian.z = rset.getDouble("z");
	}

        public void writeOn(ResultSet rset) throws SQLException {
                CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		rset.updateInt("id", thisCartesian.id);
		rset.updateDouble("x", thisCartesian.x);	
                rset.updateDouble("y", thisCartesian.y);	
                rset.updateDouble("z", thisCartesian.z);	
	}

        public static synchronized int getNextIdAsInt() {
                currentId += 1;
                return currentId;
        }

        public static synchronized int getLastCoordinateId() {
                return currentId;
        }

        public static synchronized void setLastCoordinateId(int id) {
                currentId = id;
        }

        public String getIdAsString() {
		return String.valueOf(id);
	}
}
