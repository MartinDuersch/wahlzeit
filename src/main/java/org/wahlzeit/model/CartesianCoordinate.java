package org.wahlzeit.model;
import java.sql.*;
import java.util.HashMap;

@PatternInstance(
	patternName = "Value Object",
	participants = {
		"CartesianCoordinate",
	}
)
public class CartesianCoordinate extends AbstractCoordinate{

	private static HashMap<Integer, CartesianCoordinate> valueObjects = new HashMap<Integer, CartesianCoordinate>();
        
    	protected final double x;
	protected final double y;
	protected final double z;

	public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		CartesianCoordinate newCartesianCoordinate = new CartesianCoordinate(x, y, z);
		int hash = newCartesianCoordinate.hashCode();

		if(valueObjects.containsKey(hash)) {
			return valueObjects.get(hash);
		} else {			
			valueObjects.put(hash, newCartesianCoordinate);
			return newCartesianCoordinate;
		}
	}
	
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
		incWriteCount();
	}
	
	public void delete() {
		int hash = this.hashCode();
		if(valueObjects.containsKey(hash)) {
			valueObjects.remove(hash);
		}
	}

	public CartesianCoordinate(ResultSet rset) throws SQLException {
		this.id = rset.getInt("id");
		this.x = rset.getDouble("x");
		this.y = rset.getDouble("y");
		this.z = rset.getDouble("z");
	}
	
	//converts cartesian to spheric representation
	@Override
	public SphericCoordinate asSphericCoordinate() throws ArithmeticException{
		//check vor valid CartesianCoordinate
		assertClassInvariants();

		double r = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));

		double phi;
		phi = Math.acos(this.z/r);

		// if (r <= EPSILON) {
		// 	return new SphericCoordinate(0, 0, 0);
		// }

		double theta = Math.atan2(y, x);
		
		//class invariants of SphericCoordinate will be checked in constructor
		return SphericCoordinate.getSphericCoordinate(r, phi, theta);
	}

	//returns cartesianDistance of 2 Coordinates
	@Override
	public double getCartesianDistance(Coordinate coordinate) throws CoordinateException{
		try {			
			//check precondition:
			assertNotNull(coordinate);
			assertInstanceOfCoordinate(coordinate);

			CartesianCoordinate coordinateCartesian = coordinate.asCartesianCoordinate();


			double result = Math.sqrt(
				Math.pow(this.getX() - coordinateCartesian.getX(), 2) +
				Math.pow(this.getY() - coordinateCartesian.getY(), 2) +
				Math.pow(this.getZ() - coordinateCartesian.getZ(), 2));

			return result;
		} catch (Exception e) {
			throw new CoordinateException(e, e.getMessage());
		}
        }
	
	@Override
	public boolean isEqual (Coordinate coordinate){
		//check precondition:
		try {
			assertNotNull(coordinate);
			assertInstanceOfCoordinate(coordinate);

		CartesianCoordinate coordinateToCompare = coordinate.asCartesianCoordinate();
		return (checkEqualDoubles(this.getX(), coordinateToCompare.getX()) &&
                	checkEqualDoubles(this.getY(), coordinateToCompare.getY()) &&
                	checkEqualDoubles(this.getZ(), coordinateToCompare.getZ()));

                } catch (Exception exception) {
                        return false;
                }
        	
	}

	//checks if double values are "close enough"
	public static boolean checkEqualDoubles(double d1, double d2) {
		return EPSILON > Math.abs(d1-d2);
	}

	//loading and saving data from DB will only happen in cartesian representation
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		thisCartesian.id = rset.getInt("id");
		// thisCartesian.x = rset.getDouble("x");
		// thisCartesian.y = rset.getDouble("y");
		// thisCartesian.z = rset.getDouble("z");
		assertClassInvariants();
	}

	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
		rset.updateInt("id", thisCartesian.id);
		rset.updateDouble("x", thisCartesian.x);	
		rset.updateDouble("y", thisCartesian.y);	
		rset.updateDouble("z", thisCartesian.z);	
	}

	@Override
	public void assertClassInvariants() {
	    assertValidDouble(this.x);
	    assertValidDouble(this.y);
	    assertValidDouble(this.z);
	    return;
	}

	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, id);
	}

	public int getId() {
		return id;
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
	
	public void setId(int id) {
		this.id = id;
		incWriteCount();
		assertClassInvariants();
	}
}
