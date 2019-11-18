package spaceshapes.views;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelEvent.EventType;
import spaceshapes.ShapeModelListener;

public class Task2 extends Task1 implements ShapeModelListener{
	
	public Task2(ShapeModel model) {
		super(model);
	}

	@Override
	public void update(ShapeModelEvent event) {
		EventType type = event.eventType();
		//if type is shape moved, tree should not be affected thus skip the update
		if(type == EventType.ShapeMoved) {
			return;
		}
		//getting parameters required to generate the tree model event
		TreePath path = new TreePath(event.parent().path().toArray());
		int[] childIndices = { event.index() };
		Shape[] children = { event.operand() };
		Shape source = event.parent();
		//generating event
		TreeModelEvent treeModelEvent = new TreeModelEvent(source, path, childIndices, children);
		for(TreeModelListener listener: _listeners) {
			//sending node inserted event
			if(type == EventType.ShapeAdded) {
				listener.treeNodesInserted(treeModelEvent);
			//sending node removed event
			}else if(type == EventType.ShapeRemoved) {
				listener.treeNodesRemoved(treeModelEvent);
			}
		}

	}	
}
