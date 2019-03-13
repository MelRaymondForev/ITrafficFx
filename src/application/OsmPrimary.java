package application;

import java.util.List;

public class OsmPrimary extends OsmRoad{
	private int alanes,blanes;
	OsmPrimary(long i, String n, List ns, boolean o, String role) {
		super(i, n, ns,o,role);
		alanes=2;
		blanes=2;
	}
	OsmPrimary(long i, String n, List ns, int a, int b, boolean o, String role){
		super(i,n,ns,o,role);
		alanes = a;
		blanes = b;
	}
}
