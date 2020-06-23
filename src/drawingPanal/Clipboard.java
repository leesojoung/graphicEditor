package drawingPanal;

import java.util.Vector;

import Shape.GShape;

public class Clipboard {
	private Vector<GShape> shapes;
	
	public Clipboard() {
		this.shapes = new Vector<GShape>();
	}
	
	public void setContents(Vector<GShape> shapes) {
		this.shapes.clear();//이전 cut 날리기
		this.shapes.addAll(shapes);
	}
	
	public Vector<GShape> getContents() {
		 Vector<GShape> clonedshapes = new Vector<GShape>();
		 for(GShape shape : this.shapes) {
			 GShape clonedshape = shape.clone();
			 if(shape == clonedshape) {
				 System.out.println("ok");
			 }
			 clonedshapes.add(clonedshape);
		 }
		return clonedshapes;//shape이 cloneable 해야함
	}
}
