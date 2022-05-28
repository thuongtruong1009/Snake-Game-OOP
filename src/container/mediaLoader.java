package container;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class mediaLoader {
	public static BufferedImage loadImage(String mainImage) {
		try {
			return ImageIO.read(mediaLoader.class.getResource(mainImage));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	public static Clip LoadSound(String mainSound) {
		try {
			Thread.sleep(300);
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(mediaLoader.class.getResource(mainSound)));
			return clip;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Clip eatSound(String eatSound) {
		try {
			// Thread.sleep(300);
			Clip clip2 = AudioSystem.getClip();
			clip2.open(AudioSystem.getAudioInputStream(mediaLoader.class.getResource(eatSound)));
			return clip2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}