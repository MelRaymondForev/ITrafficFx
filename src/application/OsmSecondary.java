package application;

import java.util.List;

public class OsmSecondary extends OsmRoad{
	private int alanes,blanes;
	OsmSecondary(long i, String n, List ns, boolean o) {
		super(i, n, ns,o);
		alanes=1;
		blanes=1;
	}
	OsmSecondary(long i, String n, List ns, int a, int b, boolean o){
		super(i,n,ns,o);
		alanes = a;
		blanes = b;
	}
}
