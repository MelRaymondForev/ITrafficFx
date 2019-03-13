package application;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class OSMParser8{
	enum OsmNodeType{
		DEFAULT, JUNCTION, TRAFFICLIGHT;
	}
	enum OsmRoadType{
		DEFAULT, MOTORWAY, PRIMARY, SECONDARY, TERTIARY, NONROAD;
	}
	
        Map<Long, OsmNode> nodeTable = new TreeMap();
        Map<Long, OsmRoad> roadTable = new TreeMap();
        float minlat,maxlat,minlon,maxlon ;
        ArrayList<String> lists  = new ArrayList<String>();
        
	public OSMParser8(){
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory =
		DocumentBuilderFactory.newInstance();
		
//		if (lists.equals("A.C. Cortes")) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File rawMap1 = new File("map2.osm");
			File rawMap2 = new File("parkmall.osm");
			File rawMap3 = new File("accortes.osm");
			File rawMap4 = new File("hcortes.osm");
			File rawMap5 = new File("maguikayflyover.osm");
			File rawMap6 = new File("mcbriones.osm");
			File rawMap7 = new File("pacificmall.osm");
			File rawMap8 = new File("s&r.osm");	
			File rawMap9 = new File("subangdaku.osm");
			File rawMap10 = new File("un.osm");
			File rawMap11 = new File("sample.osm");
			Document doc = builder.parse(rawMap9);
			doc.getDocumentElement().normalize();
			//System.out.println("Root: "+ doc.getDocumentElement().getNodeName());
			NodeList bounds = doc.getElementsByTagName("bounds");
                        minlat = Float.parseFloat(((Element)(bounds.item(0))).getAttribute("minlat"));
                        maxlat = Float.parseFloat(((Element)(bounds.item(0))).getAttribute("maxlat"));
                        minlon = Float.parseFloat(((Element)(bounds.item(0))).getAttribute("minlon"));
                        maxlon = Float.parseFloat(((Element)(bounds.item(0))).getAttribute("maxlon"));
                        
                        System.out.println("Map Bounds");
                        System.out.println("Lat:"+minlat+" to "+maxlat);
                        System.out.println("Lon:"+minlon+" to "+maxlon);
                        
                        NodeList nodes = doc.getElementsByTagName("node");
			
			for(int i=0;i<nodes.getLength();i++) {
				Node node = nodes.item(i);
				Element enode = (Element) node;
				//System.out.println("node_id: " + enode.getAttribute("id"));
				//System.out.println("lat: " + enode.getAttribute("lon"));
				//System.out.println("lon: " + enode.getAttribute("lat"));
				
				long nodeID=Long.parseLong(enode.getAttribute("id"));
				float nodeLon=Float.parseFloat(enode.getAttribute("lon"));
				float nodeLat=Float.parseFloat(enode.getAttribute("lat"));
				
				OsmNodeType nodeType=OsmNodeType.DEFAULT;
				NodeList nodeTags = enode.getElementsByTagName("tag");
				for(int j=0;j<nodeTags.getLength();j++) {
					Node wayTag = nodeTags.item(j);
					Element enodeTag = (Element) wayTag;
					String nodeTagKey=enodeTag.getAttribute("k");
					String nodeTagValue=enodeTag.getAttribute("v");
					
					if(nodeTagKey.equals("junction") && nodeTagValue.equals("yes"))
						nodeType=OsmNodeType.JUNCTION;
					else if(nodeTagKey.equals("highway") && nodeTagValue.equals("traffic_signals"))
						nodeType=OsmNodeType.TRAFFICLIGHT;
					
					
					//System.out.println("key:" + enodeTag.getAttribute("k")+" value:"+enodeTag.getAttribute("v"));
					
					
				}
				OsmNode newNode;
				if(nodeType==OsmNodeType.JUNCTION)
					newNode = new OsmJunction(nodeID,nodeLon,nodeLat);
				else if(nodeType==OsmNodeType.TRAFFICLIGHT)
					newNode = new OsmTrafficLight(nodeID,nodeLon,nodeLat);
				else 
					newNode = new OsmNode(nodeID,nodeLon,nodeLat);
				
				nodeTable.put(nodeID, newNode);
				//System.out.println();
				
			}
			
			//System.out.println(Long.toString(401423726));
			//System.out.println(nodeTable.get(Long.parseLong("401423726")));
			NodeList ways = doc.getElementsByTagName("way");
			
			for(int i=0;i<ways.getLength();i++) {
				Node way = ways.item(i);
				
				Element eway = (Element) way;
				//System.out.println("way_id: " + eway.getAttribute("id"));
				Long roadID = Long.parseLong(eway.getAttribute("id"));
				
				OsmRoadType roadType = OsmRoadType.DEFAULT;
				int aLanes=2;
				int bLanes=2;
				boolean oneWay=false;
				String roadName = "unnamed road";
				boolean isRoad=false;
				
				NodeList wayTags = eway.getElementsByTagName("tag");
				for(int j=0;j<wayTags.getLength();j++) {
					Node wayTag = wayTags.item(j);
					Element ewayTag = (Element) wayTag;
					
					String wayTagKey=ewayTag.getAttribute("k");
					String wayTagValue=ewayTag.getAttribute("v");
					
					if(wayTagKey.equals("highway")) {
						isRoad = true;
						if(wayTagValue.equals("motorway"))
							roadType=OsmRoadType.MOTORWAY;
						else if(wayTagValue.equals("primary"))
							roadType=OsmRoadType.PRIMARY;
						else if(wayTagValue.equals("secondary"))
							roadType=OsmRoadType.PRIMARY;
						else if(wayTagValue.equals("tertiary"))
							roadType=OsmRoadType.PRIMARY;
						else
							roadType=OsmRoadType.DEFAULT;
					}
					if(wayTagKey.equals("lanes")) {
						aLanes=Integer.parseInt(wayTagValue)/2;
						bLanes=Integer.parseInt(wayTagValue)-aLanes;
					}
					if(wayTagKey.equals("name")) {
						roadName=wayTagValue;
					}
					if(wayTagKey.equals("oneway")) {
						if(wayTagValue.equals("yes"))
							oneWay=true;
						else
							oneWay=false;
					}
					
					//System.out.println("key:" + ewayTag.getAttribute("k")+" value:"+ewayTag.getAttribute("v"));
					
				}
				
				List<OsmNode> wayOsmNodes = new ArrayList();
				
				
				//System.out.println("Nodes");
				NodeList wayNodes = eway.getElementsByTagName("nd");
				for(int j=0;j<wayNodes.getLength();j++) {
					Node wayNode = wayNodes.item(j);
					Element ewayNode = (Element) wayNode;
					
					//System.out.println("     nd_ref:" + ewayNode.getAttribute("ref"));
					wayOsmNodes.add(nodeTable.get(Long.parseLong(ewayNode.getAttribute("ref"))));
					
				}
				OsmRoad newRoad;
				if(isRoad) {
					
					
					if(roadType==roadType.MOTORWAY)
						newRoad = new OsmMotorway(roadID,roadName,wayOsmNodes,oneWay, roadName);
					else if(roadType==roadType.PRIMARY)
						newRoad = new OsmPrimary(roadID,roadName,wayOsmNodes,oneWay, roadName);	
					else if(roadType==roadType.SECONDARY)
						newRoad = new OsmSecondary(roadID,roadName,wayOsmNodes,oneWay, roadName);	
					else if(roadType==roadType.TERTIARY)
						newRoad = new OsmTertiary(roadID,roadName,wayOsmNodes,oneWay, roadName);	
					else 
						newRoad = new OsmTertiary(roadID,roadName,wayOsmNodes,oneWay, roadName);
					roadTable.put(roadID, newRoad);
				}
				
				//System.out.println();
				//System.out.println("---------");
				//System.out.println();
			}
		           
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
        

}
