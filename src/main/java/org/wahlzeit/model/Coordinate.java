package org.wahlzeit.model;

public interface Coordinate{
  //use only checked exceptions in interface 
  CartesianCoordinate asCartesianCoordinate() throws CoordinateException;
  public double getCartesianDistance(Coordinate coordinate) throws CoordinateException;
  SphericCoordinate asSphericCoordinate() throws ArithmeticException;
  public double getCentralAngle(Coordinate coordinate) throws CoordinateException;
  public boolean isEqual(Coordinate coordinate);
  public int getId();
  public void assertClassInvariants();
  public void assertInstanceOfCoordinate(Object object);
  public void assertNotNull(Object object);
}