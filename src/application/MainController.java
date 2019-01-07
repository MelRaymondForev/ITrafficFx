package application;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import application.OsmJunction;
import application.OsmNode;
import application.OsmRoad;
import application.OsmTrafficLight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;



public class MainController implements Initializable{
	@FXML private Label myMessage; 
	@FXML private Label simulationReport;
	
	@FXML private Button start;
	public void printStart(ActionEvent e) {
		System.out.println("The Simulator is running...");
	}
	
	@FXML private Button stop;
	public void printStop(ActionEvent e) {
		System.out.println("The Simulator is stopping...\n\n\nStarting new Simulation soon...");
	}
	
	@FXML private Button pause;
	public void printPause(ActionEvent e) {
		System.out.println("The Simulator is paused right now...");
	}
	
	//Selecting location 
//	@FXML private TextField location;
//	@FXML void selectLocation(ActionEvent e) {
//		
//	}
	
	@FXML private ComboBox<String> combobox;
	ObservableList<String> lists = FXCollections.observableArrayList("A.C. Cortes", "H. Cortes", "Maguikay flyover",
																							  "M.C. Briones", "Pacific Mall", "Parkmall",
																							  "S&R Intersection", "Subangdaku flyover", 
																							  "United Nations");
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combobox.setItems(lists);
	}
	
	public OSMParser p;
	//Building Road on map using OSM Nodes
	@FXML private Button load;
	@FXML private Canvas map;
	public void buildRoad(ActionEvent e) {
			p = new OSMParser();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	private void drawLines(GraphicsContext gc) {
		gc.strokeLine(35, 67, 35,42);
		for(Long l:p.roadTable.keySet()) {
            OsmRoad road = p.roadTable.get(l);
            float x1,y1,x2,y2;
            
            System.out.println(p.roadTable.get(l).name+ "ID: " + p.roadTable.get(l).id);
            for (int i = 0; i < road.nodes.size()-1; i++) {
                y1=620-((road.nodes.get(i).getLat()-p.minlat)/(p.maxlat-p.minlat))*620;
                x1=((road.nodes.get(i).getLon()-p.minlon)/(p.maxlon-p.minlon))*1250;
                y2=620-((road.nodes.get(i+1).getLat()-p.minlat)/(p.maxlat-p.minlat))*620;
                x2=((road.nodes.get(i+1).getLon()-p.minlon)/(p.maxlon-p.minlon))*1250;
                
                System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
               
                gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
                
            }
            System.out.println();
        }
	}
	
	//Show Status Report
	@FXML private Button report;
	@FXML void showReport(ActionEvent e) {
		try {
			FXMLLoader fx = new FXMLLoader(getClass().getResource("StatusReport.fxml"));
			Parent root2 = (Parent) fx.load();
			Stage stage = new Stage();
			stage.setTitle("SIMULATOR:  STATUS REPORT");
			stage.setScene(new Scene(root2));
			stage.show();
		}catch(Exception event) {
			System.out.println("Error: Can't load window");
		}
	}
	

	//Add Vehicle Window
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
	
//	
//	public void DrawShape(ActionEvent event) {
//		Rectangle vehicle = new Rectangle();
//		
//		//Setting the properties of the rectangle 
//	      vehicle.getX(); 
//	      vehicle.getY(); 
//	      vehicle.getHeight(); 
//	      vehicle.getWidth(); 
//	         
//	     
//	   }  
	
	//Get the Speed of an individual vehicle
	public int getSpeed(ActionEvent e) {
		int speed = 0;
		
		
		
		return speed;
	}
	
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