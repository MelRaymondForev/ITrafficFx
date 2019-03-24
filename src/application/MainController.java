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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	
	@FXML private ComboBox<String> location;
	@FXML private Label selectedLocation; 
	ObservableList<String> locations = FXCollections.observableArrayList("A.C. Cortes", "H. Cortes", "Maguikay flyover",
			  "M.C. Briones", "Pacific Mall", "Parkmall",
			  "S&R Intersection", "Subangdaku flyover", 
			  "United Nations");
	
	@FXML void showLocationMenu(ActionEvent e) {
	    assert location != null : "fx:id=\"location\" was not injected: check your FXML file 'Menu.fxml'.";
	    assert selectedLocation != null : "fx:id=\"selectedLocation\" was not injected: check your FXML file 'Menu.fxml'.";

	    selectedLocation.textProperty().bind(location.getSelectionModel().selectedItemProperty());

	    location.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	      @Override public void changed(ObservableValue<? extends String> selected, String selectLoc, String newFruit) {
	        if (selectLoc != null) {
	          switch(selectLoc) {
	            case "A.C. Cortes":  
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator1.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		} 
	            	break;
	            case "H.Cortes": 
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator2.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		}
	            	break;
	            case "Maguikay flyover":   
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator3.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		}
	            	break;
	            case "M.C. Briones":   
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator4.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		}
	            	break;
	            case "Pacific Mall":   
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator5.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		}
	            	break;
	            case "Parkmall Intersection":   
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator6.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		}
	            	break;
	            case "S&R Intersection":   
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator7.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		}
	            	break;
	            case "Subangdaku flyover":  
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator8.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		}
	            	break;
	            case "United Nations":   
	            	try {
	        			FXMLLoader fx = new FXMLLoader(getClass().getResource("simulator9.fxml"));
	        			Parent root2 = (Parent) fx.load();
	        			Stage stage = new Stage();
	        			stage.setScene(new Scene(root2));
	        			stage.show();
	        		}catch(Exception event) {
	        			System.out.println("Error: Can't load window");
	        		}
	            	break;
	          }
	        } 
	      }
	    });
	}
	
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
	public void buildRoad(ActionEvent e) {
			p = new OSMParser1();	
			GraphicsContext gc1 = map.getGraphicsContext2D();
			drawLines1(gc1);
	}
	
	public OSMParser2 p2;
	//Building Road on map using OSM Nodes
	@FXML private Button load2;
	@FXML private Canvas map2;
	String location2;
	public void buildRoad2(ActionEvent e) {
			p2 = new OSMParser2();	
			GraphicsContext gc2 = map2.getGraphicsContext2D();
			drawLines2(gc2);
	}
	
	public OSMParser3 p3;
	//Building Road on map using OSM Nodes
	@FXML private Button load3;
	@FXML private Canvas map3;
	String location3;
	public void buildRoad3(ActionEvent e) {
			p3 = new OSMParser3();	
			GraphicsContext gc3 = map3.getGraphicsContext2D();
			drawLines3(gc3);
	}
	
	public OSMParser4 p4;
	//Building Road on map using OSM Nodes
	@FXML private Button load4;
	@FXML private Canvas map4;
	String location4;
	public void buildRoad4(ActionEvent e) {
			p4 = new OSMParser4();	
			GraphicsContext gc4 = map4.getGraphicsContext2D();
			drawLines4(gc4);
	}
	
	public OSMParser5 p5;
	//Building Road on map using OSM Nodes
	@FXML private Button load5;
	@FXML private Canvas map5;
	String location5;
	public void buildRoad5(ActionEvent e) {
			p5 = new OSMParser5();	
			GraphicsContext gc5 = map5.getGraphicsContext2D();
			drawLines5(gc5);
	}
	
	public OSMParser6 p6;
	//Building Road on map using OSM Nodes
	@FXML private Button load6;
	@FXML private Canvas map6;
	String location6;
	public void buildRoad6(ActionEvent e) {
			p6 = new OSMParser6();	
			GraphicsContext gc6 = map6.getGraphicsContext2D();
			drawLines6(gc6);
	}
	
	public OSMParser7 p7;
	//Building Road on map using OSM Nodes
	@FXML private Button load7;
	@FXML private Canvas map7;
	String location7;
	public void buildRoad7(ActionEvent e) {
			p7 = new OSMParser7();	
			GraphicsContext gc7 = map7.getGraphicsContext2D();
			drawLines7(gc7);
	}
	
	public OSMParser8 p8;
	//Building Road on map using OSM Nodes
	@FXML private Button load8;
	@FXML private Canvas map8;
	String location8;
	public void buildRoad8(ActionEvent e) {
			p8 = new OSMParser8();	
			GraphicsContext gc8 = map8.getGraphicsContext2D();
			drawLines8(gc8);
	}
	
	public OSMParser9 p9;
	//Building Road on map using OSM Nodes
	@FXML private Button load9;
	@FXML private Canvas map9;
	String location9;
	public void buildRoad9(ActionEvent e) {
			p9 = new OSMParser9();	
			GraphicsContext gc9 = map9.getGraphicsContext2D();
			drawLines9(gc9);
	}
	
	private void drawLines1(GraphicsContext gc) {
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
           
//            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            
        }
        System.out.println();
    }
}
	
	private void drawLines2(GraphicsContext gc2) {
	gc2.strokeLine(35, 67, 35,42);
	for(Long l:p2.roadTable.keySet()) {
        OsmRoad road = p2.roadTable.get(l);
        float x1,y1,x2,y2;
        
        System.out.println(p2.roadTable.get(l).name+ "ID: " + p2.roadTable.get(l).id);
        for (int i = 0; i < road.nodes.size()-1; i++) {
            y1=620-((road.nodes.get(i).getLat()-p2.minlat)/(p2.maxlat-p2.minlat))*620;
            x1=((road.nodes.get(i).getLon()-p2.minlon)/(p2.maxlon-p2.minlon))*1250;
            y2=620-((road.nodes.get(i+1).getLat()-p2.minlat)/(p2.maxlat-p2.minlat))*620;
            x2=((road.nodes.get(i+1).getLon()-p2.minlon)/(p2.maxlon-p2.minlon))*1250;
            
            System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
           
//            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            gc2.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            
        }
        System.out.println();
    }
}
	
	private void drawLines3(GraphicsContext gc3) {
	gc3.strokeLine(35, 67, 35,42);
	for(Long l:p3.roadTable.keySet()) {
        OsmRoad road = p3.roadTable.get(l);
        float x1,y1,x2,y2;
        
        System.out.println(p3.roadTable.get(l).name+ "ID: " + p3.roadTable.get(l).id);
        for (int i = 0; i < road.nodes.size()-1; i++) {
            y1=620-((road.nodes.get(i).getLat()-p3.minlat)/(p3.maxlat-p3.minlat))*620;
            x1=((road.nodes.get(i).getLon()-p3.minlon)/(p3.maxlon-p3.minlon))*1250;
            y2=620-((road.nodes.get(i+1).getLat()-p3.minlat)/(p3.maxlat-p3.minlat))*620;
            x2=((road.nodes.get(i+1).getLon()-p3.minlon)/(p3.maxlon-p3.minlon))*1250;
            
            System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
           
//            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            gc3.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            
        }
        System.out.println();
    }
}
	private void drawLines4(GraphicsContext gc4) {
	gc4.strokeLine(35, 67, 35,42);
	for(Long l:p4.roadTable.keySet()) {
        OsmRoad road = p4.roadTable.get(l);
        float x1,y1,x2,y2;
        
        System.out.println(p4.roadTable.get(l).name+ "ID: " + p4.roadTable.get(l).id);
        for (int i = 0; i < road.nodes.size()-1; i++) {
            y1=620-((road.nodes.get(i).getLat()-p4.minlat)/(p4.maxlat-p4.minlat))*620;
            x1=((road.nodes.get(i).getLon()-p4.minlon)/(p4.maxlon-p4.minlon))*1250;
            y2=620-((road.nodes.get(i+1).getLat()-p4.minlat)/(p4.maxlat-p4.minlat))*620;
            x2=((road.nodes.get(i+1).getLon()-p4.minlon)/(p4.maxlon-p4.minlon))*1250;
            
            System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
           
//            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            gc4.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            
        }
        System.out.println();
    }
}
	private void drawLines5(GraphicsContext gc5) {
	gc5.strokeLine(35, 67, 35,42);
	for(Long l:p5.roadTable.keySet()) {
        OsmRoad road = p5.roadTable.get(l);
        float x1,y1,x2,y2;
        
        System.out.println(p5.roadTable.get(l).name+ "ID: " + p5.roadTable.get(l).id);
        for (int i = 0; i < road.nodes.size()-1; i++) {
            y1=620-((road.nodes.get(i).getLat()-p5.minlat)/(p5.maxlat-p5.minlat))*620;
            x1=((road.nodes.get(i).getLon()-p5.minlon)/(p5.maxlon-p5.minlon))*1250;
            y2=620-((road.nodes.get(i+1).getLat()-p5.minlat)/(p5.maxlat-p5.minlat))*620;
            x2=((road.nodes.get(i+1).getLon()-p5.minlon)/(p5.maxlon-p5.minlon))*1250;
            
            System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
           
//            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            gc5.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            
        }
        System.out.println();
    }
}
	private void drawLines6(GraphicsContext gc6) {
	gc6.strokeLine(35, 67, 35,42);
	for(Long l:p6.roadTable.keySet()) {
        OsmRoad road = p6.roadTable.get(l);
        float x1,y1,x2,y2;
        
        System.out.println(p6.roadTable.get(l).name+ "ID: " + p6.roadTable.get(l).id);
        for (int i = 0; i < road.nodes.size()-1; i++) {
            y1=620-((road.nodes.get(i).getLat()-p6.minlat)/(p6.maxlat-p6.minlat))*620;
            x1=((road.nodes.get(i).getLon()-p6.minlon)/(p6.maxlon-p6.minlon))*1250;
            y2=620-((road.nodes.get(i+1).getLat()-p6.minlat)/(p6.maxlat-p6.minlat))*620;
            x2=((road.nodes.get(i+1).getLon()-p6.minlon)/(p6.maxlon-p6.minlon))*1250;
            
            System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
           
//            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            gc6.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            
        }
        System.out.println();
    }
}
	private void drawLines7(GraphicsContext gc7) {
	gc7.strokeLine(35, 67, 35,42);
	for(Long l:p7.roadTable.keySet()) {
        OsmRoad road = p7.roadTable.get(l);
        float x1,y1,x2,y2;
        
        System.out.println(p7.roadTable.get(l).name+ "ID: " + p7.roadTable.get(l).id);
        for (int i = 0; i < road.nodes.size()-1; i++) {
            y1=620-((road.nodes.get(i).getLat()-p7.minlat)/(p7.maxlat-p7.minlat))*620;
            x1=((road.nodes.get(i).getLon()-p7.minlon)/(p7.maxlon-p7.minlon))*1250;
            y2=620-((road.nodes.get(i+1).getLat()-p7.minlat)/(p7.maxlat-p7.minlat))*620;
            x2=((road.nodes.get(i+1).getLon()-p7.minlon)/(p7.maxlon-p7.minlon))*1250;
            
            System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
           
//            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            gc7.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            
        }
        System.out.println();
    }
}
	private void drawLines8(GraphicsContext gc8) {
	gc8.strokeLine(35, 67, 35,42);
	for(Long l:p8.roadTable.keySet()) {
        OsmRoad road = p8.roadTable.get(l);
        float x1,y1,x2,y2;
        
        System.out.println(p8.roadTable.get(l).name+ "ID: " + p8.roadTable.get(l).id);
        for (int i = 0; i < road.nodes.size()-1; i++) {
            y1=620-((road.nodes.get(i).getLat()-p8.minlat)/(p8.maxlat-p8.minlat))*620;
            x1=((road.nodes.get(i).getLon()-p8.minlon)/(p8.maxlon-p8.minlon))*1250;
            y2=620-((road.nodes.get(i+1).getLat()-p8.minlat)/(p8.maxlat-p8.minlat))*620;
            x2=((road.nodes.get(i+1).getLon()-p8.minlon)/(p8.maxlon-p8.minlon))*1250;
            
            System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
           
//            gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            gc8.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
            
        }
        System.out.println();
    }
}
	
	private void drawLines9(GraphicsContext gc9) {
		gc9.strokeLine(35, 67, 35,42);
		for(Long l:p9.roadTable.keySet()) {
            OsmRoad road = p9.roadTable.get(l);
            float x1,y1,x2,y2;
            
            System.out.println(p9.roadTable.get(l).name+ "ID: " + p9.roadTable.get(l).id);
            for (int i = 0; i < road.nodes.size()-1; i++) {
                y1=620-((road.nodes.get(i).getLat()-p9.minlat)/(p9.maxlat-p9.minlat))*620;
                x1=((road.nodes.get(i).getLon()-p9.minlon)/(p9.maxlon-p9.minlon))*1250;
                y2=620-((road.nodes.get(i+1).getLat()-p9.minlat)/(p9.maxlat-p9.minlat))*620;
                x2=((road.nodes.get(i+1).getLon()-p9.minlon)/(p9.maxlon-p9.minlon))*1250;
                
                System.out.println(Math.round(x1)+","+Math.round(y1)+","+Math.round(x2)+","+Math.round(y2));
               
//                gc.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
                gc9.strokeLine(Math.round(x1), Math.round(y1), Math.round(x2),Math.round(y2));
                
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