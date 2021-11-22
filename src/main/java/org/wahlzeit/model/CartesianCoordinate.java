package org.wahlzeit.model;
import org.wahlzeit.services.DataObject;
import java.util.Objects;
import java.security.InvalidAlgorithmParameterException;
import java.sql.*;

public class CartesianCoordinate extends DataObject implements Coordinate{
        
        private final static double EPSILON = 1e-6;
    /**
	 * 0 is never returned from nextValue; first value is 1
	 */
	protected static int currentId = 0;

        private int id;
        private double x;
	private double y;
	private double z;

        public CartesianCoordinate(double x, double y, double z) {
		this.setCartesianCoordinates(x, y, z);
	}
    public CartesianCoordinate(int id, double x, double y, double z) {
		this.setCartesianCoordinates(x, y, z);
	}
    /**
	 * 
	 * @methodtype constructor
	 */
	public CartesianCoordinate(ResultSet rset) throws SQLException {
		readFrom(rset);
	}

        public CartesianCoordinate asCartesianCoordinate(){
		return new CartesianCoordinate(this.id, this.x, this.y, this.z);
	}

    public void setCartesianCoordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        incWriteCount();
    }

    public double getCartesianDistance(Coordinate coord){
        return this.getDistance(coord.asCartesianCoordinate());
        }

    public SphericCoordinate asSphericCoordinate() {
        if (this.x == 0) {
            //throws
        }
		double r = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
		double theta = Math.acos(this.z/r);
		double phi;
        if (x > 0 && y >= 0) {
            phi = Math.atan((y / x));
        } else if (x > 0 && y < 0) {
            phi = Math.atan((y / x)) + 2*Math.PI;
        } else {
            phi = Math.atan((y / x)) + Math.PI;
        }
		return new SphericCoordinate(r, phi, theta);
	}

    public double getCentralAngle(Coordinate coordinate){
		return this.asSphericCoordinate().getCentralAngle(coordinate);
	}

    public double getDistance(CartesianCoordinate cartesianCoordinate) {
        return Math.sqrt(
            Math.pow(this.getX() - cartesianCoordinate.getX(), 2) +
            Math.pow(this.getY() - cartesianCoordinate.getY(), 2) +
            Math.pow(this.getZ() - cartesianCoordinate.getZ(), 2));
    }

    public boolean isEqual(Coordinate coordinate) {
        if (coordinate.asCartesianCoordinate() instanceof CartesianCoordinate == false) {
            return false;
        }
        if (this == coordinate) {
            return true;
        }
        var coordinateToCheck = coordinate.asCartesianCoordinate();
        return (checkEqualDoubles(this.getX(), coordinateToCheck.getX()) &&
                checkEqualDoubles(this.getY(), coordinateToCheck.getY()) &&
                checkEqualDoubles(this.getZ(), coordinateToCheck.getZ()));
    }
    

    private static boolean checkEqualDoubles(double d1, double d2) {
        return EPSILON > Math.abs(d1-d2);
    }

    @Override
    public boolean equals(Object cartesianCoordinate) {
        return isEqual((Coordinate) cartesianCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public int getId() {
		return id;
	}

    public void setId(int id) {
		this.id = id;
	}

    public double getX() {
		return x;
	}

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

	public void setX(double x) {
		this.x = x;
        incWriteCount();
	}

	public void setY(double y) {
		this.y = y;
        incWriteCount();
	}

	public void setZ(double z) {
		this.z = z;
        incWriteCount();
	}

        	/**
	 * 
	 * @methodtype get
	 */
	public String getIdAsString() {
		return String.valueOf(id);
	}

    public void readFrom(ResultSet rset) throws SQLException {
		id = rset.getInt("id");
        x = rset.getDouble("x");
        y = rset.getDouble("y");
        z = rset.getDouble("z");
	}

    public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", id);
		rset.updateDouble("x", x);	
        rset.updateDouble("y", y);	
        rset.updateDouble("z", z);	
	}

    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, id);
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
}
