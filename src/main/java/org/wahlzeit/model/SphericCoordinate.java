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
        //converts spheric to cartesian representation
        public CartesianCoordinate asCartesianCoordinate(){
		double x = this.radius * Math.sin(this.phi) * Math.cos(this.theta);
		double y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
		double z = this.radius * Math.cos(this.phi);

		return new CartesianCoordinate(x,y,z);
	}

        //returns centralAngle of 2 Coordinates
        @Override
        public double getCentralAngle(Coordinate coordinate){
                SphericCoordinate coordinateSpheric = coordinate.asSphericCoordinate();

                //calculates centra angle via chord length
                double deltaX = Math.cos(this.getTheta()) * Math.cos(this.getPhi()) - Math.cos(coordinateSpheric.getTheta()) * Math.cos(coordinateSpheric.getPhi());
                double deltaY = Math.cos(this.getTheta()) * Math.sin(this.getPhi()) - Math.cos(coordinateSpheric.getTheta()) * Math.sin(coordinateSpheric.getPhi());
                double deltaZ = Math.sin(this.getTheta()) - Math.sin(coordinateSpheric.getTheta());

                double centralAngle = 2 * Math.asin(Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ)/2);
                return centralAngle;
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
