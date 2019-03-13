package application;

import java.util.ArrayList;

public class RoadController {
	
	ArrayList<Vehicles> cars = new ArrayList<Vehicles>();
    int carCount = 0;
    
    public RoadController(){
        super();    
    }
    
    public void addCar(Vehicles v){
        cars.add(v);
    }
    
    public void step() {
        for (int a = 0; a < cars.size(); a++) {
            Vehicles v = cars.get(a);
            if (collision(v.getX() + v.getSpeed(), v.getY(),  v) == false){
                v.setX(v.getX() + v.getSpeed());  
//                if (v.getX() > ROAD_WIDTH){
//                    if (collision(0, v.getY(), v) == false) {
//                        v.setX(0);
//                        carCount++;
//                    }
//                }
            }
            else {
//                if ((v.getY() > 40) && (collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false)){
//                    v.setY(v.getY() - LANE_HEIGHT);
//                }
//                else if ((v.getY() < 40 + 3 * LANE_HEIGHT) && (collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false)){
//                    v.setY(v.getY() + LANE_HEIGHT);
//                }
            }
        }
    }
    
    public boolean collision(int x, int y, Vehicles v){
        
        for (int a = 0; a < cars.size(); a++) {
            Vehicles u = cars.get(a);
            if (y == u.getY()){
                if (u.equals(v) == false){
                    if (x < u.getX() + u.getWidth() && x + v.getWidth() > u.getX()){
                        return true;
                    }
                }
            }
         }
         return false;
    }
    
    public int getCarCount() {
        return carCount;
    }
    
    public void resetCarCount() {
        carCount = 0;
    }

}
