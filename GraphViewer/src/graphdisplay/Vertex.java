package graphdisplay;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class with the instances representing the vertex components that make up a graph
 * stores a list of edges that are attached to this vertex
 * @author Flynn
 *
 */
public class Vertex {
	private List<Edge> _edges= new ArrayList<Edge>();
	private int _x;
	private int _y;
	private int _width = 40;
	private int _height = 30;
	private String _name ="vertex";
	private Graph _graph;
	
	/**
	 * Create an instance of a new vertex
	 * @param x the x coordinate of the new vertex's position on the display
	 * @param y the y coordinate of the new vertex's position on the display
	 * @param width the width of the vertex
	 * @param height the height of the vertex
	 * @param name the name that will be displayed in the centre of the vertex on the display
	 */
	
	public Vertex(int x, int y, int width, int height, String name) {
		_x = x;
		_y = y;
		_width = width;
		_height = height;
		_name = name;   
	}
	/**
	 * set the reference to the graph this vertex is stored in
	 * @param graph
	 */
	public void setGraph(Graph graph) {
		if(_graph==null) {
			_graph = graph;
		}
	}
	
	/**
	 * add a new edge to this vertex
	 * @param e
	 */
	public void addEdge(Edge e) {
		_edges.add(e);
	}
	
	/**
	 * remove a specified edge from this vertex
	 * @param e the edge to be removed
	 * @return the status of if the edge was removed successfully
	 */
	public boolean  removeEdge(Edge e) {
		return _edges.remove(e);
	}
	/**
	 * return the edge at a given index //
	 * @param index
	 * @return
	 */
	public Edge edgeAt(int index) {
		return _edges.get(index);
	}
	/**
	 * return the index of a given edge passed in if it is present //
	 * @param edge the edge to find the index of
	 * @return the index of the given edge
	 */
	public int indexOf(Edge edge) {
		return _edges.indexOf(edge);
	}
	
	/**
	 * return the total number of edges connected to this vertex
	 * @return the number of edges
	 */
	public int countEdge() {
		return _edges.size();
	}
	
	/**
	 * return a list of the edges connected to this vertex //
	 * @return
	 */
	public List<Edge> edgeList() {
		return _edges;
	}
	
	/**
	 * produce a path of the hierarchy up to the parent of this object (graph)
	 * @return
	 */
	public List<Object> path(){
		List<Object> path = new ArrayList<Object>();
		path = _graph.path();
		path.add(_graph);
		
		return path;
	}
	
	/**
	 * paint all the edge components associated with this vertex
	 * @param painter the graphics instance that will draw the edges
	 */
	public void paintEdge(Painter painter) {
		for(Edge edge:_edges) {
			edge.paint(painter);
		}
	}
	/**
	 * paint this vertex
	 * @param painter the graphics instance that will draw the vertex
	 */
	public void paintVertex(Painter painter) {
		painter.setColor(Color.BLACK);
		painter.fillOval(_x, _y, _width, _height);
		painter.setColor(Color.WHITE);
		painter.drawOval(_x, _y, _width, _height);
		painter.drawText(_name, _x, _y, _width, _height);
	}
	
	/**
	 * return the x-coord of this vertex
	 */
	public int getX() {
		return _x;
	}
	/**
	 * return the y-coord of this vertex
	 */
	public int getY() {
		return _y;
	}
	/**
	 * return the width of the vertex
	 */
	public int getWidth() {
		return _width;
	}
	/**
	 * return the height of the vertex
	 */
	public int getHeight() {
		return _height;
	}
	/**
	 * return the graph this vertex is a part of
	 * @return
	 */
	public Graph getGraph() {
		return _graph;
	}
	/**
	 * produce the string of the class name "Vertex " with the given string 'name'
	 */
	public String toString() {
		return getClass().getSimpleName() + " " + _name;
	}
}
