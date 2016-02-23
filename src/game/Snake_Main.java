package game;



import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Snake_Main extends JFrame {

	public static void main(String[] args) {
				
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window("Snake");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
