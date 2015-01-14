package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame  {

	private static final long serialVersionUID = 6381341702405403734L;

	Gamepanel panel = new Gamepanel();
	
	public Window(String title) {
		JFrame window = new JFrame(title);
		window.setSize(new Dimension(500, 500));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		window.add(panel);
		panel.setFocusable(true);
		panel.requestFocus();

	}
	

}
