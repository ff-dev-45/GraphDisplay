package graphdisplay;

/**
 * Represents the graph and fires GraphModelEvents when changes occur to the state
 * of the GraphModel, these events can be listened to by classes that implement the 
 * GraphModelListener interface.
 * 
 * @author Flynn
 */
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class GraphModel {

	private GraphContainer _graph;
	
	@SuppressWarnings("unused")
	private Dimension _bounds;//planned for possible use at a later updated maybe
	
	private List<GraphModelListener> _listeners;
	
	/**
	 * Creates a new Instance for a graph model
	 * @param bounds
	 */
	public GraphModel(Dimension bounds) {
		_graph = new GraphContainer();
		_bounds = bounds;
		_listeners = new ArrayList<GraphModelListener>();
	}
	
	/**
	 * passes the root of the graphModel which will be a GraphContainer
	 * @return
	 */
	public GraphContainer root() {
		return _graph;
	}
	
	/**
	 * can Add a graph to be stored within the root/GraphContainer of this model
	 * fires GraphAddedEvent
	 * @param graph the graph to be added
	 * @param container the root/graphContainer to add the graph to
	 * @return status of adding the graph
	 */
	public boolean addGraph(Graph graph, GraphContainer container) {
		boolean success = true;
		
		try {
			container.addGraph(graph);
			fire(GraphModelEvent.makeGraphAddedEvent(graph, this));
		} catch (IllegalArgumentException e) {
			success = false;
		}
		return success;
	}
	
	/**
	 * able to remove a graph from the graphContainer stored within this model if the graph is present
	 * fires GraphRemovedEvent
	 * @param graph the graph to be removed
	 */
	public void removeGraph(Graph graph) {
		int index = _graph.indexOf(graph);
		_graph.removeGraph(graph);
		
		fire(GraphModelEvent.makeGraphRemovedEvent(graph, _graph, index, this));
	}
	
	/**
	 * can add Vertices to a graph contained within this model
	 * fires VertexAddedEvent
	 * @param graph the graph the vertex is being added to
	 * @param vertex the vertex being added to the graph
	 * @return status of adding the vertex
	 */
	public boolean addVertex(Graph graph, Vertex vertex) {
		boolean success = true;
		
		try {
			graph.addVertex(vertex);
			fire(GraphModelEvent.makeVertexAddedEvent(graph, this, vertex));
		} catch(IllegalArgumentException e) {
			success = false;
		}
		return success;
	}
	
	/**
	 * can add edges to a graph between two vertices,
	 * fires EdgeAddedEvent
	 * @param graph the graph this addition is taking place in
	 * @param vertex1 the first vertex the edge connects
	 * @param vertex2 the second vertex the edge connects
	 * @return status of adding the edge to the graph
	 */
	public boolean addEdge(Graph graph, Vertex vertex1, Vertex vertex2) {
	boolean success = true;
		
		try {
			graph.addEdge(vertex1,vertex2);
			Edge edge = vertex1.edgeAt(vertex1.countEdge()-1);
			fire(GraphModelEvent.makeEdgeAddedEvent(graph, this, vertex1, edge));
			fire(GraphModelEvent.makeEdgeAddedEvent(graph, this, vertex2, edge));
		} catch(IllegalArgumentException e) {
			success = false;
		}
		return success;
	}
	
	/**
	 * remove a specified edge from the graph
	 * fires EdgeRemovedEvent
	 * @param graph the graph this edge is being removed from
	 * @param edge the edge being removed
	 */
	public void removeEdge(Graph graph, Edge edge) {
		Vertex vertex1 = edge.from();
		Vertex vertex2 = edge.to();
		int index1 = vertex1.indexOf(edge);
		int index2 = vertex2.indexOf(edge);
		graph.removeEdge(vertex1, vertex2, edge);
		fire(GraphModelEvent.makeEdgeRemovedEvent(graph, _graph, index1, this, vertex1, edge));
		edge.removeVertex(vertex1);
		fire(GraphModelEvent.makeEdgeRemovedEvent(graph, _graph, index2, this, vertex2, edge));
		edge.removeVertex(vertex2);
		
	}
	
	/**
	 * remove a specified vertex from a graph
	 * fires VertexRemovedEvent & EdgeRemovedEvent for each edge from this vertex
	 * @param graph the graph the vertex is being removed from
	 * @param vertex the vertex being removed
	 */
	public void removeVertex(Graph graph, Vertex vertex) {
		List<Edge> toRemove = vertex.edgeList();
		int size = toRemove.size();
		for(int i=0;i<size;i++) {
			removeEdge(graph,toRemove.get(0));
		}
		
		int index = graph.indexOf(vertex);
		graph.removeVertex(vertex);
		
		fire(GraphModelEvent.makeVertexRemovedEvent(graph, _graph, index, this, vertex));
	}

	public void draw(){
		fire(GraphModelEvent.makeGraphDrawEvent(_graph, this));
	}
	
	/**
	 * Adds a GraphModelListener to be registered with this GraphModel to be notified of updates
	 * @param listener
	 */
	public void addGraphModelListener(GraphModelListener listener) {
		_listeners.add(listener);
	}
	
	/**
	 * Removes a registered GraphModelListener from graphModel updates/events
	 * @param listener
	 */
	public void removeGraphModelListener(GraphModelListener listener) {
		_listeners.remove(listener);
	}
	
	/**
	 * Fire a given event to all the GraphModelListeners that are registered with this model
	 * @param event
	 */
	public void fire(GraphModelEvent event) {
		for(GraphModelListener listener: _listeners) {
			listener.update(event);
		}
	}
}
