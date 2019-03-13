package application.Accessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List ;

import application.Repository.Vehicle;

import java.util.ArrayList ;

public class VehicleDataAccessor {

	 // in real life, use a connection pool....
    private Connection connection ;

    public VehicleDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Vehicle> getVehicleList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from vehicle");
        ){
            List<Vehicle> vehicleList = new ArrayList<>();
            while (rs.next()) {
            	int id = Integer.parseInt(rs.getString("id"));
            	int speed = Integer.parseInt(rs.getString("speed"));
            	int height = Integer.parseInt(rs.getString("height"));
                String carType = rs.getString("carType");
                String direction = rs.getString("direction");
                Vehicle vehicle = new Vehicle(id, speed, height, carType, direction);
                vehicleList.add(vehicle);
            }
            return vehicleList ;
        } 
    }
	
}
