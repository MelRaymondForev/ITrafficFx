package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.Vehicles;
import application.Accessor.VehicleDataAccessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public class AddVehicleController implements Initializable{

	@FXML public ComboBox<String> combobox;
	@FXML public ComboBox<String> combobox2;
	
	ObservableList<String> lists = (ObservableList<String>) FXCollections.observableArrayList("Auto", "Sedan", "SUV", "Truck");
	ObservableList<String> directions = (ObservableList<String>) FXCollections.observableArrayList("One-way", "Left-Turn", "Right-Turn", "U-Turn");
	
	public void CloseWindow(ActionEvent event) {
//		System.exit(0);
		((Node)event.getSource()).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combobox.setItems(lists);
		combobox2.setItems(directions);
	}
	
	
	@FXML public TextField speed;
	@FXML public TextField width;
	@FXML public TextField height;
	@FXML public ColorPicker carColor;
	@FXML public Button save;
	
	public void drawVehicle(GraphicsContext gc) {
		
		try {
			Vehicles vehicle = new Vehicles(0, 0);
//			vehicle.setSpeed(newSpeed);
//			vehicle.getHeight();
//			vehicle.getSpeed();
//			vehicle.getType();
//			vehicle.getColor();
//			vehicle.getDirection();
////			gc.fillRect(x, y, width, height);
//			
//			System.out.println("%d %d %d" + height + width + speed);
//			
//			
		}catch(Exception e) {
			System.out.println("There was an error in creating the vehicle.");
		}
		
		System.out.println("You've successfully created a vehicle.");
	
	}
}
