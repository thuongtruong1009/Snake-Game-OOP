package utils;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class GradientBorder {
	public JComponent makeUI() {
		Icon icon = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g.create();
				Point2D start = new Point2D.Float(0f, 0f);
				Point2D end = new Point2D.Float(99f, 0f);
				float[] dist = { 0.0f, 0.5f, 1.0f };
				Color[] colors = { Color.RED, Color.YELLOW, Color.GREEN };
				g2.setPaint(new LinearGradientPaint(start, end, dist, colors));
				g2.fillRect(x, y, 100, 10);
				g2.dispose();
			}

			@Override
			public int getIconWidth() {
				return 100;
			}

			@Override
			public int getIconHeight() {
				return 10;
			}
		};
		JLabel label = new JLabel("GradientMatteBorder");
		label.setBorder(BorderFactory.createMatteBorder(10, 5, 10, 0, icon));
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		p.add(label, BorderLayout.NORTH);
		return p;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.getContentPane().add(new GradientBorder().makeUI());
		f.setSize(320, 240);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}