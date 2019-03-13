package application;

import java.util.List;

public class OsmTertiary extends OsmRoad{
	private int alanes,blanes;
	OsmTertiary(long i, String n, List ns, boolean o, String role) {
		super(i, n, ns,o,role);
		alanes=1;
		blanes=1;
	}
	OsmTertiary(long i, String n, List ns, int a, int b, boolean o, String role){
		super(i,n,ns,o,role);
		alanes = a;
		blanes = b;
	}
}
