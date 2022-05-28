package container;

import java.awt.EventQueue;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Frame();
			}
		});
	}
}