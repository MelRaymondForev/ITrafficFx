package application;

import java.util.List;

public class OsmPrimary extends OsmRoad{
	private int alanes,blanes;
	OsmPrimary(long i, String n, List ns, boolean o) {
		super(i, n, ns,o);
		alanes=2;
		blanes=2;
	}
	OsmPrimary(long i, String n, List ns, int a, int b, boolean o){
		super(i,n,ns,o);
		alanes = a;
		blanes = b;
	}
}
