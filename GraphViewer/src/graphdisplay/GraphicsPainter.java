package graphdisplay;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * implementation of the painter interface to delegate drawing for objects
 * that require drawing on the display
 * @author Flynn
 *
 */
public class GraphicsPainter implements Painter {
	private Graphics _g;
	
	/**
	 * New GraphicsPainter instance with delegated Graphics
	 * @param g
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(Color.white);//default drawing colour
	}
	
	/**
	 * @see graphdisplay.Painter.drawOval
	 */
	@Override
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x,y,width,height);

	}
	/**
	 * @see graphdisplay.Painter.drawLine
	 */
	@Override
	public void drawLine(int x, int y, int x2, int y2) {
		_g.drawLine(x,y,x2,y2);
	}

	/**
	 * @see graphdisplay.Painter.drawText
	 */
	@Override
	public void drawText(String str, int x, int y, int width, int height) {
		//draws a centered text on the supplied coordiantes
		FontMetrics metrics = _g.getFontMetrics();
		int ascent = metrics.getAscent();
		
		int centredX = x + (width-metrics.stringWidth(str)) /2;
		int centredY = y + ((height - metrics.getHeight())/2)+ascent;

		_g.drawString(str, centredX, centredY);

	}
	/**
	 * @see graphdisplay.Painter.fillOval
	 */
	@Override
	public void fillOval(int x, int y, int width, int height) {
		_g.fillOval(x,y,width,height);
	}
	
	/**
	 * @see graphdisplay.Painter.getColor
	 */
	public Color getColor() {
		return 	_g.getColor();
	}
	/**
	 * @see graphdisplay.Painter.setColor
	 */
	public void setColor(Color c) {
		_g.setColor(c);
	}


}
