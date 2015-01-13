package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gamepanel extends JPanel implements ActionListener, KeyListener {

	private final int SQUARE_SIZE = 20;
	private final int APPLE_SIZE = 15;
	private int squareXSpeed = 0;
	private int squareYSpeed = 0;
	private int speed = 20;
	private Point apple;
	private boolean isApple = true;
	private Random rand;
	private Timer t;
	private int score;

	ArrayList<Point> snakeArray = new ArrayList<Point>();

	public Gamepanel() {

		addKeyListener(this);
		t = new Timer(100, this); // Swingtimer 25millis
		t.start();

		// alussa 4 palaa
		snakeArray.add(new Point(0, 0));
		snakeArray.add(new Point(0, -1));
		snakeArray.add(new Point(0, -2));
		snakeArray.add(new Point(0, -3));

		rand = new Random();

		apple = new Point(rand.nextInt(450), rand.nextInt(450));

	}

	// Render
	public void paintComponent(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 500, 500);

		g.setColor(Color.GREEN);

		// Loopataan mato ja piirretään palaset
		for (int i = snakeArray.size() - 1; i > 0; i--) {
			g.fillRect(snakeArray.get(i).x, snakeArray.get(i).y, SQUARE_SIZE,
					SQUARE_SIZE);
			snakeArray.get(i).x = snakeArray.get(i - 1).x;
			snakeArray.get(i).y = snakeArray.get(i - 1).y;
		}

		// Törmäys omenan kanssa
		if (snakeArray.get(0).x <= apple.x + 15
				&& snakeArray.get(0).x >= apple.x - 15
				&& snakeArray.get(0).y <= apple.y + 15
				&& snakeArray.get(0).y >= apple.y - 15) {
			isApple = false;
			snakeArray
					.add(new Point((snakeArray.get(0).x), snakeArray.get(0).y));
			score += 10;
		}

		// Tehdään uusi omena
		if (!isApple) {
			apple = new Point(rand.nextInt(450), rand.nextInt(450));
			isApple = true;
		}

		// Piirretään omena
		g.setColor(Color.RED);
		g.fillOval(apple.x, apple.y, APPLE_SIZE, APPLE_SIZE);

		// Piirretään Score
		g.setColor(Color.GREEN);
		g.drawString("Score: " + score, 400, 20);

		// Game over jos törmätään seinään
		if (snakeArray.get(0).x > this.getWidth() || snakeArray.get(0).x < 0) {
			t.stop();
			g.setColor(Color.RED);
			g.drawString("Game Over!", 200, 250);
		}
		if (snakeArray.get(0).y > this.getHeight() || snakeArray.get(0).y < 0) {
			t.stop();
			g.setColor(Color.RED);
			g.drawString("Game Over!", 200, 250);
		}

		System.out.println("Apple x: " + apple.x + " Apple y: " + apple.y);
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
