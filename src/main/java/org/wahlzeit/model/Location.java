package org.wahlzeit.model;
import java.sql.*;
import org.wahlzeit.services.DataObject;

public class Location extends DataObject{

    private int id;
    private Coordinate coordinate;
    private int coordinateId;

	/**
	 * 0 is never returned from nextValue; first value is 1
	 */
	protected static int currentId = 0;

    public Location (Coordinate coordinate) {
        this.coordinate = coordinate;
        this.coordinateId = coordinate.getId();
    }
    	/**
	 * 
	 * @methodtype constructor
	 */
	public Location(ResultSet rset) throws SQLException {
		readFrom(rset);
	}

    /**
	 * 
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

    public int getId() {
		return id;
	}

    public void setId(int id) {
		this.id = id;
        incWriteCount();
	}

    public int getCoordinateId() {
		return coordinateId;
	}

    public void setCoordinateId(int id) {
		this.coordinateId = id;
        incWriteCount();
	}

    public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
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
		coordinateId = rset.getInt("coordinate_id");
	}

    public void writeOn(ResultSet rset) throws SQLException {
		rset.updateInt("id", id);
		rset.updateInt("coordinate_id", coordinateId);	
	}

    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, id);
	}

    public static synchronized int getNextIdAsInt() {
		currentId += 1;
		return currentId;
	}
    public static synchronized int getLastLocationId() {
		return currentId;
	}
    public static synchronized void setLastLocationId(int id) {
		currentId = id;
	}
}
