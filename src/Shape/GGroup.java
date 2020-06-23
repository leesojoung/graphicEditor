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
		//new�ϸ� �ʱ�ȭ�� �ϳ��� ���ο� �޸� ���� 
	}
	
	public void contains(Vector<GShape> shapeVector) {//�׸����� �ִ��� Ȯ��
		for(GShape shape : shapeVector) {
			//������ shape�� ����ͼ� ���������.
			if(this.getShape().contains(shape.getShape().getBounds2D())) {
				this.containedshapes.add(shape);
				shape.setSelected(true);
			}
		}
	}
	
}
