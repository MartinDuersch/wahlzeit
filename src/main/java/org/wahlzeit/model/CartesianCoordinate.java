package org.wahlzeit.model;
import java.util.Objects;
import java.sql.*;

public class CartesianCoordinate extends AbstractCoordinate{
        
    /**
	 * 0 is never returned from nextValue; first value is 1
	 */
	protected static int currentId = 0;
    
    protected double x;
	protected double y;
	protected double z;

    public CartesianCoordinate(double x, double y, double z) {
		this.setCartesianCoordinates(x, y, z);
	}
    public CartesianCoordinate(int id, double x, double y, double z) {
		this.setCartesianCoordinates(x, y, z);
	}

	public CartesianCoordinate(ResultSet rset) throws SQLException {
		readFrom(rset);
	}
    
    @Override
    public SphericCoordinate asSphericCoordinate() throws ArithmeticException{
        double r = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
		double theta = Math.acos(this.z/r);
        
        if (r <= EPSILON) {
            return new SphericCoordinate(0, 0, 0);
        }
        
		double phi = Math.atan2(y, x);
		return new SphericCoordinate(r, phi, theta);
	}
    
    public void setCartesianCoordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        incWriteCount();
    }


    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, id);
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
}
