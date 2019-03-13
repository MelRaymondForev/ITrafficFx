package application.Repository;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LocationRepository {
	
	 private final StringProperty location = new SimpleStringProperty(this, "location");
	    public StringProperty locationProperty() {
	        return location ;
	    }
	    public final String getLocation() {
	        return locationProperty().get();
	    }
	    public final void setLocation(String location) {
	        locationProperty().set(location);
	    }
	    
	
	    public LocationRepository() {}

	    public LocationRepository(String location) {
	        setLocation(location);
	    }

}
