package spaceshapes;

import java.awt.Color;
import java.awt.Image;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging simply logging them. The contents of a MockPainter object's
 * log can be retrieved by a call to toString() on the MockPainter.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class MockPainter implements Painter {
	// Internal log.
	private StringBuffer _log = new StringBuffer();
	//default color
	private Color _color = new Color(212, 212, 212);
	/**
	 * Returns the contents of this MockPainter's log.
	 */
	public String toString() {
		return _log.toString();
	}

	/**
	 * Logs the drawRect call.
	 */
	public void drawRect(int x, int y, int width, int height) {
		_log.append("(rectangle " + x + "," + y + "," + width + "," + height + ")");
	}
	
	/**
	 * Logs drawFilledRect
	 */
	public void fillRect(int x, int y, int width, int height) {
		_log.append("(fillRect " + x + "," + y + "," + width + "," + height + ")");
	}
	
	/**
	 * Logs the drawOval call.
	 */
	public void drawOval(int x, int y, int width, int height) {
		_log.append("(oval " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawLine call.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_log.append("(line " + x1 + "," + y1 + "," + x2 + "," + y2 + ")");
	}
	
	/**
	 * Logs the drawHexagon call.
	 */
	public void drawHexagon(int x, int y, int width, int height) {
		if(width < 40) {
			//if the width of the hexagon is under 40 pixels, draw a diamond
			drawLine(x, y + height/2, x + width/2, y);
			drawLine(x + width/2, y, x + width, y + height/2);
			drawLine(x + width, y + height/2, x + width/2, y + height);
			drawLine(x + width/2, y + height, x, y + height/2);
		}else{
			//otherwise draw a hexagon starting from the LHS going clockwise
			drawLine(x, y + height/2, x+20, y);
			drawLine(x+20, y, x + width - 20, y);
			drawLine(x + width - 20, y, x + width, y + height/2);
			drawLine(x + width, y + height/2, x + width - 20, y + height);
			drawLine(x + width - 20, y + height, x + 20, y + height);
			drawLine(x + 20, y + height, x, y + height/2);
		}
	}
	
	/**
	 * Returns current color
	 */
	public Color getColor() {
		return _color;
	}
	
	/**
	 * Logs the setColor call
	 */
	public void setColor(Color color) {
		_log.append("(" + color + ")");
	}
	
	/**
	 * Empty method as does not need to be logged
	 */
	public void translate(int x, int y) {
	}

	/**
	 * Empty method as does not need to be logged
	 */
	public void drawCentredString(String string, int x, int y, int width, int height) {
	}
	
	public void drawImage(Image img, int x, int y, int width, int height) {
		//sugma wh
	}
}