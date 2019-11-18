package spaceshapes;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to represent a carrier space-shape that can contain other shapes
 * 
 * @author lchi184
 *
 */
public class CarrierShape extends Shape {
	//list that stores contained shapes
	private List<Shape> _children;
	
	/**
	 * Default Constructor
	 */
	public CarrierShape() {
		super();
		_children = new ArrayList<Shape>();
	}
	
	/**
	 * Creates a CarrierShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 */
	public CarrierShape(int x, int y){
		super(x,y);
		_children = new ArrayList<Shape>();
	}
	
	/**
	 * Creates a CarrierShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public CarrierShape(int x, int y, int deltaX, int deltaY){
		super(x,y,deltaX,deltaY);
		_children = new ArrayList<Shape>();
	}
	
	/**
	 * Creates a CarrierShape instance with specified values for instance 
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
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
		_children = new ArrayList<Shape>();
	}
	
	/**
	 * Creates a CarrierShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param text to be displayed
	 */
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height, text);
		_children = new ArrayList<Shape>();
	}
	
	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width - width of two-dimensional world.
	 * @param height - height of two-dimensional world.
	 */
	public void move(int width, int height) {
		//move itself
		super.move(width, height);
		//move all shapes inside container with the given width and height of CarrierShape
		for(Shape shape: _children) {
			shape.move(_width, _height);
		}
	}
	
	/**
	 * Paints this DynamicShape object using the supplied Painter object. 
	 */
	public void doPaint(Painter painter) {
		painter.drawRect(_x,_y,_width,_height);
		//translates origin of painter and paints children
		painter.translate(_x,_y);
		for(Shape shape : _children) {
			shape.paint(painter);
		}
		//returns painter origin to previous position 
		painter.translate(-_x,-_y);
	}
	
	/**
	 * Adds given parameter shape to carrierShape. will throw exception if shape already
	 * has a parent or if shape is out of bonds relative to carrier shape
	 */
	public void add(Shape shape) throws IllegalArgumentException{
		if(shape.parent() != null || shape.isOutOfBounds(_width, _height)) {
			throw new IllegalArgumentException();
		}
		_children.add(shape);
		shape.addParent(this);
	}
	
	/**
	 * checks if shape given is in CarrierShape
	 */
	public boolean contains(Shape containedShape) {
		return _children.contains(containedShape);
	}
	
	/**
	 * removes shape given from CarrierShape
	 */
	public void remove(Shape containedShape) {
		_children.remove(containedShape);
		containedShape.removeParent();
	}
	
	/**
	 * returns shape at given index
	 */
	public Shape shapeAt(int index) throws IndexOutOfBoundsException {
		return _children.get(index);
	}
	
	/**
	 * returns the number of shapes in CarrierShape
	 */
	public int shapeCount() {
		return _children.size();
	}
	
	/**
	 * finds the index of the given shape inside CarrierShape
	 */
	public int indexOf(Shape shape) {
		return _children.indexOf(shape); 
	}
	
}