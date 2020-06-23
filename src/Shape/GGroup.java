package Shape;

import java.util.Vector;

public class GGroup extends GRectangle {
	private static final long serialVersionUID = 1L;

	
	private Vector<GShape> containedshapes;
	public GGroup(){
		this.containedshapes = new Vector<GShape>();
	}
	public GShape newInstance() {
		return new GGroup();
		//new하면 초기화된 하나의 새로운 메모리 생성 
	}
	
	public void contains(Vector<GShape> shapeVector) {//그림들이 있는지 확인
		for(GShape shape : shapeVector) {
			//안쪽의 shape을 끌어와서 물어봐야함.
			if(this.getShape().contains(shape.getShape().getBounds2D())) {
				this.containedshapes.add(shape);
				shape.setSelected(true);
			}
		}
	}
	
}
