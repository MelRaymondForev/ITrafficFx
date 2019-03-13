package application;

import javafx.scene.canvas.GraphicsContext;

public class Truck extends Vehicles{
	
	public Truck(int newx, int newy) {
		super(newx, newy);
		width = 7;
		height = 0;
		speed = 0;
		direction = false;
	}
	
	 public void drawMe(GraphicsContext gc){
//		    g.setColor(Color.GREEN);
//		    g.fillRect(x, y, width, height);
	 }
	
}
