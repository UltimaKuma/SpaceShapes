package spaceshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * Simple GUI program to show an animation of shapes in a confined space. Class AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of 
 * AnimationViewer can be added to a JFrame object. A JFrame object is a 
 * window that can be closed, minimised, and maximised. The state of an
 * AnimationViewer object comprises a list of Shapes and a Timer object. An
 * AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the AnimationViewer iterates 
 * through a list of Shapes requesting that each Shape paints and moves itself.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {
	// Frequency in milliseconds for the Timer to generate events.
	private static final int DELAY = 20;

	// Collection of Shapes to animate.
	private List<Shape> _shapes;

	private Timer _timer = new Timer(DELAY, this);

	/**
	 * Creates an AnimationViewer instance with a list of Shape objects and 
	 * starts the animation.
	 */
	public AnimationViewer() {
		this.setBackground(Color.BLACK);
		_shapes = new ArrayList<Shape>();
	
		// Populate the list of Shapes.
		_shapes.add(new RectangleShape(0, 0, 2, 3, 60, 60));
		_shapes.add(new RectangleShape(10, 10, 4, 2, 60, 60));
		_shapes.add(new OvalShape(20, 20, -3, 5, 70, 70));
		_shapes.add(new HexagonShape(20, 20, 3, -2, 80, 60));
		_shapes.add(new DynamicShape(30, 10, 5, 3, 80, 80, new Color(150, 40, 200)));	
		_shapes.add(new DynamicShape(30, 10, -6, 4, 80, 80, new Color(150, 200, 20)));
		_shapes.add(new DynamicShape(30, 10, -5, -2, 80, 80, new Color(100, 200, 200)));		
		
		CarrierShape bottomCarrier = new CarrierShape(10, 10, 5, 3, 50, 100);
		bottomCarrier.add(new OvalShape(20, 20, -3, 5, 10, 10));
		Shape crazy = new DynamicShape(30, 10, -2, 3, 10, 10, new Color(24, 209, 46));
		crazy.addText("donacdum");
		bottomCarrier.add(crazy);		
		
		CarrierShape middleCarrier = new CarrierShape(120, 120, 5, 3, 150, 150);
		middleCarrier.add(new OvalShape(20, 20, randomRange(-10, 10), randomRange(-10, 10), 20, 20));
		middleCarrier.add(new HexagonShape(20, 20, randomRange(-10, 10), randomRange(-10, 10), 20, 20));
		middleCarrier.add(new DynamicShape(30, 10, randomRange(-10, 10), randomRange(-10, 10), 20, 20, new Color(18, 82, 3)));
		middleCarrier.add(bottomCarrier);
		
		CarrierShape topCarrier = new CarrierShape(100, 100, -1, 2, 400, 400);
		topCarrier.add(new DynamicShape(30, 10, randomRange(-10, 10), randomRange(-10, 10), 40, 40, new Color(200, 40, 80)));
		topCarrier.add(new DynamicShape(30, 10, randomRange(-10, 10), randomRange(-10, 10), 40, 40, new Color(20, 40, 200)));
		topCarrier.add(middleCarrier);
		topCarrier.addText("D    V    D");
		
		_shapes.add(topCarrier);
		
		
		
		Shape rect = new DynamicShape(0, 0, 8, -6, 100, 50, new Color(50, 50, 50));
		rect.addText("According to all known laws of aviation, there is no way a bee should be able to fly.");
		_shapes.add(rect);
		
		// Start the animation.
		_timer.start();
	}

	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been opened, exposed or moved.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);
		
		// Calculate bounds of animation screen area.
		int width = getSize().width;
		int height = getSize().height;
		
		// Create a GraphicsPainter that Shape objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);
		
		// Progress the animation.
		for(Shape s : _shapes) {
			s.paint(painter);
			s.move(width, height);
		}
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent. ActionEvents are
	 * received by the Timer.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationViewer repaints itself. The call to 
		// repaint() will cause the AnimationViewer's paintComponent() method 
		// to be called.
		repaint();
	}
	
	
	/**
	 * Main program method to create an AnimationViewer object and display this
	 * within a JFrame window.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Animation viewer");
				frame.add(new AnimationViewer());
		
				// Set window properties.
				frame.setSize(1000, 1000);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	public static int randomRange(int min, int max) {
		int result = (int)((Math.random() * ((max - min) + 1)) + min);
		return result;
	}
}
