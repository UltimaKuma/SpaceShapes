package spaceshapes;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(new Color(212, 212, 212));
	}

	/**
	 * @see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}
	
	/**
	 * @see spaceshapes.Painter.drawRFilledect
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * @see spaceshapes.Painter.drawHexagon
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
	 * @see spaeshapes.Painter.getColor
	 */
	public Color getColor() {
		return _g.getColor();
	}
	
	/**
	 * @see spaeshapes.Painter.setColor
	 */
	public void setColor(Color color) {
		_g.setColor(color);
	}
	
	/**
	 * @see spaeshapes.Painter.translate
	 */
	public void translate(int x, int y) {
		_g.translate(x, y);
	}
	
	/**
	 * @see spaeshapes.Painter.drawCentredString
	 */
	public void drawCentredString(String string, int x, int y, int width, int height) {
		FontMetrics fontMetrics = _g.getFontMetrics();
		int textWidth = fontMetrics.stringWidth(string);
		int textAscent = fontMetrics.getAscent();
		int textDescent = fontMetrics.getDescent();
		
		int adjustedX = x + (width-textWidth)/2;
		int adjustedY = y + height/2 + (textAscent - textDescent)/2;
		
		_g.drawString(string, adjustedX, adjustedY);
	}
	
	/**
	 * @see spaeshapes.Painter.drawImage
	 */
	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img, x, y, width, height, null);
	}
}
