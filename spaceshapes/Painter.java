package spaceshapes;

import java.awt.Color;
import java.awt.Image;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * rectabgle. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws a colored rectangle. Parameters x and y specify the top left corner of the
	 * rectabgle. Parameters width and height specify its width and height. Parameter
	 * color specifies the color.
	 */
	public void fillRect(int x, int y, int width, int height);

	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);
	
	/**
	 * Draws a hexagon. Parameters x and y specify the top left corner of the
	 * hexagon. Parameters width and height specify its width and height.
	 */
	public void drawHexagon(int x, int y, int width, int height);
	
	/**
	 * Gets the current color of the painter
	 */
	public Color getColor();
	
	/**
	 * Sets the current color of the painter
	 */
	public void setColor(Color color);
	
	/**
	 * Changes current origin to new position
	 */
	public void translate( int x, int y );
	
	/**
	 * Should draw string centred on shape 
	 * @param string the string to be drawn
	 * @param x coordinate of shape to be drawn on
	 * @param y coordinate of shape to be drawn on
	 * @param width of shape
	 * @param height of shape
	 */
	public void drawCentredString(String string, int x, int y, int width, int height);
	
	/**
	 * Draws image
	 * @param img to be drawn
	 * @param x coordinate of shape to be drawn on
	 * @param y coordinate of shape to be drawn on
	 * @param width of shape
	 * @param height of shape
	 */
	public void drawImage(Image img, int x, int y, int width, int height);
}
