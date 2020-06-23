package drawingPanal;

import java.util.Vector;

import Shape.GShape;

public class Clipboard {
	private Vector<GShape> shapes;
	
	public Clipboard() {
		this.shapes = new Vector<GShape>();
	}
	
	public void setContents(Vector<GShape> shapes) {
		this.shapes.clear();//���� cut ������
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
		return clonedshapes;//shape�� cloneable �ؾ���
	}
}
