package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Highscore {

	// Variables
	private File f = new File("highscore.dat");
	private int score;

	// Constructor. Create a new file or read the old one
	public Highscore() {

		if (f.exists()) {
			readScore();
		} else {
			setScore(0);
		}
	}

	// Write score to a file
	public void setScore(int score) {
		if (this.score < score) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write(score);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Read score from a file
	private void readScore() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			score = br.read();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Return score variable
	public int getScore() {
		return score;
	}
}
