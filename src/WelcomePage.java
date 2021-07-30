package codeThuongTruong.java;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
/**
 * @author ThuongTruong-ITITIU19228
 *
 */
public class WelcomePage extends JFrame {
	public WelcomePage() {		
			JPanel panel= new JPanel();
			add(panel);		
			add(new GamePanel(LoginPage.SPEED));		
			setTitle("SNACK GAME");
			pack();		
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);		
	}
}
