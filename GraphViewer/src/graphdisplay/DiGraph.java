package graphdisplay;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class DiGraph implements Graph {
	private List<Vertex>_vertices;
	private GraphContainer _container;
	private String _name = "";
	
	/**
	 * NOT CURRENTLY IMPLEMENTED IN WORKING PROGRAM,
	 * planned to be added in soon
	 * 
	 * @author Flynn
	 */
	
	/**
	 * Creates a new Directed Graph
	 */
	public DiGraph() {
		_vertices = new LinkedList<Vertex>();
	}
	/**
	 * Creates a new named directed Graph
	 * @param name
	 */
	public DiGraph(String name) {
		_vertices = new LinkedList<Vertex>();
		_name = name;
	}
	
	/**
	 * Sets the parent/container of this graph
	 */
	@Override
	public void setParent(Object parent) {
		if(parent instanceof GraphContainer) {
			_container = ((GraphContainer)parent);
		}
	
	}
	
	/**
	 * Adds a vertex to this digraph
	 */
	@Override
	public void addVertex(Vertex u) {
		_vertices.add(u);
		u.setGraph(this);
	}
	
	/**
	 * remove a given vertex from this digraph
	 */
	@Override
	public boolean removeVertex(Vertex v) {
		return _vertices.remove(v);
	}
	
	/**
	 * add a new directional edge from one vertex to another in this graph 
	 */
	@Override
	public void addEdge(Vertex u, Vertex v) {
		Edge newEdge = new DirectedEdge(u,v);
		u.addEdge(newEdge);
		v.addEdge(newEdge);
		
		//will need to change nature of how edges are added for directions if
		//both outgoing/ingoing lists of edges will be tracked
	}
	
	/**
	 * remove a given edge from the vertices specified
	 */
	@Override
	public void removeEdge(Vertex u, Vertex v, Edge edge) {
		u.removeEdge(edge);
		v.removeEdge(edge);
	}
	/**
	 * paints the vertices/edges in a way so that edges do not show up under/overlapping the vertex 
	 */
	@Override
	public void paint(Painter painter) {
		for(Vertex vertex: _vertices) {
			vertex.paintEdge(painter);
		}
		for(Vertex vertex: _vertices) {
			vertex.paintVertex(painter);
		}

	}
	/**
	 * return the vertex at a given index
	 */
	@Override
	public Vertex vertexAt(int index) {
		return _vertices.get(index);
	}
	/**
	 * count the number of vertices within this digraph
	 */
	@Override
	public int countVertices() {
		return _vertices.size();
	}
	/**
	 * find the index of a given object if it is present, return -1 if not in this digraph
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
	 * create a path to the parent object
	 */
	@Override
	public List<Object> path() {
		List<Object> path = new ArrayList<Object>();
		path.add(_container);
		return path;
	}
	/**
	 * provide a string showing the class name of any instance
	 */
	public String toString() {
		return getClass().getSimpleName()+": "+_name;
	}
}
