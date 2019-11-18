package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;

/**
 * This class acts as an adaptor for TreeModel by translating all commands 
 * from ShapeModel into the appropriate TreeModel function
 * @author Leon C
 *
 */
public class Task1 implements TreeModel {
	private ShapeModel _model;
	protected List<TreeModelListener> _listeners;
	
	/**
	 * Constructor that takes in a ShapeModel to adapt into a TreeModel
	 * @param model to adapt
	 */
	public Task1(ShapeModel model) {
		_model = model;
		_listeners = new ArrayList<TreeModelListener>();
	}

	
	@Override
	public Object getRoot() {
		return _model.root();
	}

	@Override
	public Object getChild(Object parent, int index) {
		try {
			if(parent instanceof CarrierShape) {
				CarrierShape carrierParent = (CarrierShape) parent;
				return carrierParent.shapeAt(index);
			}
		} catch (IndexOutOfBoundsException e) {
			//do nothing
		}
		return null;

	}

	@Override
	public int getChildCount(Object parent) {
		//downcasting to access method
		if(parent instanceof CarrierShape) {
			CarrierShape carrierParent = (CarrierShape) parent;
			return carrierParent.shapeCount();
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		//only CarrierShapes cannot be leaves
		if(node instanceof CarrierShape) {
			return false;
		}
		return true;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// not required

	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		//need to check and downcast inputs
		if(parent instanceof CarrierShape && child instanceof Shape) {
			CarrierShape carrierParent = (CarrierShape) parent;
			Shape shapeChild = (Shape) child;
			return carrierParent.indexOf(shapeChild);
		}
		//return -1 if not the right instances
		return -1;
	}

	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		_listeners.add(listener);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		_listeners.remove(listener);
	}
}
