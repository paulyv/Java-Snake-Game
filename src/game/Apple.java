package game;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class Apple {

	// Variables
	public final static int SIZE = 20;
	public static BufferedImage APPLE_IMAGE;
	private boolean isApple;
	private Point point;
	private Random rand;
	private int x;
	private int y;

	// Constructor. Creates a new initial apple by reading apple image.png,
	// creates new random point and sets apple to true
	public Apple() {
		try {
			URL image_url = this.getClass().getResource("/res/redapple.png");
			APPLE_IMAGE = ImageIO.read(image_url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		rand = new Random();
		point = new Point(rand.nextInt(450), rand.nextInt(450));
		x = point.x;
		y = point.y;

		isApple = true;
	}

	// Returns apples x coordinate
	public int getX() {
		return x;
	}

	// Returns apples y coordinate
	public int getY() {
		return y;
	}

	// Returns apples current status
	public boolean isApple() {
		return isApple;
	}

	// Set apple true or false wether it is eaten or not
	public void setApple(boolean isApple) {
		this.isApple = isApple;
	}

	// Returns apples current point
	public Point getP() {
		return point;
	}

	// Create a new random point for the apple
	public void newPoint() {
		point = new Point(rand.nextInt(450), rand.nextInt(450));
		x = point.x;
		y = point.x;
	}
}
