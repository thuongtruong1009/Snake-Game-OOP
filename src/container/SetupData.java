package container;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import files.Write;

/**
 * @author https://www.github.com/thuongtruong1009
 */
public class SetupData extends JFrame {
	Write writeFile = new Write();
	private static final long serialVersionUID = 1L;

	public SetupData() {
		JPanel panel = new JPanel();
		add(panel);
		add(new GamePanel(Frame.DELAY));
		setTitle("Speed: " + Frame.DELAY + " - Time: " + getTime());
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		// EXIT_ON_CLOSE close system, DISPOSE_ON_CLOSE dispose close frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private String getTime() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
		String time = dateFormat.format(date);
		return time;
	}
}