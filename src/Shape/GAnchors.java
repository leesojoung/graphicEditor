package Shape;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

public class GAnchors implements Serializable {
	private final int w = 20;
	private final int h = 20;
	private final int dw = w/2;
	private final int dh = h/2;
	public enum EAnchors{
		NW, NN, NE, EE, SE, SS, SW, WW, RR//9개 +앵커위에 없는경우null
	}
	private Vector<Ellipse2D> anchors;
	@SuppressWarnings("unused")
	public GAnchors() {
		this.anchors = new Vector<Ellipse2D>();
		for(EAnchors eAnchor : EAnchors.values()) {
			this.anchors.add(new Ellipse2D.Double(0, 0, w, h));
		}
	}
	public EAnchors onShape(int x, int y) {
		for(int i=0; i<EAnchors.values().length; i++) {
			if(this.anchors.get(i).contains(x, y)) {
				System.out.println("GAnchor's name : " + EAnchors.values()[i]);
				return EAnchors.values()[i];
			}
		}
		return null;//null상태 
		
	}
	
	public Vector<Ellipse2D> getAnchors() {	return anchors;	}
	public void setAnchors(Vector<Ellipse2D> anchors) {	this.anchors = anchors;}
	
	public void draw(Graphics2D graphics2D) {
//		graphics2D.setPaintMode();
		for(Shape shape : this.anchors) {
//			Color color = graphics2D.getColor();
			graphics2D.draw(shape);
//			graphics2D.setColor(Color.white);
//			graphics2D.fill(shape);
//			graphics2D.setColor(color);
		}
//		graphics2D.setXORMode(Color.white);
	}
	public void setBoundingRect(Rectangle r) {
		for(EAnchors eAnchor : EAnchors.values()) {
			int x=0, y=0;
			switch(eAnchor) {
			case NW:
				x = r.x;
				y = r.y;
				break;
			case NN:
				x = r.x + r.width/2;
				y = r.y;
				break;
			case NE:
				x = r.x + r.width;
				y = r.y;
				break;
			case EE:
				x = r.x + r.width;
				y = r.y + r.height/2;
				break;
			case SE:
				x = r.x + r.width;
				y = r.y + r.height;
//				setCursor(Cursor.MOVE_CURSOR);
				break;
			case SS:
				x = r.x + r.width/2;
				y = r.y + r.height;
				break;
			case SW:
				x = r.x ;
				y = r.y + r.height;
				break;
			case WW:
				x = r.x ;
				y = r.y + r.height/2;
				break;
			case RR:
				x = r.x + r.width/2 ;
				y = r.y - 50;
				break;
			}
			x = x - dw;
			y = y - dh;
			this.anchors.get(eAnchor.ordinal()).setFrame(x, y,w, h);
		}
	}
	
}
