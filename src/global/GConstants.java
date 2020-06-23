package global;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import Shape.GGroup;
import Shape.GPolygon;
import Shape.GRectangle;
import Shape.GShape;

public class GConstants {

	public enum EMainFrame{
		x(550),
		y(150),
		w(800),
		h(800)
		;
		private int value;
		EMainFrame(int value){
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	public enum EToolBar{
		//��ü�� �ȿ��� ���� .. ������ ����
		select("icon/select.png", "icon/select2.png", new GGroup()),
		rectangle("icon/rectangle.png", "icon/rectangle2.png", new GRectangle()),
		polygon("icon/polygon.png", "icon/polygon2.png", new GPolygon()),
//		ellipse("icon/c.png", new GEllipse()),
//		line("icon/l.png", new GLine()),
		;
		private String basicIcon;
		private String seletedIcon;
		private GShape shape;
		private EToolBar(String basicIcon, String seletedIcon, GShape shape){
			this.basicIcon = basicIcon;
			this.seletedIcon = seletedIcon;
			this.shape = shape;
		}
		
		public String basicIcon(){
			return this.basicIcon;
		}
		public String seletedIcon(){
			return this.seletedIcon;
		}
		public GShape getShape(){
			return this.shape;
		}
	}	
	
	
	public enum EMenu {
		fileMenu("File"),
		editMenu("Edit"),
		colorMenu("Color"),
		;
	
		private String text;
		private	EMenu(String text){
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}	
	public enum EFileMenu{
		newItem(new JMenuItem("���� �����",  new ImageIcon("icon/new.png")), "nnew"),
		openItem(new JMenuItem("����",  new ImageIcon("icon/open2.png")), "open"),
		saveItem(new JMenuItem("����",  new ImageIcon("icon/save.png")), "save"),
		saveAsItem(new JMenuItem("�ٸ��̸����� ����",  new ImageIcon("icon/saveAs.png")), "saveAs"),
		printItem(new JMenuItem("�μ��ϱ�",  new ImageIcon("icon/print2.png")), "print"),
		closeItem(new JMenuItem("�ݱ�",  new ImageIcon("icon/close.png")), "close"),
		;
		private JMenuItem text;
		private String method;
		private EFileMenu(JMenuItem text, String method){
			this.text = text;
			this.method = method;
		}
		public JMenuItem getText(){
			return this.text;
		}
		public String getMethod(){
			return this.method;
		}
	}
	public enum EEditMenu{
		undo(new JMenuItem("�ǵ�����", new ImageIcon("icon/undo.png")), "undo"),
		redo(new JMenuItem("�ٽý���", new ImageIcon("icon/redo.png")), "redo"),
		cut(new JMenuItem("�߶󳻱�", new ImageIcon("icon/cut.png")), "cut"),
		copy(new JMenuItem("���系��", new ImageIcon("icon/copy.png")), "copy"),
		paste(new JMenuItem("�ٿ��ֱ�", new ImageIcon("icon/paste.png")), "paste"),
		group(new JMenuItem("�׷�", new ImageIcon("icon/group.png")), "group"),
		ungroup(new JMenuItem("�׷�����", new ImageIcon("icon/ungroup.png")), "ungroup"),
		;
		private JMenuItem text;
		private String method;
		private EEditMenu(JMenuItem text, String method){
			this.text = text;
			this.method = method;
		}
		public JMenuItem getText(){
			return this.text;
		}
		public String getMethod(){
			return this.method;
		}
	}
	public enum EColorMenu {
		eLine(new JMenuItem("line"), "line"),
		eFill(new JMenuItem("fill"), "fill")
		;
		
		private JMenuItem text;
		private String method;
		private EColorMenu(JMenuItem text, String method){
			this.text = text;
			this.method = method;
		}
		public JMenuItem getText(){
			return this.text;
		}
		public String getMethod(){
			return this.method;
		}
	}
}
