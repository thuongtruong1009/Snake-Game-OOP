package codeThuongTruong.java;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author ThuongTruong-ITITIU19228
 *
 */
public class WelcomePage extends JFrame {

	private static final long serialVersionUID = 1L;

	public WelcomePage() {		
			JPanel panel= new JPanel();
			add(panel);		
			add(new GamePanel(LoginPage.SPEED));		
			setTitle("Snake Game");
			pack();		
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);		
	}
}
