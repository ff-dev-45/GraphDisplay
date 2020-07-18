package graphdisplay;

/**
 * NOT CURRENTLY IMPLEMENTED IN WORKING PROGRAM
 * planned to be added in soon
 * @author Flynn
 *
 */
public class DirectedEdge extends Edge {
	
	/**
	 * creates a DirectedEdge
	 * @param u vertex the edge is from
	 * @param v vertex the edge is to
	 */
	public DirectedEdge(Vertex u, Vertex v) {
		super(u, v);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint(Painter painter) {
		int x = (2*from().getX()+from().getWidth())/2;
		int y = (2*from().getY()+from().getHeight())/2;
		int x2 = (2*to().getX()+to().getWidth())/2;
		int y2 = (2*to().getY()+to().getHeight())/2;
		painter.drawLine(x, y,x2,y2);
		int xMid = (x+x2)/2;
		int yMid = (y+y2)/2;
		painter.drawLine(xMid, yMid, xMid-5, yMid-5);
		painter.drawLine(xMid, yMid, xMid-5, yMid+5);
	}
	

}
