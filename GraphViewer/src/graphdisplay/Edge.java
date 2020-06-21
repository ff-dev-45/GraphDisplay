package graphdisplay;

import java.util.List;

/**
 * Class representing what is required by edge instances in the GraphDisplay program,
 * many of the methods are required for edge to be displayed in the JTree and be interacted with
 * 
 * @author Flynn
 *
 */
public class Edge {
	private Vertex _parent1;
	private Vertex _parent2;
	
	/**
	 * Create an edge instance with reference to the vertices it is between
	 * @param u the first vertex it connects to
	 * @param v the second vertex it connects to
	 */
	public Edge(Vertex u, Vertex v) {
		_parent1 = u;
		_parent2 = v;
	}
	
	/**
	 * returns the first vertex it connects to
	 * @return the first vertex 'parent'
	 */
	public Vertex from() {
		return _parent1;
	}
	/**
	 * returns the second vertex it connects to
	 * @return the second vertex 'parent'
	 */
	public Vertex to() {
		return _parent2;
	}
	
	/**
	 * creates a path to this object from the parent objects,
	 * GraphContainer->Graph->Vertex,
	 * If a vertex parent1 has already been removed, return path to parent2
	 * @return the path of objects that make up the heirachy to the parent of tihs edge
	 */
	public List<Object> path(){
		if(_parent1!=null) {
			return _parent1.path();
		}
		else {
			return _parent2.path();
		}
	}
	
	/**
	 * Used to paint the edge from one vertex to other
	 * @param painter the graphics object used to draw the lines
	 */
	public void paint(Painter painter) {
		int x = (2*_parent1.getX()+_parent1.getWidth())/2;
		int y = (2*_parent1.getY()+_parent1.getHeight())/2;
		int x2 = (2*_parent2.getX()+_parent2.getWidth())/2;
		int y2 = (2*_parent2.getY()+_parent2.getHeight())/2;
		painter.drawLine(x, y,x2,y2);
	}
	
	/**
	 * Used when removing an edge/vertex, it must remove the reference to the vertex being removed,
	 * this allows for a path to be generated to the other vertex to allow the edge to be removed
	 * from that vertex
	 * @param parent pass in the reference to the vertex to remove
	 */
	public void removeVertex(Vertex parent) {
			if(parent.equals(_parent1)) {
				_parent1 = null;
			}
			if(parent.equals(_parent2)) {
				_parent2 = null;
			}
	}
	
	/**
	 * returns the name of the edge as the clas name with the two vertices it is attached to
	 */
	public String toString() {
		return getClass().getSimpleName() +" "+_parent1+" and "+_parent2;
	}
}
