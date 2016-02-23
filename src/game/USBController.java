package game;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class USBController implements Runnable {

	private static Controller ps3Controller;
	private boolean isController = false;

	public USBController() {
		
		// Get the controller environment
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		
		// Get all controllers from the environment
		Controller[] cs = ce.getControllers();
		
		// Find and assign ps3 controller
		for (int i = 0; i < cs.length; i++) {
			// Assign the stick controller to ps3 variable
			if (cs[i].getType() == Controller.Type.STICK) {
				ps3Controller = cs[i];
				System.out.println("This is ps3 stick controller");
				isController = true;
			}

		}

	}

	@Override
	public void run() {
		while (isController) {
			// Poll the controller for changes in buttons
			ps3Controller.poll();
			// Loop through the components and check for changes in desired buttons
			for (Component c : ps3Controller.getComponents()) {
				if (c.getName() == "5" && c.getPollData() == 1) {
					System.out.println("RIGHT");
					if (Snake.getSnakeXdirection() == 0) {
						Snake.setSnakeXdirection(1);
						Snake.setSnakeYdirection(0);
					}
				}
				if (c.getName() == "7" && c.getPollData() == 1) {
					System.out.println("LEFT");
					if (Snake.getSnakeXdirection() == 0) {
						Snake.setSnakeXdirection(-1);
						Snake.setSnakeYdirection(0);
					}
				}
				if (c.getName() == "6" && c.getPollData() == 1) {
					System.out.println("DOWN");
					if (Snake.getSnakeYdirection() == 0) {
						Snake.setSnakeYdirection(1);
						Snake.setSnakeXdirection(0);
					}
				}
				if (c.getName() == "4" && c.getPollData() == 1) {
					System.out.println("UP");
					if (Snake.getSnakeYdirection() == 0) {
						Snake.setSnakeYdirection(-1);
						Snake.setSnakeXdirection(0);
					}
				}

			}

		}

	}
}