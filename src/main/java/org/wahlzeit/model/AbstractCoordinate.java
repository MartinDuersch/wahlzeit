package org.wahlzeit.model;
import org.wahlzeit.services.DataObject;
import java.util.Objects;
import java.sql.*;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

        protected final static double EPSILON = 1e-6;
        protected static int currentId = 0;
        protected int id;

        public CartesianCoordinate asCartesianCoordinate() {
		return (CartesianCoordinate) this;
	}

        public SphericCoordinate asSphericCoordinate() {
		return (SphericCoordinate) this;
	}

        public double getCartesianDistance(Coordinate coordinate){
		return this.asCartesianCoordinate().getDistance(coordinate.asCartesianCoordinate());
	}

        @Override
        public boolean equals(Object coordinate) {
                return isEqual((Coordinate) coordinate);
        }

        @Override
        public int hashCode() {
                CartesianCoordinate cartCoord = this.asCartesianCoordinate();
                return Objects.hash(cartCoord.x, cartCoord.y, cartCoord.z);
        }
        
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


        
        private static boolean checkEqualDoubles(double d1, double d2) {
                return EPSILON > Math.abs(d1-d2);
        }

        public double getDistance(Coordinate coordinate) {
                CartesianCoordinate coordinateThis = this.asCartesianCoordinate();
                CartesianCoordinate coordinateCartesian = this.asCartesianCoordinate();

                //calculates cartesian distance
                return Math.sqrt(
                        Math.pow(coordinateThis.getX() - coordinateCartesian.getX(), 2) +
                        Math.pow(coordinateThis.getY() - coordinateCartesian.getY(), 2) +
                        Math.pow(coordinateThis.getZ() - coordinateCartesian.getZ(), 2));
        }

        public double getCentralAngle(Coordinate coordinate){
                SphericCoordinate coordinateThis = this.asSphericCoordinate();
                SphericCoordinate coordinateSpheric = coordinate.asSphericCoordinate();

                //via chord length
		double deltaX = Math.cos(coordinateThis.theta) * Math.cos(coordinateThis.phi) - Math.cos(coordinateSpheric.getTheta()) * Math.cos(coordinateSpheric.getPhi());
		double deltaY = Math.cos(coordinateThis.theta) * Math.sin(coordinateThis.phi) - Math.cos(coordinateSpheric.getTheta()) * Math.sin(coordinateSpheric.getPhi());
		double deltaZ = Math.sin(coordinateThis.theta) - Math.sin(coordinateSpheric.getTheta());

		double centralAngle = 2 * Math.asin(Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ)/2);
		return centralAngle;
	}

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
