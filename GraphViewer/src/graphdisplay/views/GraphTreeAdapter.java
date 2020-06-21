package graphdisplay.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import graphdisplay.Edge;
import graphdisplay.Graph;
import graphdisplay.GraphContainer;
import graphdisplay.GraphModel;
import graphdisplay.GraphModelEvent;
import graphdisplay.GraphModelListener;
import graphdisplay.Vertex;

/**
 * Acts as an adapter between the incompatible graphModel and TreeModel, so that graph
 * components can be used in JTree and be rendered as objects, this class can also listen
 * to graphModelEvents and react and send out the corresponding events for the treeModel Listeners
 * @author Flynn
 *
 */
public class GraphTreeAdapter implements TreeModel, GraphModelListener {
	private GraphModel _graphModel;
	private List<TreeModelListener> _treeListeners = new ArrayList<TreeModelListener>();
	
	/**
	 * Creates an adapter for the specific graphModel
	 * @param graphModel the graphModel this adapter is for
	 */
	public GraphTreeAdapter(GraphModel graphModel) {
		_graphModel = graphModel;
	}
	

	/**
	 * returns the root of the graphModel for the tree
	 */
	@Override
	public Object getRoot() {
		return _graphModel.root();
	}
	
	/**
	 * returns the graph component at the given index, a graph if the object is GraphContainer,
	 * a vertex is the object given is a graph, and an edge if the given object is a vertex.
	 * If the index is out of bounds null is returned 
	 */
	@Override
	public Object getChild(Object parent, int index) {
		
		if(parent instanceof GraphContainer) {
			try {
				return ((GraphContainer) parent).graphAt(index);
			} catch(IndexOutOfBoundsException e) {
				return null;
			}
		}
		if(parent instanceof Graph) {
			try {
				return ((Graph) parent).vertexAt(index);
			} catch(IndexOutOfBoundsException e) {
				return null;
			}
		}
		if(parent instanceof Vertex) {
			try {
				return ((Vertex) parent).edgeAt(index);
			} catch(IndexOutOfBoundsException e) {
				return null;
			}
		}
		else {
		return null;
	}
	}
	/**
	 * returns the number of child objects of the given parent instance, if it is a leaf/edge 0 is returned
	 */
	@Override
	public int getChildCount(Object parent) {
		if(parent instanceof GraphContainer) {
			//number of graphs in a graph container
			return ((GraphContainer) parent).graphCount();
		}
		if(parent instanceof Graph) {
			//number of vertices within a graph
			return ((Graph) parent).countVertices();
		}
		if(parent instanceof Vertex) {
			//number of edges associated with a vertex
			return ((Vertex) parent).countEdge();
		}
		else {
		return 0;
	}
	}
	
	/** 
	 * returns true if the node object is a leaf, for this tree only edges are leaf nodes, all other object types
	 * are containers so will return false
	 */
	@Override
	public boolean isLeaf(Object node) {
		if(node instanceof GraphContainer) {
			return false;
		}
		if(node instanceof Graph) {
			return false;
		}
		if(node instanceof Vertex) {
			return false;
		}
		if(node instanceof Edge) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * empty declaration for valueforPathChanged, as currently paths do not change in this implementation
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}
	
	/**
	 * Returns the index of the child object in a parent, if either parent/child is null return -1, or if either does not belong in tree
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if(parent instanceof GraphContainer && child instanceof Graph) {
			return ((GraphContainer) parent).indexOf(child);
		}
		if(parent instanceof Graph && child instanceof Vertex) {
			return((Graph) parent).indexOf(child);
		}
		if(parent instanceof Vertex && child instanceof Edge) {
			return ((Vertex) parent).indexOf(((Edge)child));
		}
		else {
		return -1;
		}
	}
	
	/**
	 *Adds a TreeModelListener to the list of observers registered for this adapter 
	 */
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		_treeListeners.add(l);
	}
	
	/**
	 * removes a registered TreeModelListener from the list of oberservers for this adapter
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		_treeListeners.remove(l);
	}


	/**
	 * Upon receiving a GraphModelEvent this will respond by creating the corresponding TreeModelEvent
	 * required to be sent to TreeModelListeners to update TreeModel
	 */
	@Override
	public void update(GraphModelEvent event) {
		//Unpack event
		GraphModelEvent.EventType type = event.eventType();
		Graph graph = event.operand();
		
		TreeModelEvent treeEvent = null;
		List<Object> path = new ArrayList<Object>();
		int index = event.index();//index from where the object the event is about is from in the tree
		
		if(type == GraphModelEvent.EventType.GraphAdded) {
			//builds a path to the parent of the graph added
			path.add(event.source());
			//New tree event for a graph being added to the tree
			treeEvent = new TreeModelEvent(event.source(),path.toArray(), new int[] {index}, new Graph[] {graph});
			
			//fire treeNodesInserted event to all registered treeModelListeners
			for(TreeModelListener listener: _treeListeners) {
				listener.treeNodesInserted(treeEvent);
			}
		} else if (type == GraphModelEvent.EventType.GraphRemoved) {
			//build a path to the parent of the graph removed
			path.add(event.source());
			//New tree event for a graph being removed from the tree
			treeEvent = new TreeModelEvent(event.source(), path.toArray(),new int[] {index}, new Graph[] {graph});
			
			//fire treeNodesRemoved event to all registered TreeModelListeners
			for(TreeModelListener listener: _treeListeners) {
				listener.treeNodesRemoved(treeEvent);
			}
			
			
		}else if (type == GraphModelEvent.EventType.VertexAdded) {
			//build a path to the parent of the vertex added
			path.add(event.vertex().path());
			//vertex object added to the tree
			Vertex vertex = event.vertex();
			//New tree event for a node/vertex being added to the tree
			treeEvent = new TreeModelEvent(event.source(),path.toArray(),new int[] {index}, new Vertex[] {vertex});
			
			//fire treeNodesInserted to all registered TreeModelListeners
			for(TreeModelListener listener: _treeListeners) {
				listener.treeNodesInserted(treeEvent);
			}
		}else if (type == GraphModelEvent.EventType.VertexRemoved) {
			//Create path to parent of vertex removed
			path.add(event.vertex().path());
			//Vertex that was removed
			Vertex vertex = event.vertex();
			//New tree event for a node/vertex being removed from the tree
			treeEvent = new TreeModelEvent(event.source(),path.toArray(),new int[] {index}, new Vertex[] {vertex});
			
			//fire treeNodesRemoved to all registered TreeModelListeners
			for(TreeModelListener listener: _treeListeners) {
				listener.treeNodesRemoved(treeEvent);
			}
			
		}else if (type == GraphModelEvent.EventType.EdgeAdded) {
			//Create a path to parent of the edge added
			path.add(event.edge().path());
			//New tree event for a node/edge being added to the tree
			treeEvent = new TreeModelEvent(event.source(),path.toArray(), new int[] {index}, new Edge[] {event.edge()});
			
			//fire TreeNodesInserted to all registered TreeModelListeners
			for(TreeModelListener listener: _treeListeners) {
				listener.treeNodesInserted(treeEvent);
			}
			
		}else if (type == GraphModelEvent.EventType.EdgeRemoved) {
			//Create a path to parent of edge removed
			path.add(event.edge().path());
			//New tree event for a node/edge being removed from a tree
			treeEvent = new TreeModelEvent(event.source(),path.toArray(), new int[] {index}, new Edge[] {event.edge()});
			
			//fire treeNodesRemoved to all registered TreeModelListeners
			for(TreeModelListener listener: _treeListeners) {
				listener.treeNodesRemoved(treeEvent);
			}
		}
		
	}
	
	

}
