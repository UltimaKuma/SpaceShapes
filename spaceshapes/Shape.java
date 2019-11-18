package spaceshapes;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public abstract class Shape {
	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;
	
	protected static final int DEFAULT_Y_POS = 0;
	
	protected static final int DEFAULT_DELTA_X = 5;
	
	protected static final int DEFAULT_DELTA_Y = 5;
	
	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;
	// ===

	// === Instance variables, accessible by subclasses.
	protected int _x;

	protected int _y;

	protected int _deltaX;

	protected int _deltaY;

	protected int _width;

	protected int _height;
	
	protected CarrierShape _parent;
	
	protected String _text;
	// ===

	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		_parent = null;
	}
	
	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		_parent = null;
		_text = text;
	}
	
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
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
		}

		_x = nextX;
		_y = nextY;
	}

	/**
	 * Paints the shape and the related text. Acts as a template method that cannot
	 * be overidden. (Template Method Pattern)
	 */
	public final void paint(Painter painter) {
		this.doPaint(painter);
		this.paintText(painter);
	}
	
	/**
	 * Needs to be overidden, should draw the shape with given painter.
	 * Acts as a mandatory hook
	 */
	protected abstract void doPaint(Painter painter);

	/**
	 * Optionally overidden, draws the text with given painter. 
	 * Acts as an optional hook
	 */
	protected void paintText(Painter painter) {
		if(_text != null) {
			painter.drawCentredString(_text, _x, _y, _width, _height);
		}
	}
	
	/**
	 * Returns this Shape object's x position.
	 */
	public int x() {
		return _x;
	}
	
	/**
	 * Returns this Shape object's y position.
	 */
	public int y() {
		return _y;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaX() {
		return _deltaX;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaY() {
		return _deltaY;
	}
	
	/**
	 * Returns this Shape's width.
	 */
	public int width() {
		return _width;
	}
	
	/**
	 * Returns this Shape's height.
	 */
	public int height() {
		return _height;
	}
	
	/**
	 * Denotes parameter shape as the parent of this shape
	 */
	public void addParent(CarrierShape shape) {
		_parent = shape;
	}
	
	/**
	 * Removes parent shape
	 */
	public void removeParent() {
		_parent = null;
	}
	
	/**
	 * Returns parent shape
	 */
	public CarrierShape parent() {
		return _parent;
	}
	
	/**
	 * Returns a String whose value is the fully qualified name of this class 
	 * of object. E.g., when called on a RectangleShape instance, this method 
	 * will return "spaceshapes.RectangleShape".
	 */
	public String toString() {
		return getClass().getName();
	}
	
	/**
	 * Returns a list of shapes that describes the path from the root of the 
	 * shape to the one called
	 */
	public List<Shape> path() {
		List<Shape> path = new ArrayList<Shape>();
		this.addShapeToPath(path);
		return path;
	}
	
	/**
	 * Adds current path to List<Shape> path after calling its parent to do so
	 * Will make a path from the parent to the child called on
	 */
	public List<Shape> addShapeToPath(List<Shape> path) {
		if(_parent != null) {
			_parent.addShapeToPath(path);
		}
		path.add(this);
		return path;
	}
	
	/**
	 * Checks if current shape is out of bounds relative to the size of the container given
	 */
	public boolean isOutOfBounds(int width, int height) {
		if (_x < 0 || _x + _width > width) {
			return true;
		}
		if (_y < 0 || _y + _height > height) {
			return true;
		}
		return false;
	}
	
	/**
	 * Adds text to be drawn in shape
	 * @param text a String to be displayed in Shape
	 */
	public void addText(String text) {
		_text = text;
	}
	
	/**
	 * Adds text to be drawn in shape
	 * @param text a String to be displayed in Shape
	 */
	public String text() {
		return _text;
	}
}
