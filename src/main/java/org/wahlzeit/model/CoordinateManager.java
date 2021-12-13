package org.wahlzeit.model;
import java.io.*;
import java.sql.*;
import java.util.*;

import org.wahlzeit.main.*;
import org.wahlzeit.services.*;

public class CoordinateManager extends ObjectManager {
        /**
	 * 
	 */
	protected static final CoordinateManager instance = new CoordinateManager();

        public static final CoordinateManager getInstance() {
		return instance;
	}

        protected CartesianCoordinate createObject(ResultSet rset) throws SQLException {

		return new CartesianCoordinate(rset);
	}

        public void saveCoordinate(Coordinate coordinate) {
		CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0,0,0);
		try {
			
			cartesianCoordinate = coordinate.asCartesianCoordinate();
		} catch (Exception e) {
			//TODO: handle exception
		}
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM coordinates WHERE id = ?");
			updateObject(cartesianCoordinate, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

        public CartesianCoordinate createCoordinate(double x, double y, double z) throws Exception {
                CartesianCoordinate coordinate = new CartesianCoordinate(x, y, z);
                coordinate.setId(CartesianCoordinate.getNextIdAsInt());
		addCoordinate(coordinate);
		return coordinate;
	}

        public void addCoordinate(CartesianCoordinate coordinate) {
		int id = coordinate.getId();
		try {
			PreparedStatement stmt = getReadingStatement("INSERT INTO coordinates(id) VALUES(?)");
			createObject(coordinate, stmt, id);
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
}
