package application;

import java.util.List;

public abstract class OsmRoad {
	public long id;
	public String name;
	boolean oneWay;
	public List<OsmNode> nodes;
	public String roadRoleForward;
	
	OsmRoad(long i, String n, List ns, boolean o, String role){
		id = i;
		name = n;
		nodes = ns;
		oneWay = o;
		roadRoleForward = role;
	}
}
