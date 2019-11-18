package spaceshapes;

import static org.junit.Assert.assertEquals;

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
public class TestHexagon {
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
	 * Test if big hexagon is made properly
	 */
	@Test
	public void testBigHexagon() {
		HexagonShape shape = new HexagonShape(100, 20, 5, 5, 100, 100);
		shape.paint(_painter);
		assertEquals("(line 100,70,120,20)(line 120,20,180,20)"
				+ "(line 180,20,200,70)(line 200,70,180,120)"
				+ "(line 180,120,120,120)(line 120,120,100,70)", _painter.toString());
	}
	
	/**
	 * Test if small hexagon is made properly
	 */
	@Test
	public void testSmallHexagon() {
		HexagonShape shape = new HexagonShape(100, 20, 5, 5, 30, 30);
		shape.paint(_painter);
		assertEquals("(line 100,35,115,20)(line 115,20,130,35)"
				+ "(line 130,35,115,50)(line 115,50,100,35)", _painter.toString());
	}
}
