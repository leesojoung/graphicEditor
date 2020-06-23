package main;

import javax.swing.UIManager;

public class GMain {
	static GMainFrame mainFrame;
	public static void main(String[] args) {
		
		
	      try {
	          UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
	       } catch (Exception e) {
	          e.printStackTrace();
	       }
		
	 mainFrame.setDefaultLookAndFeelDecorated(true);		
//		
	 mainFrame = new GMainFrame();
	 mainFrame.initialize();
	 mainFrame.setVisible(true);
		
	}

}
