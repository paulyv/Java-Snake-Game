package game;

import java.io.IOException;

import org.newdawn.easyogg.OggClip;

public class MusicManager {

	private OggClip music;
	// private OggClip pickup;

	public MusicManager() {
		try {
		//	pickup = new OggClip(this.getClass().getResourceAsStream(
		//			"/res/pickup.ogg"));
			
			music = new OggClip(this.getClass().getResourceAsStream(
					"/res/saga_musix_candy.ogg"));
			

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
		// pickup.loop();

	}

}
