package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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

		addKeyListener(this);
		t = new Timer(100, this); // Swingtimer 25millis
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
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 500, 500);

		g.setColor(Color.GREEN);

		// Loopataan mato ja piirret��n palaset
		for (int i = snakeArray.size() - 1; i > 0; i--) {
			g.fillRect(snakeArray.get(i).x, snakeArray.get(i).y, SQUARE_SIZE,
					SQUARE_SIZE);
			snakeArray.get(i).x = snakeArray.get(i - 1).x;
			snakeArray.get(i).y = snakeArray.get(i - 1).y;
		}

		// T�rm�ys itsens� kanssa

		for (int i = snakeArray.size() - 1; i > 1; i--) {
			if (snakeArray.get(0).x == snakeArray.get(i).x
					&& snakeArray.get(0).y == snakeArray.get(i).y) {
				t.stop();
				g.setColor(Color.RED);
				g.drawString("Game Over!", 200, 250);
			}
		}

			// T�rm�ys omenan kanssa
			if (snakeArray.get(0).x <= apple.getX() + 15
					&& snakeArray.get(0).x >= apple.getX() - 15
					&& snakeArray.get(0).y <= apple.getY() + 15
					&& snakeArray.get(0).y >= apple.getY() - 15) {
				apple.setApple(false);
				snakeArray.add(new Point((snakeArray.get(0).x), snakeArray
						.get(0).y));
				score += 10;
			}


		// Tehd��n uusi omena
		if (apple.isApple() == false) {
			apple.newPoint();
			apple.setApple(true);
		}

		// Piirret��n omena
		g.setColor(Color.RED);
		g.fillOval(apple.getX(), apple.getY(), Apple.SIZE, Apple.SIZE);

		// Piirret��n Score
		g.setColor(Color.GREEN);
		g.drawString("Score: " + score, 400, 20);

		// Game over jos t�rm�t��n sein��n
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

		//System.out.println("Apple x: " + apple.x + " Apple y: " + apple.y);
	}

	@Override
	// Game loop
	public void actionPerformed(ActionEvent e) {

		// Muutetaan vain p��n paikkaa ja muu ruumis seuraa per�ss�
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
