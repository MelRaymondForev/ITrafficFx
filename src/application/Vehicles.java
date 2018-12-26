package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Vehicles {
	@FXML
	private Label myVehicle;
	
	int x;
	int y;
	int width = 0;
	int height = 0;
	int speed = 0;
	String type="";
	
	public void Vehicle(int newx, int newy) {
		x = newx;
		y = newy;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public String getType() {
		return type;
	}
	
	public String getVehicleType(ActionEvent e) {
		String type = "";
		
		
		return type;
	}
	
	public int getSpeed(ActionEvent e) {
		int speed = 0;
		
		
		
		return speed;
	}
	
}
