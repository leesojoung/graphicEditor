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
		//new하면 초기화된 하나의 새로운 메모리 생성 / 여기 클래스에 관련된 새로운 메모리를 만듦 = newinstance와 같음
	}
	public void setOrigin(int x, int y){
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	public void setPoint(int x, int y){//따라서 움직이는 애	카피를 해서 썼다가 원래 데이터를 계속 가지고 있는다.
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
