package game;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Apple extends Item {


	public Apple() {
		try {
			Image = ImageIO.read(new File("res/redapple.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Create a random number to the gamearegrid + offset
		rand = new Random();
		point = new Point((rand.nextInt(25) * Item.SIZE) + Gamepanel.gameAreaXoffset, (rand.nextInt(25) * Item.SIZE) + Gamepanel.gameAreaYoffset);
		x = point.x;
		y = point.y;

		isVisible = true;
	}

}
