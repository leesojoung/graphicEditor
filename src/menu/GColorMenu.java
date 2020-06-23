package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import drawingPanal.GDrawingPanal;
import global.GConstants.EColorMenu;

public class GColorMenu extends JMenu  {
	private static final long serialVersionUID = 1L;

	private GDrawingPanal drawingPanal;
	public void associate(GDrawingPanal drawingPanal) { this.drawingPanal = drawingPanal;}
	

	public GColorMenu(String text){
		super(text);
		
		ActionHandler actionHandler = new ActionHandler();
		
		for(EColorMenu eMenuItem : EColorMenu.values()) {
			JMenuItem menuItem = eMenuItem.getText();
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			if(eMenuItem.name() == "line") {
				 menuItem.setToolTipText("선 색을 설정합니다");
		    }else if(eMenuItem.name() == "fill") {
		    	menuItem.setToolTipText("도형에 색을 채웁니다");
		    }
			add(menuItem);
		}
		hotkey();
		shortcutKey();
	}

	public void line() {
		Color color = JColorChooser.showDialog(this.drawingPanal, "Line Color", this.drawingPanal.getForeground());
		this.drawingPanal.setLineColor(color);
	}

	public void fill() {
		Color color = JColorChooser.showDialog(this.drawingPanal, "Fill Color", this.drawingPanal.getForeground());
		this.drawingPanal.setFillColor(color);
	}
	
	private void hotkey() {
		EColorMenu.eLine.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		EColorMenu.eLine.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
	}
	
	public void shortcutKey() {
		EColorMenu.eLine.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		EColorMenu.eFill.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			invokeMethod(event.getActionCommand());
		}
	}
	public void invokeMethod(String name) {
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				 | SecurityException e) {
			e.printStackTrace();
		}		
	}
	public void initialize() {
	}
}
