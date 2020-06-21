package graphdisplay;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A NormalGraph is a graph that has no directed/weighted edges,
 * and all edges connect to both vertices and are stored in the same list,
 * The graph has a list of the vertices stored within and a reference to the parent container
 * @author Flynn
 *
 */
public class NormalGraph implements Graph {
	private List<Vertex> _vertices;
	private GraphContainer _container;
	
	/**
	 * creates a new NormalGraph instance
	 */
	public NormalGraph() {
		_vertices = new LinkedList<Vertex>();
	}
	
	/**
	 * set the parent (GraphContainer) of this graph instance
	 */
	@Override
	public void setParent(Object parent) {
		if(parent instanceof GraphContainer) {
			_container = ((GraphContainer)parent);
		}
	
	}
	/**
	 * add a vertex into the list of vertices, and set the graph as the 'parent' of the vertex
	 */
	public void addVertex(Vertex u) {
		_vertices.add(u);
		u.setGraph(this);
	}
	
	/**
	 * remove a specifc vertex from the graph if it is present
	 */
	public boolean removeVertex(Vertex u) {
		return _vertices.remove(u);
	}
	
	/**
	 * add a new edge between two vertices of this graph
	 */
	public void addEdge(Vertex u, Vertex v) {
		Edge newEdge = new Edge(u,v);
		u.addEdge(newEdge);
		v.addEdge(newEdge);
	}
	
	/**
	 * remove an edge from between two vertices of this graph
	 */
	public void removeEdge(Vertex u, Vertex v, Edge edge) {
		u.removeEdge(edge);
		v.removeEdge(edge);
	}
	
	/**
	 * paint the components of this graph in a way so edges  are printed first before vertices
	 * this causes vertices to be drawn last so they paint over and fill edge lines 
	 */
	public final void paint(Painter painter) {
		for(Vertex vertex: _vertices) {
			vertex.paintEdge(painter);
		}
		for(Vertex vertex: _vertices) {
			vertex.paintVertex(painter);
		}
	}
	/**
	 * return the vertex at a given index if it is present
	 */
	@Override
	public Vertex vertexAt(int index) {
		return _vertices.get(index);
	}
	
	/**
	 * count the total number of vertices present within this graph
	 */
	@Override
	public int countVertices() {
		return _vertices.size();
	}
	/**
	 * return the index of a given child object "vertex" return -1 if it is not present within this graph
	 */
	@Override
	public int indexOf(Object child) {
		if(child instanceof Vertex) {
			return _vertices.indexOf(((Vertex)child));
		}
		else {
			return -1;
		}
	}
	/**
	 * return the class name of the vertex 
	 */
	public String toString() {
		return getClass().getSimpleName();
	}
	
	/**
	 * produce a list of objects representing the heirachy to this object from the root
	 */
	@Override
	public List<Object> path() {
		List<Object> path = new ArrayList<Object>();
		path.add(_container);
		return path;
	}
}
