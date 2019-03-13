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

import application.OSMParser1;
import application.OSMParser2;
import application.OSMParser3;
import application.OSMParser4;
import application.OSMParser5;
import application.OSMParser6;
import application.OSMParser7;
import application.OSMParser8;
import application.OSMParser9;
import application.OsmJunction;
import application.OsmNode;
import application.OsmRoad;
import application.OsmTrafficLight;
import application.RoadController;
import application.Vehicles;
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
import javafx.stage.Stage;



public class MainController implements Initializable, Runnable{
//	@FXML private Label myMessage; 
//	@FXML private Label simulationReport;
	
	boolean running = false;
	long startTime = 0;
	int carCount, capacity, LOS = 0;
	ArrayList<Vehicles> vehicles = new ArrayList<>();
	RoadController road = new RoadController();
	
	
	@FXML private Button start;
	 ArrayList<Vehicles> cars = new ArrayList<Vehicles>();
	public void Start(ActionEvent e) {
		 if (e.getSource().equals(start)){
	            if (running == false){
	                running = true;
	                road.resetCarCount();
	                startTime = System.currentTimeMillis();
	                Thread t = new Thread(this);
	                t.start();
	            }
	        }
	        
		System.out.println("The Simulator is running...");
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
	
	@FXML private Button pause;
	public void Pause(ActionEvent e) {
		
		if (e.getSource().equals(stop)){
            running = false;
        }
//		if (event.getSource().equals(start)){
//            if (running == false){
//                running = true;
//                road.resetCarCount();
//                startTime = System.currentTimeMillis();
//                Thread t = new Thread(this);
//                t.start();
//            }
//        }
//        if (event.getSource().equals(stop)){
//            running = false;
//        }
//        if (event.getSource().equals(auto)){
//             Automobile auto = new Automobile(0 ,30);
//             road.addCar(auto);
//            
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 480; y = y + 120) {
//                    if (road.collision(x, y, auto) == true){
//                        auto.setX(x);
//                        auto.setY(y);
//                        if (road.collision(x, y, auto) == false){
//                            frame.repaint();
//                            return;
//                        }
//                    }
//                }
//            }
//        }
//          if (event.getSource().equals(truck)){
//             Truck truck = new Truck(0, 30);
//             road.addCar(truck);
//            
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 480; y = y + 120) {
//                    if (road.collision(x, y, truck) == true){
//                        truck.setX(x);
//                        truck.setY(y);
//                        if (road.collision(x, y, truck) == false){
//                            frame.repaint();
//                            return;
//                        }
//                    }
//                }
//            }
//        }
//          if (event.getSource().equals(lambo)){
//             Lamborghini lambo = new Lamborghini(0 ,30);
//             road.addCar(lambo);
//            
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 480; y = y + 120) {
//                    if (road.collision(x, y, lambo) == true){
//                        lambo.setX(x);
//                        lambo.setY(y);
//                        if (road.collision(x, y, lambo) == false){
//                            frame.repaint();
//                            return;
//                        }
//                    }
//                }
//            }
//        }  
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
	
	public OSMParser1 p;
	//Building Road on map using OSM Nodes
	@FXML private Button load;
	@FXML private Canvas map;
	String location;
	public void buildRoad(ActionEvent e) {
			p = new OSMParser1();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	public OSMParser2 p2;
	//Building Road on map using OSM Nodes
	@FXML private Button load2;
	@FXML private Canvas map2;
	String location2;
	public void buildRoad2(ActionEvent e) {
			p2 = new OSMParser2();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	public OSMParser3 p3;
	//Building Road on map using OSM Nodes
	@FXML private Button load3;
	@FXML private Canvas map3;
	String location3;
	public void buildRoad3(ActionEvent e) {
			p3 = new OSMParser3();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	public OSMParser4 p4;
	//Building Road on map using OSM Nodes
	@FXML private Button load4;
	@FXML private Canvas map4;
	String location4;
	public void buildRoad4(ActionEvent e) {
			p4 = new OSMParser4();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	public OSMParser5 p5;
	//Building Road on map using OSM Nodes
	@FXML private Button load5;
	@FXML private Canvas map5;
	String location5;
	public void buildRoad5(ActionEvent e) {
			p5 = new OSMParser5();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	public OSMParser6 p6;
	//Building Road on map using OSM Nodes
	@FXML private Button load6;
	@FXML private Canvas map6;
	String location6;
	public void buildRoad6(ActionEvent e) {
			p6 = new OSMParser6();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	public OSMParser7 p7;
	//Building Road on map using OSM Nodes
	@FXML private Button load7;
	@FXML private Canvas map7;
	String location7;
	public void buildRoad7(ActionEvent e) {
			p7 = new OSMParser7();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	public OSMParser8 p8;
	//Building Road on map using OSM Nodes
	@FXML private Button load8;
	@FXML private Canvas map8;
	String location8;
	public void buildRoad8(ActionEvent e) {
			p8 = new OSMParser8();	
			GraphicsContext gc = map.getGraphicsContext2D();
			drawLines(gc);
	}
	
	public OSMParser9 p9;
	//Building Road on map using OSM Nodes
	@FXML private Button load9;
	@FXML private Canvas map9;
	String location9;
	public void buildRoad9(ActionEvent e) {
			p9 = new OSMParser9();	
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
               
//                gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
                gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
                
            }
            System.out.println();
        }
	}
	
	//To accept dragged vehicles into map
	public void dragndrop(ActionEvent e) {
		
		
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
	
	@FXML private Button stop;
	@FXML void Stop(ActionEvent e) {
		try {
			FXMLLoader fx = new FXMLLoader(getClass().getResource("AlertNew.fxml"));
			Parent root2 = (Parent) fx.load();
			Stage stage = new Stage();
			stage.setTitle("Alert");
			stage.setScene(new Scene(root2));
			stage.show();
		}catch(Exception event) {
			System.out.println("Error: Can't load window");
		}
//		System.out.println("The Simulator is stopping...\n\n\nStarting new Simulation soon...");
	} 
	
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
	

	
	public void randomforestclassifier() {
		
	}

	public void adaboost() {
		
	}
	
	@Override
	public void run() {
		 while(running == true){
//	            road.step();
//	            carCount = road.getCarCount();
//	            double throughputCalc = carCount / (double)(System.currentTimeMillis() - startTime);
//	            throughput.setText("Throughput: " + throughputCalc); 
//	            frame.repaint();
//	            try {
//	                Thread.sleep(100);  
//	            } catch(Exception e) {
//	                e.printStackTrace();
//	            }
	        }
		
	}

}