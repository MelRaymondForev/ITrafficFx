package application;

import java.awt.Graphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Vehicles {
	@FXML
	private Label myVehicle;
	
	int x;
	int y;
	int width = 3;
	int height = 0;
	int speed = 0;
	String type="";
	String color="";
	boolean direction = false;
	
	public Vehicles(int newx, int newy) {
		x = newx;
		y = newy;
	}	
	
	 public void paintMe(Graphics g){
	    }
	    
	 public int getX(){
       return x;
     }
	    
	 public void setX(int newx){
	   x = newx;
	 }
	      
	public int getY(){
	   return y;
	}
	    
	public void setY(int newy){
		y = newy;
	}
	
	public int getWidth() {
		return width;
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
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String newColor) {
		color = newColor;
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public void setDirection(boolean newDirection) {
		direction = newDirection;
	}
	
//	public String getVehicleType(ActionEvent e) {
//		String type = "";
//		
//		
//		return type;
//	}
		
}
