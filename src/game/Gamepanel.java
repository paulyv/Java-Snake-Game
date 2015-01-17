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
	private boolean gamePaused = false;
	private boolean collision;
	private Timer t;
	private Apple apple;
	private Highscore highscore;
	private BufferedImage bg_image;
	private Snake snake;
	private MusicManager musicMan;

	// Constructor. Add timer and keylistener to panel. Initialize the snake and
	// the apple.

	public Gamepanel() {
		super(true); // set double buffer for JPanel
		addKeyListener(this);
		t = new Timer(80, this); // Swingtimer 100millis
		

		// Initialize apple and highscore object
		musicMan = new MusicManager();
		snake = new Snake();
		apple = new Apple();
		highscore = new Highscore();
		

		// Load background image
		try {
			bg_image = ImageIO.read(this.getClass().getResource("/res/bg.png"));
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

		// Clear the screen (draw bg_image over everything)
		graphics2D.drawImage(bg_image, 0, 0, null);

		// Loop through the snake and draw the pieces
		for (int i = snake.getSnakeArray().size() - 1; i > 0; i--) {
			graphics2D.setColor(Color.GREEN);
			graphics2D.fillRoundRect(snake.getSnakeArray().get(i).x, snake
					.getSnakeArray().get(i).y, Snake.SQUARE_SIZE,
					Snake.SQUARE_SIZE, 10, 10);

			// Create spacing between snake pieces
			graphics2D.setColor(new Color(1, 117, 1));
			graphics2D.drawRoundRect(snake.getSnakeArray().get(i).x, snake
					.getSnakeArray().get(i).y, Snake.SQUARE_SIZE,
					Snake.SQUARE_SIZE, 10, 10);

			snake.getSnakeArray().get(i).x = snake.getSnakeArray().get(i - 1).x;
			snake.getSnakeArray().get(i).y = snake.getSnakeArray().get(i - 1).y;
		}

		// Detect collision with the snake itself. Turns the snake piece yellow
		// which you've it.
		for (int i = snake.getSnakeArray().size() - 1; i > 2; i--) {

			if (snake.getSnakeArray().get(0).x == snake.getSnakeArray().get(i).x
					&& snake.getSnakeArray().get(0).y == snake.getSnakeArray()
							.get(i).y) {
				t.stop();
				musicMan.stopPlaying();

				// Turn the piece yellow
				graphics2D.setColor(Color.YELLOW);
				graphics2D.fillRect(snake.getSnakeArray().get(i).x, snake
						.getSnakeArray().get(i).y, Snake.SQUARE_SIZE,
						Snake.SQUARE_SIZE);

				// Create spacing to the yellow piece.
				graphics2D.setColor(Color.darkGray);
				graphics2D.drawRect(snake.getSnakeArray().get(i).x, snake
						.getSnakeArray().get(i).y, Snake.SQUARE_SIZE,
						Snake.SQUARE_SIZE);

				// Game over text
				graphics2D.setColor(Color.RED);
				graphics2D.setFont(new Font("Arial Rounded MT Bold", Font.BOLD,
						23));
				graphics2D.drawString("Game Over!", 200, 250);
				highscore.setScore(score);
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
			snake.getSnakeArray().add(
					new Point((snake.getSnakeArray().get(0).x), snake
							.getSnakeArray().get(0).y));
			score += 10;
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
		graphics2D.drawString("High score: " + highscore.getScore(), 390, 20);

		// Draw Score
		graphics2D.setColor(Color.GREEN);
		graphics2D.drawString("Score: " + score, 390, 35);

		// Detect collision with the wall x-wise
		if (snake.getSnakeArray().get(0).x > this.getWidth()
				|| snake.getSnakeArray().get(0).x < 0) {
			t.stop();
			graphics2D.setColor(Color.RED);
			graphics2D
					.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 23));
			graphics2D.drawString("Game Over!", 180, 250);
			musicMan.stopPlaying();
			highscore.setScore(score);
		}
		// Detect collision with the wall y-wise
		if (snake.getSnakeArray().get(0).y > this.getHeight()
				|| snake.getSnakeArray().get(0).y < 0) {
			t.stop();
			graphics2D.setColor(Color.RED);
			graphics2D
					.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 23));
			graphics2D.drawString("Game Over!", 180, 250);
			musicMan.stopPlaying();
			highscore.setScore(score);
		}

		graphics2D.finalize();

	}

	@Override
	// Game loop
	public void actionPerformed(ActionEvent e) {

		// Move only the head (0-piece) and the rest will follow
		snake.getSnakeArray().get(0).x += snake.getSquareXSpeed();
		snake.getSnakeArray().get(0).y += snake.getSquareYSpeed();

		repaint(); // call render (paint component)
	}

	@Override
	// Move snake according to key presses
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (snake.getSquareYSpeed() == 0) {
				snake.setSquareXSpeed(0);
				snake.setSquareYSpeed(snake.getSpeed());
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (snake.getSquareXSpeed() == 0) {
				snake.setSquareYSpeed(0);
				snake.setSquareXSpeed(snake.getSpeed());

			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (snake.getSquareXSpeed() == 0) {
				snake.setSquareYSpeed(0);
				snake.setSquareXSpeed(-(snake.getSpeed()));
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (snake.getSquareYSpeed() == 0) {
				snake.setSquareXSpeed(0);
				snake.setSquareYSpeed(-(snake.getSpeed()));
			}
		}
		// Pause game
		if (e.getKeyCode() == KeyEvent.VK_P) {
			if (!gamePaused) {
				t.stop();
				musicMan.pausePlaying();
				gamePaused = true;
			} else {
				t.start();
				musicMan.resumePlaying();
				gamePaused = false;
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
