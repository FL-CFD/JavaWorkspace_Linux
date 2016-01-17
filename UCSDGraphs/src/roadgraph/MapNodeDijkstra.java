package roadgraph;

import geography.GeographicPoint;

public class MapNodeDijkstra extends MapNode implements Comparable<MapNodeDijkstra> {
	
	private double distStart;
	
	public MapNodeDijkstra(){
		distStart = 9.0E99;
	}
	
	public MapNodeDijkstra(GeographicPoint loc){
		super(loc);
		distStart = 9.0E99;
	}
	
	public MapNodeDijkstra(MapNode mapNode) {
		this.setLocation(mapNode.getLocation());
		this.setEdges(mapNode.getEdges());
		this.setDistStart(9.0E99);
	}
	
	@Override
	public int compareTo(MapNodeDijkstra mapNodeDijkstra){
		return (new Double(this.distStart)).compareTo(new Double(mapNodeDijkstra.distStart));
	}

	public double getDistStart() {
		return distStart;
	}

	public void setDistStart(double distStart) {
		this.distStart = distStart;
	}
	
	
	
}
