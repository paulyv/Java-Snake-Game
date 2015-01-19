package game;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {

	// Variables
	public static final int SQUARE_SIZE = 20;
	public static int SPEED = 20;
	private int snakeXdirection;
	private int snakeYdirection;
	private ArrayList<Point> snakeArray = new ArrayList<Point>();

	// Constructor
	public Snake() {
		// Initialize the snake
		snakeArray.add(new Point(100, 100));
		snakeArray.add(new Point(100, 80));
		snakeArray.add(new Point(100, 60));
		//initial snake direction
		setSnakeXdirection(0);
		setSnakeYdirection(1);
	}
	
	// Accessor methods


	public ArrayList<Point> getSnakeArray() {
		return snakeArray;
	}

	public void setSnakeArray(ArrayList<Point> snakeArray) {
		this.snakeArray = snakeArray;
	}

	public int getSnakeXdirection() {
		return snakeXdirection;
	}

	public void setSnakeXdirection(int snakeXdirection) {
		this.snakeXdirection = snakeXdirection;
	}

	public int getSnakeYdirection() {
		return snakeYdirection;
	}

	public void setSnakeYdirection(int snakeYdirection) {
		this.snakeYdirection = snakeYdirection;
	}

}
