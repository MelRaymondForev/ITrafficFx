package application;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class MainController{
	@FXML private Label myMessage; 
	@FXML private Label simulationReport;
	@FXML private boolean start = false;
	@FXML private boolean stop = true;
	@FXML private boolean pause = false;
	

//	@FXML public ComboBox<String> combobox;
//	ObservableList<String> list = FXCollections.observableArrayList("Auto", "Truck", "SUV", "Sedan");
//	public void showTypes(ActionEvent e) {
//		combobox.setItems(list);
//	}
//	
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		combobox.setItems(list);
//		
//	}

	@FXML private Button button;
	@FXML void handleButtonAction(ActionEvent e) {
		try {
			FXMLLoader fx = new FXMLLoader(getClass().getResource("addVehicles.fxml"));
			Parent root1 = (Parent) fx.load();
			Stage stage = new Stage();
			stage.setTitle("Add Vehicles");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch(Exception event) {
			System.out.println("Error: Can't load window");
		}
	}
	
	
//	public void generateRandom(ActionEvent e){
//		Random rand = new Random();
//		int myrand = rand.nextInt(150) + 1;
//		myMessage.setText(Integer.toString(myrand));
//		System.out.println(Integer.toString(myrand));
//	}
	
	ArrayList<Vehicles> vehicles = new ArrayList<>();
	public int carCount = 0;
	public int capacity = 0;
	public int LOS = 0;
	public int lane = 0;
	
	//Calculate Average Speed of Vehicle in a lane
	public int calculateAvgSpeed(ActionEvent e) {
		int totalSpeed, AvS = 0;
		 for (int a = 0; a < vehicles.size(); a++) {
	            Vehicles v = vehicles.get(a);
	            	totalSpeed = v.getSpeed();
	            	AvS = totalSpeed/vehicles.size();
	            	carCount++;
	        }	
		return AvS;
	}
	
	
	
	//Calculate LOS
	public int calculateLOS(ActionEvent e) {
		
//		while(running) {
//			if (lane.LOS < 10) {
//				System.out.println("LOS of lane " +  simulator.getLane() + "is A with a value of " + lane.LOS());
//			}
//			if (lane.LOS > 10 && lane.LOS < 20) {
//				System.out.println("LOS of lane " +  simulator.getLane() + "is A with a value of " + lane.LOS());
//			}
//			if (lane.LOS > 20 && lane.LOS < 35) {
//				System.out.println("LOS of lane " +  simulator.getLane() + "is A with a value of " + lane.LOS());
//			}
//			if (lane.LOS > 35 && lane.LOS < 55) {
//				System.out.println("LOS of lane " +  simulator.getLane() + "is A with a value of " + lane.LOS());
//			}
//			if (lane.LOS > 55 && lane.LOS < 80) {
//				System.out.println("LOS of lane " +  simulator.getLane() + "is A with a value of " + lane.LOS());
//			}
//			if (lane.LOS > 80) {
//				System.out.println("LOS of lane " +  simulator.getLane() + "is A with a value of " + lane.LOS());
//			}
//			
//			return LOS;
//	}
		return LOS;
		
		
	}
	
	//Calculate Right of Way
	public void rightOfWay(ActionEvent e) {
		
	}
	
	//Calculate Lane Capacity
	public int laneCapacity(ActionEvent e) {
		
		return capacity;
	}
	
	public int carCount() {
		return carCount;
	}
	
	public void resetcarCount() {
		carCount = 0;
	}	

}