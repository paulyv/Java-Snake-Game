package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame  {

	private static final long serialVersionUID = 6381341702405403734L;

	Gamepanel panel = new Gamepanel();
	
	// Create a new JFrame and add the panel on it.
	public Window(String title) {
		JFrame window = new JFrame(title);
		window.setSize(new Dimension(600, 600));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(true);
		window.setVisible(true);
		window.add(panel);
		panel.setFocusable(true);
		panel.requestFocus();

	}
	

}
