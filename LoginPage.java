/** Copyright CodeJava.net To Present 
All rights reserved.
*/
/**
 * 
 */
package codeThuongTruong.java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author ThuongTruong-ITITIU19228
 *
 */
public class LoginPage extends JPanel {
	JFrame frame= new JFrame();
	JPanel panel= new JPanel();
	private static final long serialVersionUID = 1L;
	static int SPEED=100;
	Timer timer;
	
	@SuppressWarnings("static-access")
	public LoginPage()  {
		
		ImageIcon image= new ImageIcon("D:\THUONG TRUONG INC\CODE PROGRAMMING\Java Code\Snackgame OOP (ITITIU19228-ITITIU19181)\src\background.jpg");
		Image img= image.getImage();
		Image temp_img= img.getScaledInstance(500, 300, Image.SCALE_SMOOTH);
		image= new ImageIcon(temp_img);
		
		JLabel background= new JLabel("", image, JLabel.CENTER);
		background.setBounds(0, 0, 500, 300);
		panel.add(background, BorderLayout.CENTER);	


		JMenuBar menubar= new JMenuBar();
		frame.add(menubar, BorderLayout.NORTH);
		//menu1
		JMenu menu1 = new JMenu("File");
		menu1.setMnemonic(KeyEvent.VK_F);
		menu1.addSeparator();
		menubar.add(menu1);
		
		JMenuItem newGameItem= new JMenuItem("New Game");
		menu1.add(newGameItem);
		newGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new WelcomePage();
			}
		});
		
		JMenuItem exitItem= new JMenuItem("Exit");
		menu1.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		//menu2
		JMenu levelItem= new JMenu("Level");
		levelItem.setMnemonic(KeyEvent.VK_L);
		levelItem.addSeparator();
		menubar.add(levelItem);	
		
		JMenuItem[] menuItem2= new JMenuItem[3];
		String[] array= {"Easy", "Medium", "Difficult"};
		for(int i= 0 ; i<3 ; i++) {				
				int level = i + 1;
				menuItem2[i]= new JMenuItem(array[i]);
				menuItem2[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {	
						//menuItem2[i].setBackground(Color.green);
						SPEED = 100 - 20*level;
					}
				});
				levelItem.add(menuItem2[i]);
		}	
			
		//frame
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		frame.setTitle("Snack Game");
		frame.setSize(500, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void play() {	
		System.out.println("Start " + SPEED);
		timer = new Timer(SPEED, (ActionListener) this);
		timer.start();
	}
	public static void main(String[] args) {
		new LoginPage();
	}
}
