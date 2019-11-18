package spaceshapes;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;


/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of RectangleShape. To test bounce dynamics use TestOval or TestShape as 
 * it is dependant on the Shape parent class
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class TestDynamicShape {
	// Fixture object that is used by the tests.
	private MockPainter _painter;

	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}

	/**
	 * Test if dynamicShape bounces off right wall correctly, should change to filledRect
	 */
	@Test
	public void testDynamicShapeBounceRight() {
		DynamicShape shape =  new DynamicShape(100, 100, 20, 0, 100, 100, new Color(30, 40, 50));
		shape.paint(_painter);
		shape.move(210, 210);
		shape.paint(_painter);
		shape.move(210, 210);
		shape.paint(_painter);
		assertEquals("(rectangle 100,100,100,100)"
				//color should be set to dynamic shape color and be reset back to original after fillRect
				//as bouncing off right wall should change to fullRect
				+ "(java.awt.Color[r=30,g=40,b=50])(fillRect 110,100,100,100)(java.awt.Color[r=212,g=212,b=212])"
				+ "(java.awt.Color[r=30,g=40,b=50])(fillRect 90,100,100,100)(java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	/**
	 * Test if dynamicShape bounces off left wall correctly, should change to filledRect
	 */
	@Test
	public void testDynamicShapeBounceLeft() {
		DynamicShape shape =  new DynamicShape(10, 100, -20, 0, 100, 100, new Color(30, 40, 50));
		shape.paint(_painter);
		shape.move(210, 210);
		shape.paint(_painter);
		shape.move(210, 210);
		shape.paint(_painter);
		assertEquals("(rectangle 10,100,100,100)"
				//color should be set to dynamic shape color and be reset back to original after fillRect
				//as bouncing off left wall should change to fullRect
				+ "(java.awt.Color[r=30,g=40,b=50])(fillRect 0,100,100,100)(java.awt.Color[r=212,g=212,b=212])"
				+ "(java.awt.Color[r=30,g=40,b=50])(fillRect 20,100,100,100)(java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	/**
	 * Test if dynamicShape bounces off bottom wall correctly, should stay rectangle
	 */
	@Test
	public void testDynamicShapeBounceBottom() {
		DynamicShape shape =  new DynamicShape(100, 100, 0, 20, 100, 100, new Color(30, 40, 50));
		shape.paint(_painter);
		shape.move(210, 210);
		shape.paint(_painter);
		shape.move(210, 210);
		shape.paint(_painter);
		assertEquals("(rectangle 100,100,100,100)"
				//as bouncing off bottom wall should stay as rectangle
				+ "(rectangle 100,110,100,100)"
				+ "(rectangle 100,90,100,100)", _painter.toString());
	}
	
	/**
	 * Test if dynamicShape bounces off top wall correctly, should stay rectangle
	 */
	@Test
	public void testDynamicShapeBounceTop() {
		DynamicShape shape =  new DynamicShape(100, 10, 0, -20, 100, 100, new Color(30, 40, 50));
		shape.paint(_painter);
		shape.move(210, 210);
		shape.paint(_painter);
		shape.move(210, 210);
		shape.paint(_painter);
		assertEquals("(rectangle 100,10,100,100)"
				//as bouncing off top wall should stay as rectangle
				+ "(rectangle 100,0,100,100)"
				+ "(rectangle 100,20,100,100)", _painter.toString());
	}
	
	/**
	 * Test if dynamicShape bounces off top then left correctly
	 */
	@Test
	public void testDynamicShapeBounceTopLeft() {
		DynamicShape shape =  new DynamicShape(10, 5, -5, -5, 100, 100, new Color(30, 40, 50));
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		assertEquals("(rectangle 10,5,100,100)"
				//should stay rectangle after bouncing off top wall
				+ "(rectangle 5,0,100,100)"
				//change to filledRect after bouncing off left wall
				+ "(java.awt.Color[r=30,g=40,b=50])(fillRect 0,5,100,100)(java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	/**
	 * Test if dynamicShape bounces off right then top correctly
	 */
	@Test
	public void testDynamicShapeBounceRightTop() {
		DynamicShape shape =  new DynamicShape(15, 10, 5, -5, 100, 100, new Color(30, 40, 50));
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		assertEquals("(rectangle 15,10,100,100)"
				//bouncing right wall so should turn to filled shape
				+ "(java.awt.Color[r=30,g=40,b=50])(fillRect 20,5,100,100)(java.awt.Color[r=212,g=212,b=212])"
				//should turn to rect after bouncing top wall
				+ "(rectangle 15,0,100,100)", _painter.toString());
	}
	
	/**
	 * Test if dynamicShape bounces off bottom then right correctly
	 */
	@Test
	public void testDynamicShapeBounceBottomRight() {
		DynamicShape shape =  new DynamicShape(10, 15, 5, 5, 100, 100, new Color(30, 40, 50));
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		assertEquals("(rectangle 10,15,100,100)"
				//stay as a rectangle as bottom first
				+ "(rectangle 15,20,100,100)"
				//change to filled rect as bounce off right
				+ "(java.awt.Color[r=30,g=40,b=50])(fillRect 20,15,100,100)(java.awt.Color[r=212,g=212,b=212])", _painter.toString());
	}
	
	/**
	 * Test if dynamicShape bounces off left then bottom correctly
	 */
	@Test
	public void testDynamicShapeBounceLeftBottom() {
		DynamicShape shape =  new DynamicShape(5, 10, -5, 5, 100, 100, new Color(30, 40, 50));
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		assertEquals("(rectangle 5,10,100,100)"
				//should change to filled rect as bounced off left
				+ "(java.awt.Color[r=30,g=40,b=50])(fillRect 0,15,100,100)(java.awt.Color[r=212,g=212,b=212])"
				//should change to rectangle as bounced off bottom
				+ "(rectangle 5,20,100,100)", _painter.toString());
	}
}
