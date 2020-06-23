package drawingPanal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
//import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import Shape.GGroup;
import Shape.GPolygon;
import Shape.GShape;
import Shape.GShape.EOnState;
import global.GConstants.EToolBar;
import menu.GCoordinate;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GTransformer;

public class GDrawingPanal extends JPanel {
	//attribute
	private static final long serialVersionUID = 1L;
	
	//components
	private MouseHandler mouseHandler;
	private Clipboard clipboard;
	private Vector<GShape> shapeVector;//�׸�����
	public Vector<GShape> getShapeVector(){//�������� ������
		return this.shapeVector;}//�׸�����
	public void restoreShapeVector(Object object) {//open���� �� �̰����� ����.
		if(object == null) {
			this.shapeVector.clear();
		} else {
			this.shapeVector = (Vector<GShape>)object;
		}
		this.repaint();
	}
	
	//working variables
	//polygond���� CMC�̿��Ѵ�. 
	private enum EActionState {eReady, eTransforming} ;
	private EActionState eActionState;
	

	private GShape currentShape;
	private GTransformer transformer;
	private GShape currentTool;
	public void setCurrentTool(EToolBar currentTool) {
		this.currentTool = currentTool.getShape();
	}
	//transforming�� ��ٴ� ���� �ǹ�
	private boolean updated;
	public boolean isUpdated() {return this.updated;}
	public void setUpdated(boolean update) {this.updated = updated;}
	private int shipX, shipY;
	
	private GCoordinate coordinate; 
	public void associate(GCoordinate coordinate2) {
		this.coordinate = coordinate2;
	}
	
