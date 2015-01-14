package game;

import java.awt.Point;
import java.util.Random;

public class Apple {

	public final static int SIZE = 15;
	public boolean isApple;
	private Point p;
	private Random rand;
	private int x;
	private int y;
	
	public Apple() {
		rand = new Random();
		p = new Point(rand.nextInt(450), rand.nextInt(450));
		x = p.x;
		y = p.y;
		
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
		return p;
	}
	
	public void newPoint() {
		p = new Point(rand.nextInt(450), rand.nextInt(450));
		x = p.x;
		y = p.x;
	}
}
