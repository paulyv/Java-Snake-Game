package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.newdawn.easyogg.OggClip;

public class MusicManager {

	private OggClip music;
	private File pickup;

	public MusicManager() {
		try {
			music = new OggClip(this.getClass().getResourceAsStream(
					"/res/saga_musix_candy.ogg"));
			
			pickup = new File("res/pickup.wav");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loopPlaying() {
		music.loop();
	}

	public void stopPlaying() {
		music.stop();

	}

	public void pausePlaying() {
		music.pause();
	}

	public void resumePlaying() {
		music.resume();
	}

	public void playPickup() {
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(pickup));
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		
		

	}

}
