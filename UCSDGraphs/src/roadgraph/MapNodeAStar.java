package roadgraph;



import geography.GeographicPoint;


public class MapNodeAStar extends MapNode implements Comparable<MapNodeAStar> {

	private double distGoal;
	private double distStart;
	
	public MapNodeAStar(){
		super();
		this.setDistStart(9.0E99);
		this.setDistGoal(9.0E99);
	}
	
	public MapNodeAStar(GeographicPoint loc){
		super(loc);
		this.setDistStart(9.0E99);
		this.setDistGoal(9.0E99);
	}
	
	public MapNodeAStar(MapNode mapNode) {
		this.setLocation(mapNode.getLocation());
		this.setEdges(mapNode.getEdges());
		this.setDistStart(9.0E99);
		this.setDistGoal(9.0E99);
	}

	public double getDist(GeographicPoint goal){
		return this.getLocation().distance(goal);
	}
	
	public double getDistGoal() {
		return distGoal;
	}

	public void setDistGoal(double distGoal) {
		this.distGoal = distGoal;
	}

	public double getDistStart() {
		return distStart;
	}

	public void setDistStart(double distStart) {
		this.distStart = distStart;
	}

	@Override
	public int compareTo(MapNodeAStar o) {
		return (new Double(this.getDistGoal()+this.getDistStart())).compareTo(new Double(o.getDistStart()+o.getDistGoal()));
	}
	
}
