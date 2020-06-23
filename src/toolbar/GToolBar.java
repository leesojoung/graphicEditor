package toolbar;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import drawingPanal.GDrawingPanal;
import global.GConstants.EToolBar;

public class GToolBar extends JToolBar {
	//attribute
	private static final long serialVersionUID = 1L;

	//components
	private Vector<JRadioButton> buttons;
	
	//associations
	private GDrawingPanal drawingPanal;
	public void associate(GDrawingPanal drawingPanal) {
		this.drawingPanal = drawingPanal;
	}
	
	public GToolBar(){
		ActionHandler actionHandler = new ActionHandler();
		ButtonGroup buttonGroup= new ButtonGroup();
		
		this.buttons = new Vector<JRadioButton>();
		
		for(EToolBar eToolBar: EToolBar.values()){
			ImageIcon basicIcon = new ImageIcon(eToolBar.basicIcon()); 
			Image changeBasic = basicIcon.getImage();  //ImageIcon을 Image로 변환.
			Image changedBasic = changeBasic.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
			ImageIcon basicIcon2 = new ImageIcon(changedBasic); //Image로 ImageIcon 생성
			
			ImageIcon selectedIcon = new ImageIcon(eToolBar.seletedIcon()); 
			Image changeSelect = selectedIcon.getImage();  //ImageIcon을 Image로 변환.
			Image changedSelet = changeSelect.getScaledInstance(40, 40,Image.SCALE_SMOOTH);
			ImageIcon selectedIcon2 = new ImageIcon(changedSelet); //Image로 ImageIcon 생성
			
			JRadioButton button =new JRadioButton();
			button.setIcon(basicIcon2);
			button.setSelectedIcon(selectedIcon2);
			button.setActionCommand(eToolBar.name());
		    button.addActionListener(actionHandler);
		    if(eToolBar.name() == "select") {
		    	 button.setToolTipText("선택");
		    }else if(eToolBar.name() == "rectangle") {
		    	 button.setToolTipText("사각형");
		    }else if(eToolBar.name() == "polygon") {
		    	 button.setToolTipText("다각형");
		    }
		    this.buttons.add(button);
			this.add(button);
			buttonGroup.add(button);
		}
	}
	public void initialize() {
		this.buttons.get(EToolBar.rectangle.ordinal()).doClick();//순서대로 r1=0, r2=1, p=3이다.
		//doclick은 마우스로 클릭하는것과 똑같은 일을 한다.
	}
	
	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			drawingPanal.setCurrentTool(EToolBar.valueOf(event.getActionCommand()));
		}
	}
}
