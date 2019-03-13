package application.Repository;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehicle {
	
	private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
	    public IntegerProperty idProperty() {
	        return id ;
	    }
	    public final int getID() {
	        return idProperty().get();
	    }
	    public final void setID(int id) {
	        idProperty().set(id);
	    }
	
	 private final IntegerProperty speed = new SimpleIntegerProperty(this, "speed");
	    public IntegerProperty speedProperty() {
	        return speed ;
	    }
	    public final int getSpeed() {
	        return speedProperty().get();
	    }
	    public final void setSpeed(int speed) {
	        speedProperty().set(speed);
	    }
	    
	 private final IntegerProperty height = new SimpleIntegerProperty(this, "height");
	    public IntegerProperty heightProperty() {
	        return height ;
	    }
	    public final int getHeight() {
	        return heightProperty().get();
	    }
	    public final void setHeight(int height) {
	        heightProperty().set(height);
	    }

	 private final StringProperty carType = new SimpleStringProperty(this, "carType");
	    public StringProperty carTypeProperty() {
	        return carType ;
	    }
	    public final String getCarType() {
	        return carTypeProperty().get();
	    }
	    public final void setCarType(String carType) {
	        carTypeProperty().set(carType);
	    }

	 private final StringProperty direction = new SimpleStringProperty(this, "direction");
	    public StringProperty directionProperty() {
	        return direction ;
	    }
	    public final String getDirection() {
	        return directionProperty().get();
	    }
	    public final void setDirection(String direction) {
	        directionProperty().set(direction);
	    }

	    public Vehicle() {}

	    public Vehicle(int id, int speed, int height, String carType, String direction) {
	    	setID(id);
	    	setSpeed(speed);
	        setHeight(height);
	        setCarType(carType);
	        setDirection(direction);
	    }

}
