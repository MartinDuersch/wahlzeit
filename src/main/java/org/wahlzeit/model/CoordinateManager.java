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

        protected Coordinate createObject(ResultSet rset) throws SQLException {
		return new Coordinate(rset);
	}

        public void saveCoordinate(Coordinate coordinate) {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM coordinates WHERE id = ?");
			updateObject(coordinate, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

        public Coordinate createCoordinate(double x, double y, double z) throws Exception {
                Coordinate coordinate = new Coordinate(x, y, z);
                coordinate.setId(Coordinate.getNextIdAsInt());
		addCoordinate(coordinate);
		return coordinate;
	}

        public void addCoordinate(Coordinate coordinate) {
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
