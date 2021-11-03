package org.wahlzeit.model;
import java.io.*;
import java.sql.*;
import java.util.*;

import org.wahlzeit.main.*;
import org.wahlzeit.services.*;

public class LocationManager extends ObjectManager{
        /**
	 * 
	 */
	protected static final LocationManager instance = new LocationManager();

        public static final LocationManager getInstance() {
		return instance;
	}

        protected Location createObject(ResultSet rset) throws SQLException {
		return new Location(rset);
	}

        public void saveLocation(Location location) {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM locations WHERE id = ?");
			updateObject(location, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}

        public Location createLocation(Coordinate coordinate) throws Exception {
                Location location = new Location(coordinate);
                location.setId(Location.getNextIdAsInt());
		addLocation(location);
		return location;
	}

        public void addLocation(Location location) {
		int id = location.getId();
		try {
			PreparedStatement stmt = getReadingStatement("INSERT INTO locations(id) VALUES(?)");
			createObject(location, stmt, id);
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
        
}
