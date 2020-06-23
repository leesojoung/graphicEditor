package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import drawingPanal.GDrawingPanal;
import global.GConstants.EFileMenu;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private File directory, file;
	private GDrawingPanal drawingPanal;
	public void associate(GDrawingPanal drawingPanal) { this.drawingPanal = drawingPanal;}
	
	public GFileMenu(String text){
		super(text);
		this.file = null;
		this.directory = new File("data");
		
		ActionHandler actionHandler = new ActionHandler(); 
		
		for(EFileMenu eMenuItem : EFileMenu.values()) {
			JMenuItem menuItem = eMenuItem.getText();
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			if(eMenuItem.name() == "newItem") {
				 menuItem.setToolTipText("새로운 그림판을 만듭니다");
		    }else if(eMenuItem.name() == "openItem") {
		    	menuItem.setToolTipText("저장되어 있는 그림판을 엽니다");
		    }else if(eMenuItem.name() == "saveItem") {
		    	menuItem.setToolTipText("현재 그림판을 저장합니다");
		    }else if(eMenuItem.name() == "saveAsItem") {
		    	menuItem.setToolTipText("현재 그림판을 다른 이름으로 저장합니다");
		    }else if(eMenuItem.name() == "closeItem") {
		    	menuItem.setToolTipText("그림판을 닫습니다");
		    }else if(eMenuItem.name() == "printItem") {
		    	menuItem.setToolTipText("현재 그림판을 인쇄합니다");
		    }
			add(menuItem);  
		}
		hotkey();
		discription();
		shortkey();
	}

	private void shortkey() {
		EFileMenu.newItem.getText().setMnemonic(KeyEvent.VK_N);
		EFileMenu.openItem.getText().setMnemonic(KeyEvent.VK_O);
		EFileMenu.saveItem.getText().setMnemonic(KeyEvent.VK_S);
		EFileMenu.saveAsItem.getText().setMnemonic(KeyEvent.VK_A);
		EFileMenu.closeItem.getText().setMnemonic(KeyEvent.VK_C);
		EFileMenu.printItem.getText().setMnemonic(KeyEvent.VK_P);
	}

	private void discription() {
		EFileMenu.newItem.getText().getAccessibleContext().setAccessibleDescription(
		        "new file open");
	}

	private void hotkey() {
		EFileMenu.newItem.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		EFileMenu.openItem.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		EFileMenu.saveItem.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		EFileMenu.saveAsItem.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		EFileMenu.closeItem.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		EFileMenu.printItem.getText().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
	}

	public void initialize() {
		
	}
	
	public void nnew() {
		this.save();
			
		this.drawingPanal.restoreShapeVector(null);
		this.drawingPanal.setUpdated(true);
		
	}
	private void readObject() {
		try {
			ObjectInputStream objectInputStream ;
			objectInputStream= new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			Object object = objectInputStream.readObject();
			this.drawingPanal.restoreShapeVector(object);
			objectInputStream.close();
			this.drawingPanal.setUpdated(false);
			System.out.println("open");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	public void open() {
		this.save();
		
		JFileChooser chooser = new JFileChooser(this.directory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "god");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.drawingPanal);
		if(returnVal == JFileChooser.APPROVE_OPTION) {//ok일때,
			this.directory = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.readObject();
		}
	}
	
	
	private void writeObject() {
		try {
			ObjectOutputStream objectOutputStream; //한줄로 세워줌  data와 type정보 가지고 있음
			objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			objectOutputStream.writeObject(this.drawingPanal.getShapeVector());
			objectOutputStream.close();
			this.drawingPanal.setUpdated(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void save() {
		if(this.drawingPanal.isUpdated()) {//수정된게 있으면 save한다.
			if(file == null) {
				this.saveAs();
			} else {
				this.writeObject();
			}
		}
	}
	public void saveAs() {
		JFileChooser chooser = new JFileChooser(this.directory);//default directory
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "god");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this.drawingPanal);
		if(returnVal == JFileChooser.APPROVE_OPTION) {//ok일때,
			this.directory = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.writeObject();
		}
	}
	public void print() {
		//print 채우기
		
	}
	public void close() {
		this.save();
		System.exit(0);
	}
	public void invokeMethod(String name) {
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				 | SecurityException e) {
			e.printStackTrace();
		}
	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			invokeMethod(event.getActionCommand());
		}
	}
}
