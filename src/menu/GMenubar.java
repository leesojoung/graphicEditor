package menu;
import javax.swing.JMenuBar;

import drawingPanal.GDrawingPanal;
import global.GConstants.EMenu;

public class GMenubar extends JMenuBar {
	//attributes 내 내부 값
	private static final long serialVersionUID = 1L;

	//components 자식
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GColorMenu colorMenu;

	//associations 형재
	private GDrawingPanal drawingPanal;
	public void associate(GDrawingPanal drawingPanal) {
		this.drawingPanal = drawingPanal;
	}
		
		
	public GMenubar(){
		//initialize attributes 속성
		
		//initialize components 자식
		this.fileMenu = new GFileMenu(EMenu.fileMenu.getText());
		this.add(this.fileMenu);
		this.editMenu = new GEditMenu(EMenu.editMenu.getText());
		this.add(this.editMenu);
		this.colorMenu = new GColorMenu(EMenu.colorMenu.getText());
		this.add(this.colorMenu);
	}

	public void initialize() {
		//associate 친구연결
		this.fileMenu.associate(this.drawingPanal);
		this.editMenu.associate(this.drawingPanal);
		this.colorMenu.associate(this.drawingPanal);
		
		//initialize components 자식 초기화
		this.fileMenu.initialize();
		this.editMenu.initialize();
		this.colorMenu.initialize();
	}
}
