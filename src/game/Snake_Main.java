package game;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Snake_Main extends JFrame {

	private static final long serialVersionUID = -1178974578870720723L;

	public static void main(String[] args) {
				
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Window frame = new Window("Matopeli");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
