package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import drawingPanal.GDrawingPanal;
import global.GConstants.EEditMenu;


public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private GDrawingPanal drawingPanel;
	
	public GEditMenu(String text){
		super(text);
		
		ActionHandler actionHandler = new ActionHandler();
		
		for(EEditMenu eMenuItem : EEditMenu.values()) {
			JMenuItem menuItem = eMenuItem.getText();
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			if(eMenuItem.name() == "undo") {
				 menuItem.setToolTipText("이전 상태로 돌아갑니다");
		    }else if(eMenuItem.name() == "redo") {
		    	menuItem.setToolTipText("최근 상태로 돌아갑니다");
		    }else if(eMenuItem.name() == "cut") {
		    	menuItem.setToolTipText("도형을 잘라냅니다");
		    }else if(eMenuItem.name() == "copy") {
		    	menuItem.setToolTipText("도형을 복사합니다");
		    }else if(eMenuItem.name() == "paste") {
		    	menuItem.setToolTipText("잘라낸 도형을 붙입니다");
		    }else if(eMenuItem.name() == "group") {
		    	menuItem.setToolTipText("그룹화합니다");
		    }else if(eMenuItem.name() == "ungroup") {
		    	menuItem.setToolTipText("그룹화를 해제합니다");
		    }
			add(menuItem);
		}
		hotkey();
		discription();
		shortkey();
	}
		private void shortkey() {
			EEditMenu.undo.getText().setMnemonic(KeyEvent.VK_Z);
			EEditMenu.redo.getText().setMnemonic(KeyEvent.VK_Y);
			EEditMenu.cut.getText().setMnemonic(KeyEvent.VK_X);
			EEditMenu.copy.getText().setMnemonic(KeyEvent.VK_C);
			EEditMenu.paste.getText().setMnemonic(KeyEvent.VK_V);
			EEditMenu.group.getText().setMnemonic(KeyEvent.VK_G);
			EEditMenu.ungroup.getText().setMnemonic(KeyEvent.VK_U);
	}
		private void discription() {
			EEditMenu.undo.getText().getAccessibleContext().setAccessibleDescription(
			        "This doesn't really do anything");
	}
		private void hotkey() {
			EEditMenu.undo.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
			EEditMenu.redo.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
			EEditMenu.cut.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
			EEditMenu.copy.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
			EEditMenu.paste.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
			EEditMenu.group.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
			EEditMenu.ungroup.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
	}
		public void associate(GDrawingPanal drawingPanel) {
			this.drawingPanel = drawingPanel;
		}
		public void initialize() {
			
		}
		
		public void invokeMethod(String name) {
			try {
				this.getClass().getMethod(name).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					 | SecurityException e) {
				e.printStackTrace();
			}
		}
		
		public void undo() {
			
		}
		public void redo() {
			
		}
		public void cut() {
			this.drawingPanel.cut();
		}
		public void copy() {
			this.drawingPanel.copy();
		}
		public void paste() {
			this.drawingPanel.paste();
		}
		public void group() {
			
		}
		public void ungroup() {
			
		}
	
		
		private class ActionHandler implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				invokeMethod(event.getActionCommand());
			}
		}
}
