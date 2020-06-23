package menu;
import javax.swing.JMenuBar;

import drawingPanal.GDrawingPanal;
import global.GConstants.EMenu;

public class GMenubar extends JMenuBar {
	//attributes �� ���� ��
	private static final long serialVersionUID = 1L;

	//components �ڽ�
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GColorMenu colorMenu;

	//associations ����
	private GDrawingPanal drawingPanal;
	public void associate(GDrawingPanal drawingPanal) {
		this.drawingPanal = drawingPanal;
	}
		
		
	public GMenubar(){
		//initialize attributes �Ӽ�
		
		//initialize components �ڽ�
		this.fileMenu = new GFileMenu(EMenu.fileMenu.getText());
		this.add(this.fileMenu);
		this.editMenu = new GEditMenu(EMenu.editMenu.getText());
		this.add(this.editMenu);
		this.colorMenu = new GColorMenu(EMenu.colorMenu.getText());
		this.add(this.colorMenu);
	}

	public void initialize() {
		//associate ģ������
		this.fileMenu.associate(this.drawingPanal);
		this.editMenu.associate(this.drawingPanal);
		this.colorMenu.associate(this.drawingPanal);
		
		//initialize components �ڽ� �ʱ�ȭ
		this.fileMenu.initialize();
		this.editMenu.initialize();
		this.colorMenu.initialize();
	}
}
