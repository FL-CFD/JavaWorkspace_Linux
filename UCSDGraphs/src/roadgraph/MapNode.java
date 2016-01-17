package roadgraph;

import java.util.ArrayList;
import java.util.List;


import geography.GeographicPoint;

public class MapNode {
	private GeographicPoint location;
	private List<MapEdge> edges;
	
	public MapNode(){
		location = null;
		edges = null;
	}
	
	public MapNode(GeographicPoint loc){
		location = loc;
		edges = new ArrayList<MapEdge>();
	}
	
	public MapNode(GeographicPoint loc, List<MapEdge> es){
		location = loc;
		edges = es;
	}
	
	/**
	 * @return the location
	 */
	public GeographicPoint getLocation() {
		return location;
	}
	/** 
	 * @param location the location to set
	 */
	public void setLocation(GeographicPoint location) {
		this.location = location;
	}
	/**
	 * @return the edges
	 */
	public List<MapEdge> getEdges() {
		return edges;
	}
	/**
	 * @param edges the edges to set
	 */
	public void setEdges(List<MapEdge> edges) {
		this.edges = edges;
	}
	
	public void addEdge(MapEdge edge){
		this.edges.add(edge);
	}
	
	
}
