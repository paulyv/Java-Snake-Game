package game;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Item {

	protected static final int SIZE = 20;
	protected BufferedImage Image;
	protected boolean isVisible = false;
	protected Random rand;
	protected Point point;
	protected int x;
	protected int y;

	// Create a new random point for the item
	public void newPoint() {
		point = new Point(rand.nextInt(480) + 50, rand.nextInt(480) + 50);
		x = point.x;
		y = point.x;
	}

	public BufferedImage getImage() {
		return Image;
	}

	public void setImage(BufferedImage image) {
		Image = image;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static int getSize() {
		return SIZE;
	}

}
