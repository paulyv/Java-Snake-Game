package tests;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Version;

public class USBControllerTest {

	private static Controller ps3Controller;

	public static void main(String[] args) {
	//	System.out.println("JInput version: " + Version.getVersion());
		ControllerEnvironment ce = ControllerEnvironment
				.getDefaultEnvironment();
		Controller[] cs = ce.getControllers();
		
		for (int i = 0; i < cs.length; i++) {
	//		System.out.println(i + ". " + cs[i].getName() + ", "
	//				+ cs[i].getType());

			if (cs[i].getType() == Controller.Type.STICK) {
				ps3Controller = cs[i];
				System.out.println("This is ps3 stick controller");
			}

		}
		
		while(true) {
			ps3Controller.poll();
			for(Component c : ps3Controller.getComponents()){
				if(c.getName() == "5" && c.getPollData() == 1){
					System.out.println("RIGHT");
				}
				if(c.getName() == "7" && c.getPollData() == 1){
					System.out.println("LEFT");
				}
				if(c.getName() == "6" && c.getPollData() == 1){
					System.out.println("DOWN");
				}
				if(c.getName() == "4" && c.getPollData() == 1){
					System.out.println("UP");
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}