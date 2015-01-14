package game;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class Apple {

	public final static int SIZE = 20;
	public static BufferedImage APPLE_IMAGE;
	private boolean isApple;
	private Point point;
	private Random rand;
	private int x;
	private int y;
		
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

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public boolean isApple() {
		return isApple;
	}

	public void setApple(boolean isApple) {
		this.isApple = isApple;
	}

	public Point getP() {
		return point;
	}
	
	public void newPoint() {
		point = new Point(rand.nextInt(450), rand.nextInt(450));
		x = point.x;
		y = point.x;
	}
}
