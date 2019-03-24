package application;
	
import application.Accessor.VehicleDataAccessor;
import application.Repository.Vehicle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Main extends Application {
	 private VehicleDataAccessor vehicleDataAccessor ;
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			vehicleDataAccessor = new VehicleDataAccessor(...); // provide driverName, dbURL, user, password...
//
//	        TableView<Vehicle> vehicleTable = new TableView<>();
//	        TableColumn<Vehicle, String> speedCol = new TableColumn<>("ID");
//	        speedCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//	        TableColumn<Vehicle, String> idCol = new TableColumn<>("Speed");
//	        speedCol.setCellValueFactory(new PropertyValueFactory<>("speed"));
//	        TableColumn<Vehicle, String> heightCol = new TableColumn<>("Height");
//	        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
//	        TableColumn<Vehicle, String> carTypeCol = new TableColumn<>("Car Type");
//	        carTypeCol.setCellValueFactory(new PropertyValueFactory<>("carType"));
//	        TableColumn<Vehicle, String> directionCol = new TableColumn<>("Directione");
//	        directionCol.setCellValueFactory(new PropertyValueFactory<>("direction"));
//
//	        vehicleTable.getColumns().addAll(idCol, speedCol, heightCol, carTypeCol, directionCol);
//
//	        vehicleTable.getItems().addAll(vehicleDataAccessor.getVehicleList());

			Parent root = FXMLLoader.load(getClass().getResource("/application/simulator.fxml"));
//			Parent root = FXMLLoader.load(getClass().getResource("/application/Menu.fxml"));
			Scene scene = new Scene(root,1750,950);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e->Platform.exit());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	 @Override
	 public void stop() throws Exception {
	     if (vehicleDataAccessor != null) {
	         vehicleDataAccessor.shutdown();
	       }
	    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
