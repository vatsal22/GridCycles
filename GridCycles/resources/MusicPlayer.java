import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class MusicPlayer {
	private Clip backgroundMusic;
	private Clip pew;
	private Clip death;
	private Clip warpSpeed;
	private Clip shieldHum;

	public MusicPlayer() {
		initClips();
	}

	private void initClips() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("BackgroundMusic.wav").getAbsoluteFile());
			backgroundMusic = AudioSystem.getClip();
			backgroundMusic.open(ais);
		} catch (Exception e) {
			System.out.println("BackgroundMusic.wav could not be read");
		}
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("Pew.wav").getAbsoluteFile());
			pew = AudioSystem.getClip();
			pew.open(ais);
		} catch (Exception e) {
			System.out.println("Pew.wav could not be read");
		}
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("Death.wav").getAbsoluteFile());
			death = AudioSystem.getClip();
			death.open(ais);
		} catch (Exception e) {
			System.out.println("Death.wav could not be read");
		}
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("WarpSpeed.wav").getAbsoluteFile());
			warpSpeed = AudioSystem.getClip();
			warpSpeed.open(ais);
		} catch (Exception e) {
			System.out.println("WarpSpeed.wav could not be read");
		}
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("ShieldHum.wav").getAbsoluteFile());
			shieldHum = AudioSystem.getClip();
			shieldHum.open(ais);
		} catch (Exception e) {
		}

	}

	// Sound effects are wrapped in try/catch so game can be played without
	// sound files
	public void playDeath() {
		try {
			death.setFramePosition(0);
			death.start();
		} catch (Exception e) {
		}
	}

	public void playShieldHum() {
		try {
			shieldHum.setFramePosition(0);
			shieldHum.start();
		} catch (Exception e) {
		}
	}

	public void playWarpSpeed() {
		try {
			warpSpeed.setFramePosition(0);
			warpSpeed.start();
		} catch (Exception e) {
		}

	}

	public void startBackgroundMusic() {
		try {
			backgroundMusic.start();
			backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {

		}
	}

	public void playPew() {
		try {
			pew.setFramePosition(0);
			pew.start();
		} catch (Exception e) {
		}

	}

	public void stopBackgroundMusic() {
		try {
			backgroundMusic.stop();
		} catch (Exception e) {
		}
	}

}
