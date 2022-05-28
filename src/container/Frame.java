package container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import files.Read;
import files.Write;
import root.Root;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class Frame extends JPanel {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	private static final long serialVersionUID = 1L;
	public static int DELAY = 100;
	public static int SPEED = 1;
	Timer timer;
	Root root = new Root();

	JLabel pic;
	Timer tm;
	int x = 0;

	@SuppressWarnings("static-access")
	public Frame() {
		//GamePanel.getSound(root.outroSound);

		pic = new JLabel();
		pic.setBounds(0, 0, 500, 235);

		// Call The Function SetImageSize
		SetImageSize(5);
		tm = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SetImageSize(x);
				x += 1;
				if (x >= root.list.length)
					x = 0;
			}
		});
		panel.add(pic, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout());
		tm.start();

		// ImageIcon image= new ImageIcon("./src/backgrounds/background2.jpg");
		// Image img= image.getImage();
		// Image temp_img= img.getScaledInstance(500, 235, Image.SCALE_SMOOTH);
		// image= new ImageIcon(temp_img);
		//
		// JLabel background= new JLabel("", image, JLabel.CENTER);
		// background.setBounds(0, 0, 500, 300);
		// panel.add(background, BorderLayout.CENTER);

		JMenuBar menubar = new JMenuBar();
		frame.add(menubar, BorderLayout.NORTH);

		// menu1
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.addSeparator();
		fileMenu.setIcon(root.fileMenu);
		menubar.add(fileMenu);

		JMenuItem newGameItem = new JMenuItem("New Game", root.ic2);
		fileMenu.add(newGameItem);
		newGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SetupData();
			}
		});

		JMenuItem scoreItem = new JMenuItem("History", root.ic1);
		fileMenu.add(scoreItem);
		scoreItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// delete content after read file
				CustomerPane.deleteContent(Read.fileURL);
				Read r = new Read();
				r.readInfor(Write.fileData);
				try {
					CustomerPane.readFile(root.scorePath, root.scoreTitle, 750, 350);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JMenuItem exitItem = new JMenuItem("Quit", root.ic3);
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// menu2
		JMenu levelMenu = new JMenu("Level");
		levelMenu.setMnemonic(KeyEvent.VK_L);
		levelMenu.addSeparator();
		levelMenu.setIcon(root.levelMenu);
		menubar.add(levelMenu);

		JMenuItem[] menuItem2 = new JMenuItem[3];
		String[] array = { "Easy", "Medium", "Difficult" };

		for (int i = 0; i < 3; i++) {
			int level = i + 1;
			ImageIcon ii = new ImageIcon(root.array2[i]);
			menuItem2[i] = new JMenuItem(array[i] + " (" + level + ")", ii);
			menuItem2[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					menuItem2[level - 1].setBackground(Color.GREEN);
					DELAY = 100 - 20 * level;
					SPEED = level;
				}
			});
			levelMenu.add(menuItem2[i]);
		}

		// menu3
		JMenu referMenu = new JMenu("More");
		referMenu.setMnemonic(KeyEvent.VK_M);
		referMenu.addSeparator();
		referMenu.setIcon(root.moreMenu);
		menubar.add(referMenu);

		JMenuItem forkItem = new JMenuItem("Fork project", root.icn1);
		referMenu.add(forkItem);
		forkItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink(root.fork);
			}
		});

		JMenuItem discussItem = new JMenuItem("Discuss", root.icn2);
		referMenu.add(discussItem);
		discussItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink(root.discuss);
			}
		});

		JMenuItem licenseItem = new JMenuItem("License", root.icn3);
		referMenu.add(licenseItem);
		licenseItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CustomerPane.readFile(root.licensePath, root.licenseTitle + " - " + root.version, 570, 450);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JMenuItem contactItem = new JMenuItem("Contact", root.icn4);
		referMenu.add(contactItem);
		contactItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink(root.contact);
			}
		});

		// menu4
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.addSeparator();
		helpMenu.setIcon(root.helpMenu);
		menubar.add(helpMenu);

		JMenuItem tutorialItem = new JMenuItem("How to play?", root.Icn1);
		helpMenu.add(tutorialItem);
		tutorialItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink(root.how_to_play);
			}
		});

		JMenuItem reportItem = new JMenuItem("Report bugs", root.Icn2);
		helpMenu.add(reportItem);
		reportItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink(root.bug);
			}
		});

		JMenuItem donateItem = new JMenuItem("Donate", root.Icn3);
		helpMenu.add(donateItem);
		donateItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerPane.donateFrame();
			}
		});
		JMenuItem aboutItem = new JMenuItem("About", root.Icn4);
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				JOptionPane.showMessageDialog(null, root.aboutDesc, root.aboutTitle, JOptionPane.INFORMATION_MESSAGE, root.Icn4);
			}
		});

		// frame
		frame.add(panel);
		frame.setTitle(root.frameTitle);
		frame.setSize(500, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		Image imageIcon = root.icon.getImage();
		frame.setIconImage(imageIcon);
	}

	// create a function to resize the image
	public void SetImageSize(int i) {
		ImageIcon icon = new ImageIcon(root.list[i]);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon newImc = new ImageIcon(newImg);
		pic.setIcon(newImc);
	}

	public static void openLink(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		System.out.println("The speed you choose is: " + DELAY);
		timer = new Timer(DELAY, (ActionListener) this);
		timer.start();
	}
	public static void main(String[] args) {
				new Frame();
	}
}