	public GDrawingPanal(){
		this.eActionState = EActionState.eReady;
		this.updated = false;//�ʱⰪ�� �׳� �������� ������ ����.
		
		this.setForeground(Color.black);
		this.setBackground(Color.white);
		
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		
		this.clipboard = new Clipboard();
		
		this.shapeVector=new Vector<GShape>();
		this.transformer = null;
	}
	public void initialize() {
		
	}
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D)graphics;
		super.paint(graphics2d);//�θ���� �׸���- �ȱ׸��� �׸�����
		
		for(GShape shape:this.shapeVector) {
			shape.draw(graphics2d);
		}
	}
	private void clearSelected(){
		for(GShape shape : this.shapeVector) {
			shape.setSelected(false);
		}
	}
	
	private EOnState onShape(int x, int y) {//�ؿ� �׸������� �����ϰ��Ѵ�. �׸��ִ��� ������ �Ǵ�
		this.currentShape = null;
		for(GShape shape : this.shapeVector) {
			EOnState eOnState = shape.onShape(x, y);
			if(eOnState != null) {
				this.currentShape = shape;
				return eOnState;
			}
		}
//		�ۿ� selection
		this.clearSelected();
		return null;
	}
	
	private void defineActionState(int x, int y) {
		EOnState eOnState = onShape(x, y);
		if(eOnState ==null) {
			this.clearSelected();//���� ������ clear//selected ���
			this.transformer = new GDrawer();
		}else {
			if(!(this.currentShape.isSelected())) {
				this.clearSelected();
				this.currentShape.setSelected(true);
			}
			switch(eOnState) {
			case eOnShape:
				this.transformer = new GMover();
				break;
			case eOnResize:
				this.transformer = new GResizer();
				break;
			case eOnRotate:
				this.transformer = new GRotator();
				break;
			default:
				//eception
				this.eActionState = null;
				break;
			}
		}
	}
	
	
	private void initTransforming(int x, int y) {
		if(this.transformer instanceof GDrawer) {
			this.currentShape = this.currentTool.newInstance();//�޸𸮺���
		}
		this.transformer.setgShape(this.currentShape);
		this.transformer.initTransforming((Graphics2D)this.getGraphics(), x, y);
	}
	private void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D)this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		this.transformer.keepTransforming(graphics2d, x, y);
	}
	private void finishTransforming(int x, int y) {
		this.transformer.finishTransforming((Graphics2D)this.getGraphics(), x, y);
		
		if(this.transformer instanceof GDrawer) {
			if(this.currentShape instanceof GGroup) {
				((GGroup)(this.currentShape)).contains(this.shapeVector);
			}else {
				this.shapeVector.add(this.currentShape);
			}
			
		}
		this.repaint();
		this.updated = true;
	}
	private void continueDrawing(int x, int y) {
		//�߰����� ��� �Լ� (������)
		this.currentShape.addPoint(x, y);
	}
	
	public void cut() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for(int i=this.shapeVector.size()-1; i>=0; i--) {
			if(this.shapeVector.get(i).isSelected()) {//������ ���É���� Ȯ��
				selectedShapes.add(this.shapeVector.get(i));
				this.shapeVector.remove(i);
			}
		}
		this.clipboard.setContents(selectedShapes);
		this.repaint();
	}
	public void copy() {
		Vector<GShape> shapes =new Vector<GShape>();
		shapes = this.clipboard.getContents();
		this.shapeVector.addAll(shapes);
		System.out.println("copy");
		
		this.repaint();
	}
	public void paste() {
		Vector<GShape> shapes =new Vector<GShape>();
		shapes = this.clipboard.getContents();
		this.shapeVector.addAll(shapes);
		System.out.println("paste");
		
		this.repaint();
	}
	
	public void setLineColor(Color color) {
		if(this.currentShape !=null){
			this.currentShape.setLineColor(color);
		}
		this.currentShape.draw((Graphics2D)this.getGraphics());
//		this.currentShape.drawAnchors((Graphics2D)this.getGraphics());
	}
	public void setFillColor(Color color) {
		if(this.currentShape !=null){
			this.currentShape.setFillColor(color);
		}
		this.currentShape.draw((Graphics2D)this.getGraphics());
//		this.currentShape.drawAnchors((Graphics2D)this.getGraphics());
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener{
		
		public void mouseClicked(MouseEvent e) {
			//���� ��ġ����  Ŭ���ϸ� ������, ������, Ŭ���� �Ͼ��. ���ÿ� �ι��Ͼ �� ����.
			if(e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}
		private void mouse1Clicked(MouseEvent e) {
			if(eActionState == EActionState.eReady) {
				if(currentTool instanceof GPolygon) {
//					initTransforming(e.getX(), e.getY());
					eActionState = EActionState.eTransforming;
				}
			} else if(eActionState == EActionState.eTransforming) {
				if(currentTool instanceof GPolygon) {
					continueDrawing(e.getX(), e.getY());
				}
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			if(eActionState == EActionState.eTransforming) {
				if(currentTool instanceof GPolygon) {
					finishTransforming(e.getX(), e.getY());
					eActionState = EActionState.eReady;
					
				}
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			ship(e.getX(), e.getY());
			if(eActionState == EActionState.eTransforming) {
				if(currentTool instanceof GPolygon) {
					if(transformer instanceof GDrawer) {
						keepTransforming(e.getX(), e.getY());
					}
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if(eActionState == EActionState.eReady) {
				if(currentTool instanceof GPolygon) {
					defineActionState(e.getX(), e.getY());
					initTransforming(e.getX(), e.getY());
					eActionState = EActionState.eReady;
				}else{
					  defineActionState(e.getX(), e.getY());
					  initTransforming(e.getX(), e.getY());
					  eActionState = EActionState.eTransforming;
				}
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if(eActionState == EActionState.eTransforming) {
				if(currentTool instanceof GPolygon) {
					
				}else { 
					System.out.println("release");
						finishTransforming(e.getX(), e.getY());
						eActionState = EActionState.eReady;
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			ship(e.getX(), e.getY());
			
			if(eActionState == EActionState.eTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	public void ship(int x, int y) {
		Point point = new Point(x, y);
		this.coordinate.coordinate(point);
	}	
	
	public int getShipX() {return shipX;}
	public int getShipY() {return shipY;}
	
	public Point getPoint() {
		Point point = new Point(this.shipX, this.shipY);
		return point;
	}
	
}
	
