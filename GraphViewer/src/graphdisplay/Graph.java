package graphdisplay;

/**
 * interface to represent the responsibilities for a graph
 */
import java.util.List;

public interface Graph {
	
	/**
	 * Add a created vertex to be registered for a graph - include setGraph for vertex
	 * @param u the vertex to be added
	 */
	public void addVertex(Vertex u);
	
	/**
	 * Remove a vertex that has already been added to a graph
	 * @param v the vertex to be removed
	 * @return true or false if the vertex is removed successfully
	 */
	public boolean removeVertex(Vertex v);
	
	/**
	 * Add an edge to the two vertices it is between
	 * @param u one of the parent vertices of the edge
	 * @param v the other parent vertex of the edge
	 */
	public void addEdge(Vertex u, Vertex v) ;
	
	/**
	 * Remove an edge between two vertices
	 * @param u one of the parent vertices of the edge
	 * @param v the other parent vertex of the edge
	 * @param edge the edge to be removed
	 */
	public void removeEdge(Vertex u, Vertex v, Edge edge);
	
	/**
	 * Return the vertex at an index of a graph
	 * @param index index of the vertex we want to grab
	 * @return the vertex at that index
	 */
	public Vertex vertexAt(int index);
	
	/**
	 * Return the number of vertices of the graph
	 * @return the vertex count
	 */
	public int countVertices();
	
	/**
	 * Return the index of the child object
	 * @param child the object we are checking
	 * @return the index of the object
	 */
	public int indexOf(Object child);
	
	/**
	 * set the object parent as the object passed in, used for storing graphs in containers
	 * @param parent the parent object we are storing this object in
	 */
	public void setParent(Object parent);
	
	/**
	 * Return the path leading to the root for heirachy tree
	 * @return the list of objects leading to the root
	 */
	public List<Object> path();
	
	/**
	 * paint the components that make up the graph structure recursively 
	 * @param painter the object following the painter interface
	 */
	public void paint(Painter painter);
}
