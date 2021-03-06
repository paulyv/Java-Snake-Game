package game;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {

	public static final int SQUARE_SIZE = 20;
	public static int SPEED = 20;
	private static int snakeXdirection;
	private static int snakeYdirection;
	private ArrayList<Point> snakeArray = new ArrayList<Point>();


	public Snake() {
		
		// Initialize the snake
		snakeArray.add(new Point(0 + Gamepanel.gameAreaXoffset, 0 + Gamepanel.gameAreaYoffset));
		snakeArray.add(new Point(SQUARE_SIZE + Gamepanel.gameAreaXoffset, SQUARE_SIZE + Gamepanel.gameAreaYoffset));
		snakeArray.add(new Point(SQUARE_SIZE * 2 + Gamepanel.gameAreaXoffset, SQUARE_SIZE * 2 + Gamepanel.gameAreaYoffset));

		//initial snake direction
		setSnakeXdirection(0);
		setSnakeYdirection(1);
	}
	
	public ArrayList<Point> getSnakeArray() {
		return snakeArray;
	}

	public void setSnakeArray(ArrayList<Point> snakeArray) {
		this.snakeArray = snakeArray;
	}

	public static int getSnakeXdirection() {
		return snakeXdirection;
	}

	public static void setSnakeXdirection(int Xdirection) {
		snakeXdirection = Xdirection;
	}

	public static int getSnakeYdirection() {
		return snakeYdirection;
	}

	public static void setSnakeYdirection(int Ydirection) {
		snakeYdirection = Ydirection;
	}

}
