package graphdisplay;

/**
 * Describes the different events of change for a GraphModel, these events are sent
 * to GraphModelListeners through the update() calls
 * 
 * @author Flynn
 *
 */

public class GraphModelEvent {
	//type of events
	public enum EventType {GraphAdded, GraphRemoved, VertexAdded, VertexRemoved, EdgeAdded, EdgeRemoved};
	
	private EventType _type;	//Event type
	private Graph _operand;		//Graph this event is from
	private GraphModel _source;	//The model that fires the event
	private Vertex _vertex;		//The vertex this event relates to if it involves a vertex
	private Edge _edge;			//The edge this event relates to if it involves a edge
	private int _index;			//The index of which the event object relates to
	private GraphContainer _root;//The root of the hierarchy 
	
	/**
	 * creates a GraphAddedEvent
	 * @param graphAdded the graph added to the GraphModel
	 * @param source the graphModel the graph is being adde to
	 */
	public static GraphModelEvent makeGraphAddedEvent(Graph graphAdded, GraphModel source) {
		GraphContainer root = source.root();
		int index = root.indexOf(graphAdded);
		
		return new GraphModelEvent(EventType.GraphAdded, graphAdded, root, index, source);
	}
	
	
	/**
	 * creates a GraphRemovedEvent
	 * @param graphRemoved the graph removed from the model
	 * @param root the source/root of the hierarchy tree
	 * @param index the index the graph is in under the root
	 * @param source the graphmodel this event is from

	 */
	public static GraphModelEvent makeGraphRemovedEvent(Graph graphRemoved, GraphContainer root, int index, GraphModel source) {
		return new GraphModelEvent(EventType.GraphRemoved, graphRemoved, root, index, source);
	}
	
	
	/**
	 * creates a VertexAddedEvent
	 * @param graph the graph the vertex is added to
	 * @param source the GraphModel this is from
	 * @param vertex the vertex being added
	 */
	public static GraphModelEvent makeVertexAddedEvent (Graph graph, GraphModel source, Vertex vertex) {
		GraphContainer root = source.root();
		int index = graph.indexOf(vertex);
		
		return new GraphModelEvent(EventType.VertexAdded, graph, root, index, source, vertex);
	}
	
	
	/**
	 * makes a VertexRemovedEvent
	 * @param graph the graph the vertex is remove from
	 * @param root the root of the hierarchy
	 * @param index the index the vertex was in from the graph before being removed
	 * @param source the GraphModel this event is from
	 * @param vertex the vertex object that was removed
	 */
	public static GraphModelEvent makeVertexRemovedEvent (Graph graph, GraphContainer root, int index, GraphModel source, Vertex vertex) {
		return new GraphModelEvent(EventType.VertexRemoved, graph, root, index, source, vertex);
	}
	
	
	/**
	 * make a EdgeAddedEvent
	 * @param graph the graph the edge is added to
	 * @param source the graphModel this event is from
	 * @param vertex the vertex the edge is added to
	 * @param edge the edge being added to the vertex
	 */
	public static GraphModelEvent makeEdgeAddedEvent (Graph graph, GraphModel source, Vertex vertex, Edge edge) {
		GraphContainer root = source.root();
		int index = vertex.indexOf(edge);
		
		return new GraphModelEvent(EventType.EdgeAdded, graph, root, index, source, vertex, edge);
	}
	
	
	/**
	 * make a EdgeRemovedEvent
	 * @param graph the graph the edge is removed from
	 * @param root the root of the hierarchy
	 * @param index the index the edge was in from the vertex prior to removal
	 * @param source the GraphModel this event is from
	 * @param vertex the vertex the edge is removed from
	 * @param edge the edge that is removed

	 */
	public static GraphModelEvent makeEdgeRemovedEvent (Graph graph, GraphContainer root, int index, GraphModel source, Vertex vertex, Edge edge) {
		return new GraphModelEvent(EventType.EdgeRemoved, graph, root, index, source, vertex, edge);
	}
	
	//Private constructor for creating the GraphModelEvents with the required data
	private GraphModelEvent(EventType type, Graph graph,GraphContainer root, int index, GraphModel source) {
		_type = type;
		_operand = graph;
		_source = source;
		_index = index;
		_root = root;
	}
	////Private constructor for creating the GraphModelEvents with the required data
	private GraphModelEvent(EventType type, Graph graph, GraphContainer root, int index, GraphModel source, Vertex vertex) {
		_type = type;
		_operand = graph;
		_source = source;
		_index = index;
		_root = root;
		_vertex = vertex;
	}
	//Private constructor for creating the GraphModelEvents with the required data
	private GraphModelEvent(EventType type, Graph graph, GraphContainer root, int index, GraphModel source, Vertex vertex, Edge edge) {
		_type = type;
		_operand = graph;
		_source = source;
		_index = index;
		_root = root;
		_vertex = vertex;
		_edge = edge;
	}
	/**
	 * returns the event type
	 */
	public EventType eventType() {
		return _type;
	}
	
	/**
	 * returns the graph this event takes place on
	 * @return
	 */
	public Graph operand() {
		return _operand;
	}
	
	/**
	 * returns the root of the hierarchy
	 */
	public GraphContainer root() {
		return _root;
	}
	
	/**
	 * returns the source/GraphModel the event is from
	 */
	public GraphModel source() {
		return _source;
	}
	
	/**
	 * returns the index of the object the event is about
	 */
	public int index() {
		return _index;
	}
	
	/**
	 * returns the vertex involved in the event if a vertex is involved
	 */
	public Vertex vertex() {
		return _vertex;
	}
	
	/**
	 * returns the edge invovled in the event if a edge is involved
	 * @return
	 */
	public Edge edge() {
		return _edge;
	}
}
