package Shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Shape.GAnchors.EAnchors;

public abstract class GShape implements Cloneable, Serializable {
	public enum EOnState {eOnShape, eOnResize, eOnRotate} ;
	//attribute �Ӽ�
	private static final long serialVersionUID = 1L;
	protected int px;
	protected int py;
	
	//components
	//class id : pointer �ٸ����� �޸𸮰� �ִ�. shape�� anchors�� copy�ȵ�.
	protected  java.awt.Shape shape;
	protected AffineTransform affineTransform;
	protected EAnchors selectedAnchor; 
	private GAnchors anchors;
	private Color lineColor, fillColor;
	
	private boolean selected;
	public Shape getShape() { return this.shape;}
	public boolean isSelected() {return selected;}//boolean�̱� ������ is�̴�.
	public void setSelected(boolean selected) {	
		this.selected = selected;
	}
	
	public GShape() {
		this.selected = false;
		this.anchors = new GAnchors();
		affineTransform = new AffineTransform(); 
		this.lineColor = Color.BLACK;
		this.fillColor = null;
	}
	
	public abstract void setOrigin(int x, int y);
	public abstract void setPoint(int x, int y);
	public abstract void addPoint(int x, int y);
	
	public void setPx(int px) {
		this.px = px;
	}
	public void setPy(int py) {
		this.py = py;
	}
	public void initMoving(Graphics2D graphics2d, int x, int y) {
			this.px = x;
			this.py = y;
			if(!selected) {
				this.selected =true;
				this.anchors.setBoundingRect(this.shape.getBounds());
				graphics2d.setXORMode(Color.WHITE);//anchor ó�� �����
				this.anchors.draw(graphics2d);
			}
	}
	public abstract void keepMoving(Graphics2D graphics2d, int x, int y);
	public abstract void finishMoving(Graphics2D graphics2d, int x, int y);
	
	public GShape clone() {
		try {
			//���ٷ� ������  data�� type������ ������ ����
			ByteArrayOutputStream  byteArrayOutputStream  = new ByteArrayOutputStream(); 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);
			
			System.out.println(this);
			
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream= new ObjectInputStream(byteArrayInputStream);
			return (GShape)objectInputStream.readObject();
			//affinetransform���� shape�� �Ű��൵ �ȴ�.=new �ȴ�. GShape���� 
		
		} catch (IOException |ClassNotFoundException  e) {
			e.printStackTrace();
		}
		return null;
	}
	
	abstract public GShape newInstance();//�������� ���ο� �׸��� �׸��� object�ϳ��� ���������.
	
	
	public void draw(Graphics2D graphics2d){
		if(this.fillColor != null){
			graphics2d.setColor(fillColor);
			graphics2d.fill(this.shape);
		}
		graphics2d.setColor(this.lineColor);
		graphics2d.draw(this.shape);
		if(this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}
	public EOnState onShape(int x, int y) {//��� ���׶�̿� �ִ��� Ȯ��
		if(this.selected) {
			this.selectedAnchor = this.anchors.onShape(x, y);
			if(this.selectedAnchor == EAnchors.RR) {//rotate
				return EOnState.eOnRotate;
			}else if(this.selectedAnchor == null/*�ۿ� �մ°��*/) {
				if(this.shape.contains(x,y)) {
					//��Ŀ���� ���� �� ���� ������ �����̴�.
					
					return EOnState.eOnShape;
				}
			}else {//resize
				return EOnState.eOnResize;
			}
		}else {
			if(this.shape.contains(x,y)) {
		
				//��Ŀ���� ���� �� ���� ������ �����̴�.
				return EOnState.eOnShape;
			}
		}
		return null;
	}
	public void moveReverse(Point2D resizeAnchor){ 
		affineTransform.setToTranslation(-resizeAnchor.getX(), -resizeAnchor.getY()); 
		shape = (affineTransform.createTransformedShape(shape)); 
		System.out.println("v=move");
	}
	public void move(Point2D resizeAnchor){ 
		affineTransform.setToTranslation(resizeAnchor.getX(), resizeAnchor.getY());
		shape = (affineTransform.createTransformedShape(shape));
		System.out.println("move");
	} 
	public EAnchors getSelectedAnchor() {return this.selectedAnchor;} 
	public GAnchors getAnchorList(){ return anchors; } 
	public Rectangle getBounds(){ return shape.getBounds(); } 
	public void resizeCoordinate(Point2D resizeFactor){ 
		affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY()); 
		shape = (affineTransform.createTransformedShape(shape));
	}
	public void setLineColor(Color color) {
		this.lineColor = color;
	}
	public void setFillColor(Color color) {
		this.fillColor = color;
	}
}
