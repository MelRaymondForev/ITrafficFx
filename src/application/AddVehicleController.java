package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class AddVehicleController implements Initializable{

	@FXML public ComboBox<String> combobox;
	
	ObservableList<String> lists = (ObservableList<String>) FXCollections.observableArrayList("Auto", "Sedan", "SUV", "Truck");
	
	public void CloseWindow(ActionEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combobox.setItems(lists);
		
	}
}
