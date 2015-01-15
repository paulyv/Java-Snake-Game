package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gamepanel extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 5385848097769207171L;
	private final int SQUARE_SIZE = 20;
	private int squareXSpeed = 0;
	private int squareYSpeed = 20;
	private int speed = 20;
	private Timer t;
	private int score;
	private Apple apple;
	
	ArrayList<Point> snakeArray = new ArrayList<Point>();

	public Gamepanel() {
		super(true); // set double buffer for jpanel
		addKeyListener(this);
		t = new Timer(100, this); // Swingtimer 100millis
		t.start();

		// alussa 4 palaa
		snakeArray.add(new Point(100, 100));
		snakeArray.add(new Point(100, 80));
		snakeArray.add(new Point(100, 60));
		snakeArray.add(new Point(100, 40));

		apple = new Apple();
	}

	// Render
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics2D = (Graphics2D) g;
		
		//Set  anti-alias
	    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON); 

	   // Set anti-alias for text
	    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
	    graphics2D.setColor(Color.darkGray);
	    graphics2D.fillRect(0, 0, 500, 500);

		// Loopataan mato ja piirretään palaset
		for (int i = snakeArray.size() - 1; i > 0; i--) {
			graphics2D.setColor(Color.GREEN);
			graphics2D.fillRect(snakeArray.get(i).x, snakeArray.get(i).y, SQUARE_SIZE, SQUARE_SIZE);
			
			// Pieni 1px väli madon osien väliin
			graphics2D.setColor(Color.darkGray);
			graphics2D.drawRect(snakeArray.get(i).x, snakeArray.get(i).y, SQUARE_SIZE, SQUARE_SIZE);
			
			snakeArray.get(i).x = snakeArray.get(i - 1).x;
			snakeArray.get(i).y = snakeArray.get(i - 1).y;
		}

		// Törmäys itsensä kanssa

		for (int i = snakeArray.size() - 1; i > 1; i--) {
			if (snakeArray.get(0).x == snakeArray.get(i).x
					&& snakeArray.get(0).y == snakeArray.get(i).y) {
				t.stop();
				graphics2D.setColor(Color.RED);
				graphics2D.drawString("Game Over!", 200, 250);
			}
		}

			// Törmäys omenan kanssa
			if (snakeArray.get(0).x <= apple.getX() + 15
					&& snakeArray.get(0).x >= apple.getX() - 15
					&& snakeArray.get(0).y <= apple.getY() + 15
					&& snakeArray.get(0).y >= apple.getY() - 15) {
				apple.setApple(false);
				snakeArray.add(new Point((snakeArray.get(0).x), snakeArray
						.get(0).y));
				score += 10;
			}


		// Tehdään uusi omena
		if (apple.isApple() == false) {
			apple.newPoint();
			apple.setApple(true);
		}

		// Piirretään omena
		graphics2D.setColor(Color.RED);
		graphics2D.drawImage(Apple.APPLE_IMAGE, apple.getX(), apple.getY(), null);
		
		// Piirretään Score
		graphics2D.setColor(Color.GREEN);
		graphics2D.drawString("Score: " + score, 400, 20);

		// Game over jos törmätään seinään
		if (snakeArray.get(0).x > this.getWidth() || snakeArray.get(0).x < 0) {
			t.stop();
			graphics2D.setColor(Color.RED);
			graphics2D.drawString("Game Over!", 200, 250);
		}
		if (snakeArray.get(0).y > this.getHeight() || snakeArray.get(0).y < 0) {
			t.stop();
			graphics2D.setColor(Color.RED);
			graphics2D.drawString("Game Over!", 200, 250);
		}

	}

	@Override
	// Game loop
	public void actionPerformed(ActionEvent e) {

		// Muutetaan vain pään paikkaa ja muu ruumis seuraa perässä
		snakeArray.get(0).x += squareXSpeed;
		snakeArray.get(0).y += squareYSpeed;

		repaint(); // call render (paintcomponent)
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (squareYSpeed == 0) {
				squareXSpeed = 0;
				squareYSpeed = speed;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (squareXSpeed == 0) {
				squareXSpeed = speed;
				squareYSpeed = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (squareXSpeed == 0) {
				squareXSpeed = -speed;
				squareYSpeed = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (squareYSpeed == 0) {
				squareXSpeed = 0;
				squareYSpeed = -speed;
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
