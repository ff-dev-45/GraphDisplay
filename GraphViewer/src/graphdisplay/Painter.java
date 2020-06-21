package graphdisplay;

import java.awt.Color;

/**
 * Interface to have the responsibilities required for painting graph components
 * @author Flynn
 *
 */
public interface Painter {
	/**
	 * Draws an oval
	 * @param x the x coord (top left corner)
	 * @param y the y coord (top left corner)
	 * @param width width of oval
	 * @param height height of oval
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line
	 * @param x the x coord of the first point
	 * @param y the y coord of the first point
	 * @param x2 the x coord of the second point
	 * @param y2 the y coord of the second point
	 */
	public void drawLine(int x, int y, int x2, int y2);
	
	/**
	 * draw text
	 * @param str the string to be drawn
	 * @param x the x coord of where to draw text
	 * @param y the y coord of where to draw text
	 * @param width the width of text box
	 * @param height the height of text box
	 */
	public void drawText(String str, int x, int y, int width, int height);
	
	/**
	 * Fills the specified oval with the colour specified
	 * @param x the x coord of specified oval
	 * @param y the y coord of specified oval
	 * @param width of the specified oval
	 * @param height of the speciied oval
	 */
	public void fillOval(int x,int y, int width, int height);
	
	/**
	 * Gets the graphics current colour
	 * @return the colour of the current graphics
	 */
	public Color getColor();
	
	/**
	 * Sets the graphics current colour, all operations follow will be in that colour
	 * @param c the colour to set the graphics too
	 */
	public void setColor(Color c);
}
