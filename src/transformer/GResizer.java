package transformer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import Shape.GAnchors.EAnchors;

public class GResizer extends GTransformer {
	private Point2D resizeAnchor; 
	private Point previousP;
	
	public GResizer() {
		previousP = new Point(); 
	}
	
	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		Point p = new Point(x, y);
		previousP = p; 
		resizeAnchor = getResizeAnchor(); 
		getgShape().moveReverse(resizeAnchor); 
	}
	@Override
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		Point p = new Point(x, y);
		transfomer(graphics2d, p);
	}
	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		this.getgShape().move(resizeAnchor);
	}

	public void transfomer(Graphics2D g2D, Point p) { 
		g2D.setXORMode(Color.white);
		Stroke dashed = new BasicStroke(1, BasicStroke. CAP_ROUND , BasicStroke. JOIN_ROUND , 10,  new float []{4}, 0);
		g2D.setStroke(dashed);
		Point2D resizeFactor = computeResizeFactor(previousP, p); 
		AffineTransform tempAffine = g2D.getTransform(); 
		g2D.translate(resizeAnchor.getX(), resizeAnchor.getY()); 
		getgShape().draw(g2D); 
		getgShape().resizeCoordinate(resizeFactor); 
		getgShape().draw(g2D); 
		g2D.setTransform(tempAffine); 
		previousP = p; 
	}

	private java.awt.geom.Point2D.Double computeResizeFactor(Point previousP, Point currentP){ 
		System.out.println("ÀÌÀüÁÂÇ¥x // ÇöÀçÁÂÇ¥x : "+ previousP.getX() + " //" + currentP.getX());
		System.out.println("ÀÌÀüÁÂÇ¥y // ÇöÀçÁÂÇ¥y : "+previousP.getY() + " //" + currentP.getY());
		

		double deltaW = 0; 
		double deltaH = 0; 
		if (getgShape().getSelectedAnchor() == EAnchors. NW ) {
			deltaW=-(currentP.x-previousP.x);
			deltaH=-(currentP.y-previousP.y); 
			System.out.println(getgShape().getSelectedAnchor());
		} else if (getgShape().getSelectedAnchor() == EAnchors. NN ) { 
			deltaW=  0; 
			deltaH=-(currentP.y-previousP.y); 
			System.out.println(getgShape().getSelectedAnchor());
		} else if (getgShape().getSelectedAnchor() == EAnchors. NE ) { 
			deltaW=  currentP.x-previousP.x;
			deltaH=-(currentP.y-previousP.y); 
			System.out.println(getgShape().getSelectedAnchor());
		} else if (getgShape().getSelectedAnchor() == EAnchors. WW ) { 
			deltaW=-(currentP.x-previousP.x);
			deltaH=  0; 
			System.out.println(getgShape().getSelectedAnchor());
		} else if (getgShape().getSelectedAnchor() == EAnchors. EE ) { 
			deltaW=  currentP.x-previousP.x;
			deltaH=  0; 
			System.out.println(getgShape().getSelectedAnchor());
		} else if (getgShape().getSelectedAnchor() == EAnchors. SW ) { 
			deltaW=-(currentP.x-previousP.x); 
			deltaH=  currentP.y-previousP.y; 
			System.out.println(getgShape().getSelectedAnchor());
		} else if (getgShape().getSelectedAnchor() == EAnchors. SS ) { 
			deltaW=  0; 
			deltaH=  currentP.y-previousP.y; 
			System.out.println(getgShape().getSelectedAnchor());
		} else if (getgShape().getSelectedAnchor() == EAnchors. SE ) { 
			deltaW=  currentP.x - previousP.x; 
			deltaH=  currentP.y - previousP.y; 
			System.out.println(getgShape().getSelectedAnchor());
			System.out.println("SE's	deltaW : "+deltaW+" // deltaH : " + deltaH);
		}
		double currentW = getgShape().getBounds().getWidth(); 
		double currentH = getgShape().getBounds().getHeight(); 
		double xFactor = 1.0;
		if (currentW > 0.0) 
			xFactor = (1.0+deltaW/currentW);
		double yFactor = 1.0; 
		if (currentH > 0.0) 
			yFactor = (1.0+deltaH/currentH); 
		return new Point.Double(xFactor, yFactor);
	} 
	
	private Point2D getResizeAnchor() {
		Point resizeAnchor = new Point(); 
		if (getgShape().getSelectedAnchor() == EAnchors. NW ) {
			resizeAnchor.setLocation(getgShape().getAnchorList().getAnchors().get( EAnchors. SE .ordinal()).getX(), 
					getgShape().getAnchorList().getAnchors().get(EAnchors. SE .ordinal()).getY()); 
			} else if (getgShape().getSelectedAnchor() == EAnchors. NN ) { 
				resizeAnchor.setLocation(0, 
					getgShape().getAnchorList().getAnchors().get( EAnchors. SS .ordinal()).getY()); 
			} else if (getgShape().getSelectedAnchor() == EAnchors. NE ) { 
				resizeAnchor.setLocation(getgShape().getAnchorList().getAnchors().get( EAnchors. SW .ordinal()).getX(), 
						getgShape().getAnchorList().getAnchors().get( EAnchors. SW .ordinal()).getY()); 
			} else if (getgShape().getSelectedAnchor() == EAnchors. WW ) { 
				resizeAnchor.setLocation(getgShape().getAnchorList().getAnchors().get( EAnchors. EE .ordinal()).getX(),0); 
			} else if (getgShape().getSelectedAnchor() == EAnchors. EE ) {
				resizeAnchor.setLocation(getgShape().getAnchorList().getAnchors().get( EAnchors. WW .ordinal()).getX(), 0); 
			} else if (getgShape().getSelectedAnchor() == EAnchors. SW ) { 
				resizeAnchor.setLocation(getgShape().getAnchorList().getAnchors().get( EAnchors. NE .ordinal()).getX(), 
						getgShape().getAnchorList().getAnchors().get( EAnchors. NE .ordinal()).getY()); 
			} else if (getgShape().getSelectedAnchor() == EAnchors. SS ) { 
				resizeAnchor.setLocation(0, 
						getgShape().getAnchorList().getAnchors().get( EAnchors. NN .ordinal()).getY()); 
			} else if (getgShape().getSelectedAnchor() == EAnchors. SE ) { 
				resizeAnchor.setLocation(getgShape().getAnchorList().getAnchors().get( EAnchors. NW .ordinal()).getX(), 
						getgShape().getAnchorList().getAnchors().get( EAnchors. NW .ordinal()).getY()); 
			} return resizeAnchor;
	} 
}
