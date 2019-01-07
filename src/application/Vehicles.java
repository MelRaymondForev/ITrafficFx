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
	
	public void setWidth(int newWidth) {
		width = newWidth;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int newHeight) {
		height = newHeight;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String newType) {
		type = newType;
	}
	
//	public String getVehicleType(ActionEvent e) {
//		String type = "";
//		
//		
//		return type;
//	}
		
}
