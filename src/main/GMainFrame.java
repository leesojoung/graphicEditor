package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawingPanal.GDrawingPanal;
import global.GConstants.EMainFrame;
import menu.GCoordinate;
import menu.GMenubar;
import toolbar.GToolBar;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//components
	private GMenubar menubar;
	private GToolBar toolBar;
	private GDrawingPanal drawingPanal;
	private GCoordinate coordinate;
	
	public GMainFrame(){
		//attributes 속성
		this.setLocation(EMainFrame.x.getValue(), EMainFrame.y.getValue());
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		//components 자식
		this.menubar = new GMenubar();
		this.setJMenuBar(this.menubar);
		
		this.toolBar = new GToolBar();
		this.add(this.toolBar,BorderLayout.NORTH);
		
		this.drawingPanal = new GDrawingPanal();
		this.add(this.drawingPanal, BorderLayout.CENTER);
		
		this.coordinate = new GCoordinate();
		this.add(this.coordinate, BorderLayout.SOUTH);
	}

	public void initialize() {
		this.menubar.associate(this.drawingPanal);
		this.toolBar.associate(this.drawingPanal);
		this.drawingPanal.associate(this.coordinate);
		
		this.menubar.initialize();
		this.toolBar.initialize();
		this.drawingPanal.initialize();
		this.coordinate.initialize();
	}

}