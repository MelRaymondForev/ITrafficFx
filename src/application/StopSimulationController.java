package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class StopSimulationController implements Initializable{
	
	@FXML public Button confirm;
	@FXML public Button cancel;
	
	public void CloseWindow(ActionEvent event) {
//		System.exit(0);
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	public void Confirm(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
