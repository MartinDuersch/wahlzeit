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
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
