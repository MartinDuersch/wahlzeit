package org.wahlzeit.model;

import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.DataObject;
import java.util.Objects;
import java.security.InvalidAlgorithmParameterException;
import java.sql.*;


public interface Coordinate{

    public CartesianCoordinate asCartesianCoordinate();
    public double getCartesianDistance(Coordinate coordinate);
    public SphericCoordinate asSphericCoordinate();
    public double getCentralAngle(Coordinate coordinate);
    public boolean isEqual(Coordinate coordinate);
    public int getId();
  }