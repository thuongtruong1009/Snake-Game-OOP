package thuongtruong1009;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author https://www.github.com/thuongtruong1009
 */
public class SetupData extends JFrame {
	private static final long serialVersionUID = 1L;
	public SetupData() {		
		JPanel panel= new JPanel();
		add(panel);		
		add(new GamePanel(Frame.DELAY));		
		setTitle("Playing");
		pack();		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
}