package container;

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
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import files.Write;
import root.Root;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class GamePanel extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	Root root = new Root();
	int UNIT_SIZE = 25;
	int GAME_UNITS = (root.PANEL_WIDTH * root.PANEL_HEIGHT) / UNIT_SIZE;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 5;
	int score;
	int appleX;
	int appleY;
	int bombX;
	int bombY;
	char direction = 'R';
	boolean running = false;
	public static int keyPress;
	static Timer timer;
	Random random;
	private static Clip music1, music2, music3, music4;
	long startTime;
	long endTime;
	public static String duration;

	public GamePanel(int SPEED) {
		random = new Random();
		this.setPreferredSize(new Dimension(root.PANEL_WIDTH, root.PANEL_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame() {
		newApple();
		newBomb();
		running = true;
		keyPress = 0;
		super.play();
		music1 = mediaLoader.LoadSound(root.introSound);
		music1.loop(Clip.LOOP_CONTINUOUSLY);
		this.startTime = System.currentTimeMillis();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			drawSnake(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawSnake(Graphics g) throws IOException {
		if (running) {
			for (int i = 0; i < root.PANEL_HEIGHT / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, root.PANEL_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, root.PANEL_WIDTH, i * UNIT_SIZE);
			}

			// draw apples
			g.setColor(Color.red);
			// g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); // the same below
			Font currentFont = g.getFont();
			g.setFont(currentFont.deriveFont(currentFont.getSize() * 2.1F)); // change font size
			g.drawString("🍎", appleX, appleY + UNIT_SIZE);

			// draw bomb
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("💣", bombX, bombY + UNIT_SIZE);

			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					// Snake head
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					// Snake eyes
					g.setColor(Color.black);
					g.fillOval(x[i], y[i], UNIT_SIZE / 5, UNIT_SIZE / 5);
					g.fillOval(x[i] + 3 * UNIT_SIZE / 4, y[i], UNIT_SIZE / 5, UNIT_SIZE / 5);
				} // Snake tail
				else if (i == bodyParts - 1) {
					g.setColor(Color.green);
					g.drawLine(x[i], y[i] + UNIT_SIZE / 2, x[i] + UNIT_SIZE / 2, y[i]);
					g.drawLine(x[i] + UNIT_SIZE / 2, y[i], x[i] + UNIT_SIZE, y[i] + UNIT_SIZE / 2);
					g.drawLine(x[i] + UNIT_SIZE, y[i] + UNIT_SIZE / 2, x[i] + UNIT_SIZE / 2, y[i] + UNIT_SIZE);
					g.drawLine(x[i] + UNIT_SIZE / 2, y[i] + UNIT_SIZE, x[i], y[i] + UNIT_SIZE / 2);
				} // Snake body
				else {
					g.setColor(new Color(45, 180, 0));
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			// Game Score text
			g.setColor(Color.red);
			g.setFont(new Font("Hi Bold", Font.PLAIN, 20));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + score, (root.PANEL_WIDTH - metrics.stringWidth("Score:")) / 2, UNIT_SIZE);
		} else {
			running = false;
			gameOverView(g);
		}
	}

	public void newApple() {
		appleX = random.nextInt((int) (root.PANEL_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (root.PANEL_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}

	public void newBomb() {
		bombX = random.nextInt(random.nextInt((int) (root.PANEL_WIDTH / UNIT_SIZE))) * UNIT_SIZE;
		bombY = random.nextInt(random.nextInt((int) (root.PANEL_HEIGHT / UNIT_SIZE))) * UNIT_SIZE;
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
			music2 = mediaLoader.eatSound(root.eatAppleSound);
			music2.start();
		}
	}

	public void checkBomb() {
		if ((x[0] == bombX) && (y[0] == bombY)) {
			bodyParts--;
			score--;
			newBomb();
			music2 = mediaLoader.eatSound(root.bombSound);
			music2.start();
		}
	}

	public void checkCollisions() {
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				endGame(score, root.winSound);
			}
		}
		// check if head touches left border
		if (x[0] < 0) {
			endGame(score, root.winSound);
		}
		// check if head touches right border
		if (x[0] > root.PANEL_WIDTH) {
			endGame(score, root.winSound);
		}
		// check if head touches top border
		if (y[0] < 0) {
			endGame(score, root.winSound);
		}
		// check if head touches bottom border
		if (y[0] > root.PANEL_HEIGHT) {
			endGame(score, root.winSound);
		}
	}

	public void checkBodyLength(){
		if(bodyParts == 0){
			endGame(score, root.failSound);
		}
	}

	public void gameOverView(Graphics g) throws IOException {
		// Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("MV BOLI", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (root.PANEL_WIDTH - metrics.stringWidth("Game Over")) / 2, root.PANEL_HEIGHT / 3);
		music1.stop();

		// Game Score text
		g.setColor(Color.yellow);
		g.setFont(new Font("Hi BOLI", Font.PLAIN, 25));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Your score is: " + score, (root.PANEL_WIDTH - metrics2.stringWidth("Your score is: ")) / 2,
				(root.PANEL_HEIGHT / 2) + 2 * UNIT_SIZE);

		clickLabelImage(root.restartBtn, 170, 100);
		clickLabelImage(root.quitBtn, 100, 50);
	}

	public void endGame(int score, String musicPath) {
		running = false;
		getSound(musicPath);

		// progress format time
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
		String time = dateFormat.format(date);

		// progress format duration
		endTime = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.000");
		duration = formatter.format((endTime - startTime) / 1000d);

		Write.getData(score, time, duration);
	}

	public static void getSound(String musicPath){
		music3 = mediaLoader.eatSound(musicPath);
		music3.start();
	}

	public void clickLabelImage(String url, int LABEL_WIDTH, int LABEL_HEIGHT) {
		ImageIcon image = new ImageIcon(url);
		Image img = image.getImage();
		Image temp_img = img.getScaledInstance(LABEL_WIDTH, LABEL_HEIGHT, Image.SCALE_SMOOTH);
		image = new ImageIcon(temp_img);

		JLabel label = new JLabel("", image, JLabel.CENTER);
		this.add(label, BorderLayout.CENTER);
		if (url == root.restartBtn) {
			label.setBounds(root.PANEL_WIDTH / 2 - LABEL_WIDTH / 2, 3 * root.PANEL_HEIGHT / 5, LABEL_WIDTH,
					LABEL_HEIGHT);
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new SetupData();
					music4.stop();
				}
			});
		} else if (url == root.quitBtn) {
			label.setBounds(root.PANEL_WIDTH / 2 - LABEL_WIDTH / 2, 3 * root.PANEL_HEIGHT / 5 + 2 * LABEL_HEIGHT,
					LABEL_WIDTH, LABEL_HEIGHT);
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
					music4.stop();
				}
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkBomb();
			checkCollisions();
			checkBodyLength();
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
					keyPress++;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
					keyPress++;
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
					keyPress++;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
					keyPress++;
				}
			}
		}
	}
}