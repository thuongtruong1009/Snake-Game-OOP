package container;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import files.DownLoad;
import files.Write;
import root.Root;

/**
 * @author https://www.github.com/thuongtruong1009
 */
public class CustomerPane extends JOptionPane {
	private static final long serialVersionUID = 1L;

	public static void donateFrame() {
		Root root = new Root();
		Object[] options = { "Paypal", "MoMo", "Kofi" };
		int result = JOptionPane.showOptionDialog(null, root.donateMessage, root.donateTitle,
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (result == JOptionPane.YES_OPTION) {
			Frame.openLink(root.paypalLink);
		} else if (result == JOptionPane.NO_OPTION) {
			Frame.openLink(root.momoLink);
		} else if (result == JOptionPane.CANCEL_OPTION) {
			Frame.openLink(root.kofiLink);
		}
	}

	public static void showInputDialog(final Object message, final Object[] options) throws HeadlessException {
		final JOptionPane pane = new JOptionPane(message, QUESTION_MESSAGE, OK_CANCEL_OPTION, null, options, null);
		pane.setMessageType(QUESTION_MESSAGE);
		final String title = UIManager.getString("OptionPane.messageDialogTitle", null);
		final JDialog dialog = pane.createDialog(null, title);
		dialog.setVisible(true);
	}

	public static void readFile(String file, String title, int width, int height) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String aLineFromFile = null;
		StringBuilder everything = new StringBuilder();
		while ((aLineFromFile = br.readLine()) != null) {
			everything.append(aLineFromFile + "\n");
		}
		JTextArea textArea = new JTextArea(everything.toString());
		textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(),
				BorderFactory.createEmptyBorder(10, 10, 10, 0)));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(width, height));

		Root root = new Root();
		if (file == root.scorePath) {
			Object[] options = { "✔ Ok", "⤓ Download", "⌦ Clear history score" };
			int result = JOptionPane.showOptionDialog(null, scrollPane, title, JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (result == JOptionPane.YES_OPTION) {

			} else if (result == JOptionPane.NO_OPTION) {
				DownLoad.downloadSuccess();
			} else if (result == JOptionPane.CANCEL_OPTION) {
				Object[] options2 = { "✔ Agree", "Cancel" };
				int result2 = JOptionPane.showOptionDialog(null, root.deleteDisc, root.deleteTitle,
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, options2[1]);
				if (result2 == JOptionPane.YES_OPTION) {
					deleteContent(Write.fileData);
				}
			}
		} else if (file == root.licensePath) {
			Object[] options = { "✔ I understand", "✍ Copy license" };
			int result = JOptionPane.showOptionDialog(null, scrollPane, title, JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (result == JOptionPane.YES_OPTION) {

			} else if (result == JOptionPane.NO_OPTION) {
				CustomerPane.copyContent(everything.toString());
			}
		}

		br.close();
		return;
	}

	public static void deleteContent(String fileURL) {
		// delete content after read file
		try {
			new FileWriter(fileURL, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void copyContent(String s) {
		StringSelection stringSelection = new StringSelection(s);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
}