package menu;

import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import drawingPanal.GDrawingPanal;

public class GCoordinate extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GDrawingPanal drawingPanal;
	private JLabel jLabel;
	
	public GCoordinate(){
		jLabel = new JLabel();
		jLabel.setText("");
		this.add(this.jLabel);
	}

	public void coordinate(Point point) {
		jLabel.setText(point.x+ " "+point.y);
	}
	public void associate(GDrawingPanal drawingPanal) {
		this.drawingPanal = drawingPanal;
	}

	public void initialize() {
		
	}
}
