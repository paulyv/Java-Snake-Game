package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame  {
	
	Gamepanel panel = new Gamepanel();
	
	public Window(String title) {
		JFrame window = new JFrame(title);
		window.setSize(new Dimension(500, 500));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.add(panel);
		panel.setFocusable(true);
		panel.requestFocus();

	}
	

}
