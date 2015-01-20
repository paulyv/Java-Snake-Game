package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gamepanel extends JPanel implements ActionListener, KeyListener {

	// Variables
	private static final long serialVersionUID = 5385848097769207171L;

	private int score;
	private int collision_x;
	private int collision_y;
	private int haloSize = 0;
	private boolean gameRunning = false;
	private boolean collision;
	private Timer t;
	private Apple apple;
	private Highscore highscore;
	private BufferedImage bg_image;
	private Snake snake;
	private MusicManager musicMan;
	private final int gameAreaXoffset = 50;
	private final int gameAreaYoffset = 50;

	// Constructor. Add timer and keylistener to panel. Initialize the snake and
	// the apple.

	public Gamepanel() {
		super(true); // set double buffer for JPanel
		addKeyListener(this);
		t = new Timer(80, this); // Swingtimer 100millis
		

		// Initialize objects and the controller thread
		musicMan = new MusicManager();
		snake = new Snake();
		apple = new Apple();
		highscore = new Highscore();
		(new Thread(new USBController())).start();
		

		// Load background image
		try {
			//this.getClass().getClassLoader().getResourceAsStream
			bg_image = ImageIO.read(new File("res/bg.png"));

		} catch (IOException ex) {
		}
		
		t.start();
		musicMan.loopPlaying();
		
	}

	// Render the JPanel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D graphics2D = (Graphics2D) g;

		// Set anti-alias
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Set anti-alias for text
		// graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		
		graphics2D.setColor(new Color(82, 50, 0));
		graphics2D.fillRect(0, 0, 700, 700);
		// Draw the actual gaming area over the grass
		graphics2D.drawImage(bg_image, gameAreaXoffset, gameAreaYoffset, null);
		
		graphics2D.setFont(new Font("Tahoma", Font.BOLD, 30));
		graphics2D.setColor(Color.GREEN);
		graphics2D.drawString("SNAKE GAME",200, 40);

		// Loop through the snake and draw the pieces
		for (int i = snake.getSnakeArray().size() - 1; i >= 0; i--) {
			graphics2D.setColor(Color.GREEN);
			graphics2D.fillRoundRect(snake.getSnakeArray().get(i).x, snake.getSnakeArray().get(i).y, Snake.SQUARE_SIZE, Snake.SQUARE_SIZE, 10, 10);

			// Create spacing between snake pieces
			graphics2D.setColor(new Color(1, 117, 1));
			graphics2D.drawRoundRect(snake.getSnakeArray().get(i).x, snake.getSnakeArray().get(i).y, Snake.SQUARE_SIZE, Snake.SQUARE_SIZE, 10, 10);

			if(i != 0){
			snake.getSnakeArray().get(i).x = snake.getSnakeArray().get(i - 1).x;
			snake.getSnakeArray().get(i).y = snake.getSnakeArray().get(i - 1).y;
			}
		}

		// Detect collision with the snake itself. Turns the snake piece yellow
		// which you've it.
		for (int i = snake.getSnakeArray().size() - 1; i > 2; i--) {

			if (snake.getSnakeArray().get(0).x == snake.getSnakeArray().get(i).x && snake.getSnakeArray().get(0).y == snake.getSnakeArray().get(i).y) {
				gameRunning = false;
				musicMan.stopPlaying();

				// Turn the piece yellow
				graphics2D.setColor(Color.YELLOW);
				graphics2D.fillRoundRect(snake.getSnakeArray().get(i).x, snake
						.getSnakeArray().get(i).y, Snake.SQUARE_SIZE,
						Snake.SQUARE_SIZE, 10, 10);

				// Create spacing to the yellow piece.
				graphics2D.setColor(new Color(1, 117, 1));
				graphics2D.drawRoundRect(snake.getSnakeArray().get(i).x, snake
						.getSnakeArray().get(i).y, Snake.SQUARE_SIZE,
						Snake.SQUARE_SIZE, 10, 10);

				// Game over text
				graphics2D.setColor(Color.RED);
				graphics2D.setFont(new Font("Arial Rounded MT Bold", Font.BOLD,
						23));
				graphics2D.drawString("Game Over!", 200, 300);
				highscore.setScore(score);
				t.stop();
			}
		}

		// Detect collision with an apple and grow the snake by 1 piece. Add 10
		// to score. Store collision coords for
		if (snake.getSnakeArray().get(0).x <= apple.getX() + 15
				&& snake.getSnakeArray().get(0).x >= apple.getX() - 15
				&& snake.getSnakeArray().get(0).y <= apple.getY() + 15
				&& snake.getSnakeArray().get(0).y >= apple.getY() - 15) {
			collision = true;
			collision_x = apple.getX();
			collision_y = apple.getY();
			apple.setApple(false);
			// add a new point to place of the last piece
			snake.getSnakeArray().add(new Point((snake.getSnakeArray().get(snake.getSnakeArray().size() - 1).x), snake.getSnakeArray().get(snake.getSnakeArray().size() - 1).y));
			score += 10;
			musicMan.playPickup();
		}

		// Draw a yellow halo animation around the place you got an apple
		if (haloSize < 50 && collision == true) {
			graphics2D.setColor(Color.YELLOW);
			graphics2D.drawOval(collision_x - (haloSize / 2), collision_y
					- (haloSize / 2), haloSize, haloSize);
			haloSize += 8;
		} else {
			haloSize = 0;
			collision = false;
		}

		// Create a new apple
		if (apple.isApple() == false) {
			apple.newPoint();
			apple.setApple(true);
		}

		// Draw the apple
		graphics2D.setColor(Color.RED);
		graphics2D.drawImage(Apple.APPLE_IMAGE, apple.getX(), apple.getY(),
				null);

		// Draw Highscore
		graphics2D.setColor(Color.GREEN);
		graphics2D.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		graphics2D.drawString("High score: " + highscore.getScore(), 400, 70);

		// Draw Score
		graphics2D.setColor(Color.GREEN);
		graphics2D.drawString("Score: " + score, 400, 85);

		// Detect collision with the wall x-wise
		if (snake.getSnakeArray().get(0).x >= 500 + gameAreaXoffset
				|| snake.getSnakeArray().get(0).x < 0 + gameAreaXoffset - Snake.SQUARE_SIZE) {
			gameRunning = false;
			graphics2D.setColor(Color.RED);
			graphics2D
					.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 23));
			graphics2D.drawString("Game Over!", 200, 300);
			musicMan.stopPlaying();
			highscore.setScore(score);
			t.stop();
		}
		// Detect collision with the wall y-wise
		if (snake.getSnakeArray().get(0).y >= 500 + gameAreaYoffset + Snake.SQUARE_SIZE
				|| snake.getSnakeArray().get(0).y < 0 + gameAreaYoffset - Snake.SQUARE_SIZE) {
			gameRunning = false;
			graphics2D.setColor(Color.RED);
			graphics2D
					.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 23));
			graphics2D.drawString("Game Over!", 200, 300);
			musicMan.stopPlaying();
			highscore.setScore(score);
			t.stop();
		}

		

	}

	@Override
	// Game loop
	public void actionPerformed(ActionEvent e) {

		
		// Move only the head (0-piece) and the rest will follow
		snake.getSnakeArray().get(0).x += Snake.SPEED * Snake.getSnakeXdirection();
		snake.getSnakeArray().get(0).y += Snake.SPEED * Snake.getSnakeYdirection();
		repaint(); // call render (paint component)
		
	}

	@Override
	// Move snake according to key presses
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (Snake.getSnakeYdirection() == 0) {
				Snake.setSnakeXdirection(0);
				Snake.setSnakeYdirection(1);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (Snake.getSnakeXdirection() == 0) {
				Snake.setSnakeXdirection(1);
				Snake.setSnakeYdirection(0);

			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (Snake.getSnakeXdirection() == 0) {
				Snake.setSnakeXdirection(-1);
				Snake.setSnakeYdirection(0);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (Snake.getSnakeYdirection() == 0) {
				Snake.setSnakeXdirection(0);
				Snake.setSnakeYdirection(-1);
			}
		}
		// Pause game
		if (e.getKeyCode() == KeyEvent.VK_P) {
			if (!gameRunning) {
				t.stop();
				musicMan.pausePlaying();
				gameRunning = true;
			} else {
				t.start();
				musicMan.resumePlaying();
				gameRunning = false;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
