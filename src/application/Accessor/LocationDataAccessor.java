package application.Accessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Repository.LocationRepository;

public class LocationDataAccessor {

	 // in real life, use a connection pool....
    private Connection connection ;

    public LocationDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<LocationRepository> getLocationList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from location");
        ){
            List<LocationRepository> locationList = new ArrayList<>();
            while (rs.next()) {
            	String location = rs.getString("location");
                LocationRepository Location = new LocationRepository(location);
                locationList.add(Location);
            }
            return locationList ;
        } 
    }
	
}
