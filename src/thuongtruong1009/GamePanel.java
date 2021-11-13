package thuongtruong1009;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.Random;

/**
 * @author https://www.github.com/thuongtruong1009
 */
public class GamePanel extends MainFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	static int WIDTH = 600;
	static int HEIGHT = 600;
	int UNIT_SIZE = 25;
	int GAME_UNITS = (WIDTH * HEIGHT) / UNIT_SIZE;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 5;
	int score;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	static Timer timer;
	Random random;
	private Clip music1, music2, music3;

	public GamePanel(int SPEED) {
		random = new Random();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();

		music1 = ImageLoader.LoadSound("../music.wav");
		music1.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void startGame() {
		newApple();
		running = true;
		super.play();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			draw(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) throws IOException {
		if (running) {

			for (int i = 0; i < HEIGHT / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, WIDTH, i * UNIT_SIZE);
			}
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					g.setColor(Color.green);
					;
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					g.setColor(new Color(45, 180, 0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			// Game Score text
			g.setColor(Color.red);
			g.setFont(new Font("Hi Bold", Font.PLAIN, 20));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + score, (WIDTH - metrics.stringWidth("Score:")) / 2, UNIT_SIZE);
		} else {
			running = false;
			gameOver(g);
		}
	}

	public void newApple() {
		appleX = random.nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
	}

	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}

	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			score++;
			newApple();
			music2 = ImageLoader.eatSound("../eat.wav");
			music2.start();
		}
	}

	public void checkCollisions() {
		music3 = ImageLoader.eatSound("../gameOver.wav");
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
				music3.start();
			}
		}
		// check if head touches left border
		if (x[0] < 0) {
			running = false;
			music3.start();
		}
		// check if head touches right border
		if (x[0] > WIDTH) {
			running = false;
			music3.start();
		}
		// check if head touches top border
		if (y[0] < 0) {
			running = false;
			music3.start();
		}
		// check if head touches bottom border
		if (y[0] > HEIGHT) {
			running = false;
			music3.start();
		}
	}

	public void gameOver(Graphics g) throws IOException {
		// Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("MV BOLI", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);
		music1.stop();

		// Game Score text
		g.setColor(Color.yellow);
		g.setFont(new Font("Hi BOLI", Font.PLAIN, 25));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Your score is: " + score, (WIDTH - metrics2.stringWidth("Your score is: ")) / 2,
				(HEIGHT / 3) + 2 * UNIT_SIZE);

		clickLabelImage("./src/restartButton.png", 170, 100);
		clickLabelImage("./src/quitButton.png", 100, 50);
	}

	public void clickLabelImage(String url, int LABEL_WIDTH, int LABEL_HEIGHT) {
		ImageIcon image = new ImageIcon(url);
		Image img = image.getImage();
		Image temp_img = img.getScaledInstance(LABEL_WIDTH, LABEL_HEIGHT, Image.SCALE_SMOOTH);
		image = new ImageIcon(temp_img);

		JLabel label = new JLabel("", image, JLabel.CENTER);
		this.add(label, BorderLayout.CENTER);
		if (url == "./src/restartButton.png") {
			label.setBounds(WIDTH / 2 - LABEL_WIDTH / 2, 3 * HEIGHT / 5, LABEL_WIDTH, LABEL_HEIGHT);
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new SetupData();
				}
			});
		} else if (url == "./src/quitButton.png") {
			label.setBounds(WIDTH / 2 - LABEL_WIDTH / 2, 3 * HEIGHT / 5 + 2 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}