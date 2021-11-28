package org.wahlzeit.model;
import java.sql.*;

public class SphericCoordinate extends AbstractCoordinate{

	protected double radius;
        protected double phi;
	protected double theta;

        public SphericCoordinate(double radius, double phi, double theta) {
		this.radius = radius;
		this.phi = phi;
		this.theta = theta;
	}

        @Override
        public CartesianCoordinate asCartesianCoordinate(){
		double x = this.radius * Math.sin(this.theta) * Math.cos(this.phi);
		double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
		double z = this.radius * Math.cos(this.theta);

		return new CartesianCoordinate(x,y,z);
	}

        public double getPhi() {
		return phi;
	}

        public double getTheta() {
		return theta;
	}

        public double getRadius() {
                return radius;
        }

        public void setRadius(double radius) {
                this.radius = radius;
        }

        public void setPhi(double phi) {
                this.phi = phi;
        }

        public void setTheta(double theta) {
                this.theta = theta;
        }

        public void writeId(PreparedStatement stmt, int pos) throws SQLException {
                stmt.setInt(pos, id);
        }

        public int getId() {
		return id;
	}

        public void setId(int id) {
		this.id = id;
	}

}
