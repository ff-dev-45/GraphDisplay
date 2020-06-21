package graphdisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * GraphContainer is a class that implements the graph interface for some accessibility and easy for setting up the tree adapter,
 * this class was also created so that if in need in the future the functionality to add/remove entire graphs would be possible
 * with minimal adjustment required to the code
 * @author Flynn
 *
 */
public class GraphContainer implements Graph {
	
	private List<Graph> _graphs;
	
	/**
	 * Create a new GraphContainer instance
	 */
	public GraphContainer() {
		_graphs = new ArrayList<Graph>();
	}
	
	/**
	 * add a new graph to this container and set this instance as the parent
	 * @param graph
	 */
	public void addGraph (Graph graph) {
		_graphs.add(graph);
		graph.setParent(this);
	}
	
	/**
	 * remove the specified graph from this container if it contains it
	 * @param graph
	 */
	public void removeGraph(Graph graph) {
		_graphs.remove(graph);
	}
	/**
	 * check to see if a graph is at that index location and return if so
	 * @param index the index of graph we are looking for
	 * @return return the graph if a graph is at that index
	 * @throws IndexOutOfBoundsException throw this exception if there is not graph and the index is out of bounds
	 */
	public Graph graphAt(int index) throws IndexOutOfBoundsException {
		return _graphs.get(index);
	}
	
	/**
	 * Count the number of graphs currently stored within this instance
	 * @return
	 */
	public int graphCount() {
		return _graphs.size();
	}
	/**
	 * find the index of the specified graph object if it is present
	 */
	@Override
	public int indexOf(Object graph) {
		if(graph instanceof Graph) {
			return _graphs.indexOf(((Graph)graph));
		}
		else {
			return -1;
		}
	}
	/**
	 * empty implementation as not required by GraphContainer
	 */
	@Override
	public void addVertex(Vertex u) {
	}

	/**
	 * empty implementation as not required by GraphContainer
	 */
	@Override
	public boolean removeVertex(Vertex v) {
		return false;
	}

	/**
	 * empty implementation as not required by GraphContainer
	 */
	@Override
	public void addEdge(Vertex u, Vertex v) {

	}

	/**
	 * empty implementation as not required by GraphContainer
	 */
	@Override
	public void removeEdge(Vertex u, Vertex v, Edge edge) {
	}

	/**
	 * recursively calls paint on all the graphs contained within this instance
	 */
	@Override
	public void paint(Painter painter) {
		for(Graph graph: _graphs) {
			graph.paint(painter);
		}
	}

	/**
	 * empty implementation as not required by GraphContainer
	 */
	@Override
	public Vertex vertexAt(int index) {
		return null;
	}

	/**
	 * empty implementation as not required by GraphContainer
	 */
	@Override
	public int countVertices() {
		return -1;
	}
	/**
	 * returns the class name for the string
	 */
	public String toString() {
		return getClass().getSimpleName();
	}
	
	/**
	 * No path from a graphContainer as this is the root of the tree
	 */
	@Override
	public List<Object> path() {
		return null;
	}
	/**
	 * no parent as this is the root parent object
	 */
	@Override
	public void setParent(Object parent) {
	}


}
