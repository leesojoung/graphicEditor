package Shape;

import java.awt.Graphics2D;

public class GPolygon extends GShape {
	private java.awt.Polygon polygon;
	
	public GPolygon() {
		super();
		
		this.shape = new java.awt.Polygon();
		this.polygon = (java.awt.Polygon)this.shape;
	}
	public GShape newInstance() {
		return new GPolygon();
		//new�ϸ� �ʱ�ȭ�� �ϳ��� ���ο� �޸� ���� / ���� Ŭ������ ���õ� ���ο� �޸𸮸� ���� = newinstance�� ����
	}
	public void setOrigin(int x, int y){
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	public void setPoint(int x, int y){//���� �����̴� ��	ī�Ǹ� �ؼ� ��ٰ� ���� �����͸� ��� ������ �ִ´�.
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}
	public  void keepMoving(Graphics2D graphics2d, int x, int y) {
		int dw = x - this.px;
		int dh = y - this.py;
		
		this.polygon.translate(dw, dh);
		
		this.px = x;
		this.py = y;
	}
	public void finishMoving(Graphics2D graphics2d, int x, int y) {
	}
}
