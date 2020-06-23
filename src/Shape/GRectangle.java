package Shape;

import java.awt.Graphics2D;

public class GRectangle extends GShape {
	private static final long serialVersionUID = 1L;
	private java.awt.Rectangle rectangle;
	
	public GRectangle() {
		super();//Shape �ҷ���
		this.shape = new java.awt.Rectangle();
		this.rectangle = (java.awt.Rectangle)this.shape;
	}
	
	@Override
	public GShape newInstance() {
		return new GRectangle();
		//new�ϸ� �ʱ�ȭ�� �ϳ��� ���ο� �޸� ���� 
	}

	public void setOrigin(int x, int y){
		this.rectangle.setBounds(x,y,0,0);
	}
	public void setPoint(int x, int y){
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
	}
	public void addPoint(int x, int y) {
	}
	
	public  void keepMoving(Graphics2D graphics2d, int x, int y) {
		int dw = x - px;
		int dh = y - py;
		//��ǥ���ư�
		this.rectangle.setLocation(this.rectangle.x + dw, this.rectangle.y + dh);
		
		this.px = x;
		this.py = y;
	}
	public void finishMoving(Graphics2D graphics2d, int x, int y) {
		
	}
}
