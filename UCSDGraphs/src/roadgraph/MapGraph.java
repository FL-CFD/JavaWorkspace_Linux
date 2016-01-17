/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
//import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

//import com.sun.javafx.collections.MappingChange.Map;

import geography.GeographicPoint;
import util.GraphLoader;


/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 2
	HashMap<GeographicPoint, MapNode> vertexMap;
	
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		vertexMap = new HashMap<GeographicPoint,MapNode>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 2
		return vertexMap.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 2
		Set<GeographicPoint> geoSet = new HashSet<GeographicPoint>();
		/*
		Iterator<java.util.Map.Entry<GeographicPoint, MapNode>> iterator = vertexMap.entrySet().iterator();
		while(iterator.hasNext()){
			java.util.Map.Entry<GeographicPoint, MapNode> entry = iterator.next();
			geoSet.add(entry.getKey());
		}
		*/
		Set<Map.Entry<GeographicPoint, MapNode>> set = vertexMap.entrySet();
		
		for(Map.Entry<GeographicPoint, MapNode> entry: set){
			geoSet.add(entry.getKey());
		}
		return geoSet;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 2
		int numEdges = 0;
		Iterator<java.util.Map.Entry<GeographicPoint, MapNode>> iterator = vertexMap.entrySet().iterator();
		while(iterator.hasNext()){
			java.util.Map.Entry<GeographicPoint, MapNode> entry = iterator.next();
			MapNode mNode = entry.getValue();
			numEdges += mNode.getEdges().size();
		}
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 2
		if(location == null) return false;
		
		Iterator<java.util.Map.Entry<GeographicPoint, MapNode>> iterator = vertexMap.entrySet().iterator();
		while(iterator.hasNext()){
			java.util.Map.Entry<GeographicPoint, MapNode> entry = iterator.next();
			GeographicPoint geographicPoint = entry.getKey();
			if(geographicPoint.equals(location)) return false;
		}
		
		MapNode mNode = new MapNode(location);
		vertexMap.put(location, mNode);
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 2

		if(from==null||to==null||roadName==null||roadType==null||length<0){
			throw new IllegalArgumentException();
		}
		if(vertexMap.get(from)==null || vertexMap.get(to)==null){
			throw new IllegalArgumentException();
		}
		
		MapEdge mapEdge = new MapEdge(from, to, roadName, roadType, length);
		
		Set<Map.Entry<GeographicPoint, MapNode>> set = vertexMap.entrySet();
		
		for(Map.Entry<GeographicPoint, MapNode> entry: set){
			if(entry.getKey().equals(from)){
				entry.getValue().addEdge(mapEdge);
			}
		}
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		if(vertexMap.get(start)==null||vertexMap.get(goal)==null) return null;
		
		List<GeographicPoint> path = new ArrayList<GeographicPoint>();
		
		LinkedList<MapNode> queue = new LinkedList<MapNode>();
		HashSet<MapNode> visited = new HashSet<MapNode>();
		HashMap<MapNode, MapNode> parent = new HashMap<MapNode, MapNode>();
		
		MapNode startNode = vertexMap.get(start);
		
		queue.addLast(startNode);
		visited.add(startNode);
		
		while(!queue.isEmpty()){
			MapNode curr = queue.removeFirst();
			nodeSearched.accept(curr.getLocation());
			
			if(curr.getLocation().equals(goal)){
				path.add(curr.getLocation());
				while(!curr.getLocation().equals(start)){
					curr = parent.get(curr);
					path.add(curr.getLocation());
				}
				Collections.reverse(path);
				return path;
			}
			List<MapEdge> curredges = curr.getEdges();
			for(MapEdge mapEdge:curredges){
				MapNode eNode = vertexMap.get(mapEdge.getEnd());
				if(visited.contains(eNode)) continue;
				visited.add(eNode);
				parent.put(eNode, curr);
				queue.addLast(eNode);
			}
		}
		
		return null;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		if(vertexMap.get(start)==null||vertexMap.get(goal)==null) return null;
		int count = 0;
		List<GeographicPoint> path = new ArrayList<GeographicPoint>();
		PriorityQueue<MapNodeDijkstra> queue= new PriorityQueue<MapNodeDijkstra>(); 
		HashSet<MapNodeDijkstra> visited = new HashSet<MapNodeDijkstra>();
		HashMap<MapNodeDijkstra, MapNodeDijkstra> parent = new HashMap<MapNodeDijkstra, MapNodeDijkstra>();
		
		//HashMap<MapNode, MapNode> parent = new HashMap<MapNode, MapNode>();
		
		Set<Map.Entry<GeographicPoint, MapNode>> set = vertexMap.entrySet();
		for(Map.Entry<GeographicPoint, MapNode> entry: set){
			vertexMap.put(entry.getKey(), new MapNodeDijkstra(entry.getValue()));
		}
		
		MapNodeDijkstra startNode = (MapNodeDijkstra)(vertexMap.get(start));
		
		startNode.setDistStart(0.0);
		
		queue.add(startNode);
		while(!queue.isEmpty()){
			MapNodeDijkstra curr = queue.remove();
			count += 1;
			if(!visited.contains(curr)){
				visited.add(curr);
				nodeSearched.accept(curr.getLocation());
				if(curr.getLocation().equals(goal)){
					//return parentMapToPath(parent, start, goal);
					path.add(curr.getLocation());
					while(!curr.getLocation().equals(start)){
						curr = parent.get(curr);
						path.add(curr.getLocation());
					}
					Collections.reverse(path);
					System.out.println(count+"");
					return path;
				}
				List<MapEdge> curredges = curr.getEdges();
				for(MapEdge mapEdge:curredges){
					MapNodeDijkstra eNode = (MapNodeDijkstra)(vertexMap.get(mapEdge.getEnd()));
					if(visited.contains(eNode)) continue;
					double updateDist = curr.getDistStart()+mapEdge.getDistance();
					if(updateDist <= eNode.getDistStart()){
						eNode.setDistStart(updateDist);
						parent.put(eNode, curr);
						queue.add(eNode);
					}
				}
			}
		}
		return null;
	}
	

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		if(vertexMap.get(start)==null||vertexMap.get(goal)==null) return null;
		int count = 0;
		List<GeographicPoint> path = new ArrayList<GeographicPoint>();
		PriorityQueue<MapNodeAStar> queue= new PriorityQueue<MapNodeAStar>(); 
		HashSet<MapNodeAStar> visited = new HashSet<MapNodeAStar>();
		HashMap<MapNodeAStar, MapNodeAStar> parent = new HashMap<MapNodeAStar, MapNodeAStar>();
		
		Set<Map.Entry<GeographicPoint, MapNode>> set = vertexMap.entrySet();
		for(Map.Entry<GeographicPoint, MapNode> entry: set){
			vertexMap.put(entry.getKey(), new MapNodeAStar(entry.getValue()));
		}
		
		MapNodeAStar startNode = (MapNodeAStar)(vertexMap.get(start));
		
		startNode.setDistStart(0.0);
		startNode.setDistGoal(0.0);
		
		queue.add(startNode);
		while(!queue.isEmpty()){
			MapNodeAStar curr = queue.remove();
			count += 1;
			if(!visited.contains(curr)){
				visited.add(curr);
				nodeSearched.accept(curr.getLocation());
				if(curr.getLocation().equals(goal)){
					//return parentMapToPath(parent, start, goal);
					path.add(curr.getLocation());
					while(!curr.getLocation().equals(start)){
						curr = parent.get(curr);
						path.add(curr.getLocation());
					}
					Collections.reverse(path);
					System.out.println(count+"");
					return path;
				}
				List<MapEdge> curredges = curr.getEdges();
				for(MapEdge mapEdge:curredges){
					MapNodeAStar eNode = (MapNodeAStar)(vertexMap.get(mapEdge.getEnd()));
					if(visited.contains(eNode)) continue;
					double updateDist = curr.getDistStart()+mapEdge.getDistance();
					if(updateDist <= eNode.getDistStart()){
						eNode.setDistStart(updateDist);
						eNode.setDistGoal(eNode.getDist(goal));
						parent.put(eNode, curr);
						queue.add(eNode);
					}
				}
			}
		}
		return null;
	}

	
	
	public static void main(String[] args)
	{
		/*
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		System.out.println("DONE.");
		
		GeographicPoint start = new GeographicPoint(1, 1);
		GeographicPoint goal = new GeographicPoint(8, -1);
		List<GeographicPoint> path = theMap.aStarSearch(start, goal);
		if(path == null){
			System.out.println("path is null!");
		}else{
			for(GeographicPoint gp:path){
				System.err.println(gp.getX()+""+", "+gp.getY()+"");
			}
		}
		*/

		
		// You can use this method for testing.  
		
		// Use this code in Week 3 End of Week Quiz
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		
		
	}
	
}
