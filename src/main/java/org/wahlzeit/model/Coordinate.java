package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import java.sql.*;

public class Coordinate extends DataObject{

    /**
	 * 0 is never returned from nextValue; first value is 1
	 */
	protected static int currentId = 0;

    private int id;
    private double x;
	private double y;
	private double z;

    public Coordinate(double x, double y, double z) {
		this.setCoordinates(x, y, z);
	}
    /**
	 * 
	 * @methodtype constructor
	 */
	public Coordinate(ResultSet rset) throws SQLException {
		readFrom(rset);
	}

    public void setCoordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        incWriteCount();
    }

    public double getDistance(Coordinate coordinate) {
        return Math.sqrt(
            Math.pow(this.getX() - coordinate.getX(), 2) +
            Math.pow(this.getY() - coordinate.getY(), 2) +
            Math.pow(this.getZ() - coordinate.getZ(), 2));
    }

    public boolean isEqual(Coordinate coordinate) {
        if (coordinate instanceof Coordinate == false) {
            return false;
        } else {
            return (this.getX() == coordinate.getX() &&
                    this.getY() == coordinate.getY() &&
                    this.getZ() == coordinate.getZ());
        }
    }

    @Override
    public boolean equals(Object coordinate) {
        return isEqual((Coordinate) coordinate);
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
