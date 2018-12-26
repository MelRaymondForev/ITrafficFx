package application;

import java.util.List;

public abstract class OsmRoad {
	long id;
	String name;
	boolean oneWay;
	List<OsmNode> nodes;
	
	OsmRoad(long i, String n, List ns, boolean o){
		id = i;
		name = n;
		nodes = ns;
		oneWay=o;
	}
}
