package application;

import java.util.List;

public class OsmMotorway extends OsmRoad{
	private int alanes,blanes;
	OsmMotorway(long i, String n, List ns, boolean o) {
		super(i, n, ns, o);
		alanes=3;
		blanes=3;
	}
	OsmMotorway(long i, String n, List ns, int a, int b, boolean o){
		super(i,n,ns,o);
		alanes = a;
		blanes = b;
	}
}
