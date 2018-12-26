package application;

public class OsmNode {
	private long id;
	private float lon,lat;
	OsmNode(long i,float n, float t){
		id = i;
		lon = n;
		lat = t;
	}
	public long getID() {
		return id;
	}
	public float getLon() {
		return lon;
	}
	public float getLat() {
		return lat;
	}

}
