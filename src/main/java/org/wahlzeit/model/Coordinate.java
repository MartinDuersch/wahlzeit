package org.wahlzeit.model;

public class Coordinate {
    private double x;
	private double y;
	private double z;

    public Coordinate(double x, double y, double z) {
		this.setCoordinates(x, y, z);
	}

    public void setCoordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
