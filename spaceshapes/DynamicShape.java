package spaceshapes;

import java.awt.Color;


/**
 * Class to represent a dynamic space-shape that changes depending on
 * the instances of wall bouncing
 * 
 * @author lchi184
 *
 */
public class DynamicShape extends Shape {
	//boolean for whether last bounce was horizontal, ie off the left or right side
	private boolean _lastBounceHorizontal;
	//color object that dictates color of shape
	private Color _color;
	
	/**
	 * Default Constructor
	 */
	public DynamicShape() {
		super();
		_lastBounceHorizontal = false;
		_color = new Color(212, 212, 212);
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY){
		super(x,y,deltaX,deltaY);
		_lastBounceHorizontal = false;
		_color = new Color(212, 212, 212);
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
		_lastBounceHorizontal = false;
		_color = new Color(212, 212, 212);
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param text to be added
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height, text);
		_lastBounceHorizontal = false;
		_color = new Color(212, 212, 212);
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param color Color object for color of DynamicShape
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_lastBounceHorizontal = false;
		_color = color;
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param text to be added
	 * @param color Color object for color of DynamicShape
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
		super(x,y,deltaX,deltaY,width,height,text);
		_lastBounceHorizontal = false;
		_color = color;
	}
	
	@Override
	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width - width of two-dimensional world.
	 * @param height - height of two-dimensional world.
	 */
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
			_lastBounceHorizontal = true;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
			_lastBounceHorizontal = true;
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
			_lastBounceHorizontal = false;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
			_lastBounceHorizontal = false;
		}

		_x = nextX;
		_y = nextY;
	}
	
	/**
	 * Paints this DynamicShape object using the supplied Painter object. 
	 */
	public void doPaint(Painter painter) {
		if(_lastBounceHorizontal) {
			//if last bounce was horizontal use fillRect
			Color ogColor = painter.getColor();
			//change to color specified
			painter.setColor(_color);
			painter.fillRect(_x, _y, _width, _height);
			//change back to original color
			painter.setColor(ogColor);
		}else{
			//else use drawRect
			painter.drawRect(_x,_y,_width,_height);
		}
	}
	
}