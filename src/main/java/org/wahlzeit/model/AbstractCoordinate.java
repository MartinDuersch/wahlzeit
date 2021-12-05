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
                return this.asCartesianCoordinate().equals(coordinate);
        }

        //returns cartesianDistance of 2 Coordinates independently of their subtypes
        public double getCartesianDistance(Coordinate coordinate) {
                return this.asCartesianCoordinate().getCartesianDistance(coordinate);
        }

        //returns centralAngle of 2 Coordinates independently of their subtypes
        public double getCentralAngle(Coordinate coordinate){
                return this.asSphericCoordinate().getCentralAngle(coordinate);

	}

        //loading and saving data from DB will only happen in cartesian representation
        public void readFrom(ResultSet rset) throws SQLException {
                this.asCartesianCoordinate().readFrom(rset);
	}

        public void writeOn(ResultSet rset) throws SQLException {
                this.asCartesianCoordinate().writeOn(rset);	
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
