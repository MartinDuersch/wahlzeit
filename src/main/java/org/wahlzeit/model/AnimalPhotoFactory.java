package org.wahlzeit.model;
import java.sql.*;

import org.wahlzeit.services.*;

public class AnimalPhotoFactory extends PhotoFactory{
        /**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static AnimalPhotoFactory instance = null;
	
	protected AnimalPhotoFactory() {
		// do nothing
	}
	/**
	 * Public singleton access method.
	 */
	public static synchronized  AnimalPhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic AnimalPhotoFactory");
			setInstance(new AnimalPhotoFactory());
		}
		
		return instance;
	}

        /**
	 * Method to set the singleton instance of AnimalPhotoFactory.
	 */
	protected static synchronized void setInstance(AnimalPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initialize AnimalPhotoFactory twice");
		}
		
		instance = photoFactory;
	}
        /**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

        /**
	 * @methodtype factory
	 */
	public AnimalPhoto createPhoto() {
		return new AnimalPhoto();
	}
	
	/**
	 * 
	 */
	public AnimalPhoto createPhoto(PhotoId id) {
		return new AnimalPhoto(id);
	}
	
	/**
	 * 
	 */
	public AnimalPhoto createPhoto(ResultSet rs) throws SQLException {
		return new AnimalPhoto(rs);
	}
}
