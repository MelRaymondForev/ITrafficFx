package application;

import java.util.List;

public class OsmTertiary extends OsmRoad{
	private int alanes,blanes;
	OsmTertiary(long i, String n, List ns, boolean o) {
		super(i, n, ns,o);
		alanes=1;
		blanes=1;
	}
	OsmTertiary(long i, String n, List ns, int a, int b, boolean o){
		super(i,n,ns,o);
		alanes = a;
		blanes = b;
	}
}
