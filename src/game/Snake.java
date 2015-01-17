package game;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {

	// Variables
	public static final int SQUARE_SIZE = 20;
	private int squareXSpeed = 0;
	private int squareYSpeed = 20;
	private int speed = 20;
	private ArrayList<Point> snakeArray = new ArrayList<Point>();

	// Constructor
	public Snake() {
		// Initialize the snake
		snakeArray.add(new Point(100, 100));
		snakeArray.add(new Point(100, 80));
		snakeArray.add(new Point(100, 60));
	}
	
	// Accessor methods
	public int getSquareXSpeed() {
		return squareXSpeed;
	}

	public void setSquareXSpeed(int squareXSpeed) {
		this.squareXSpeed = squareXSpeed;
	}

	public int getSquareYSpeed() {
		return squareYSpeed;
	}

	public void setSquareYSpeed(int squareYSpeed) {
		this.squareYSpeed = squareYSpeed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Point> getSnakeArray() {
		return snakeArray;
	}

	public void setSnakeArray(ArrayList<Point> snakeArray) {
		this.snakeArray = snakeArray;
	}

}
