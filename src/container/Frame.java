package container;

import root.Root;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
/**
 * @author https://www.github.com/thuongtruong1009
 */
public class Frame extends JPanel {
	JFrame frame= new JFrame();
	JPanel panel= new JPanel();
	private static final long serialVersionUID = 1L;
	static int DELAY=100;
	Timer timer;
	Root root = new Root();
	
	JLabel pic;
    Timer tm;
    int x = 0;
    
	
	@SuppressWarnings("static-access")
	public Frame()  {
        pic = new JLabel();
        pic.setBounds(0, 0, 500, 235);

        //Call The Function SetImageSize
        SetImageSize(5);
               //set a timer
        tm = new Timer(1000,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SetImageSize(x);
                x += 1;
                if(x >= root.list.length )
                    x = 0; 
            }
        });
        panel.add(pic, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout());
		tm.start();
//		
//		ImageIcon image= new ImageIcon("./src/backgrounds/background2.jpg");
//		Image img= image.getImage();
//		Image temp_img= img.getScaledInstance(500, 235, Image.SCALE_SMOOTH);
//		image= new ImageIcon(temp_img);
//		
//		JLabel background= new JLabel("", image, JLabel.CENTER);
//		background.setBounds(0, 0, 500, 300);
//		panel.add(background, BorderLayout.CENTER);

		JMenuBar menubar= new JMenuBar();
		frame.add(menubar, BorderLayout.NORTH);
		
		//menu1
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.addSeparator();
		menubar.add(fileMenu);
		
		JMenuItem scoreItem= new JMenuItem("History score", root.ic1);
		fileMenu.add(scoreItem);
		scoreItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readFile("./src/high_score.txt", "history");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem newGameItem= new JMenuItem("New Game", root.ic2);
		fileMenu.add(newGameItem);
		newGameItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SetupData();
			}
		});
		
		JMenuItem exitItem= new JMenuItem("Quit", root.ic3);
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		//menu2
		JMenu levelMenu= new JMenu("Level");
		levelMenu.setMnemonic(KeyEvent.VK_L);
		levelMenu.addSeparator();
		menubar.add(levelMenu);
		
		JMenuItem[] menuItem2= new JMenuItem[3];
		String[] array= {"Easy", "Medium", "Difficult"};
		
		for(int i= 0 ; i<3 ; i++) {				
				int level = i + 1;
				ImageIcon ii = new ImageIcon(root.array2[i]);
				menuItem2[i]= new JMenuItem(array[i] + " (" + level + ")", ii);
				menuItem2[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {	
						//menuItem2[i].setBackground(Color.green);
						DELAY = 100 - 20*level;
					}
				});
				levelMenu.add(menuItem2[i]);
		}
		
		//menu3
		JMenu referMenu= new JMenu("More");
		referMenu.setMnemonic(KeyEvent.VK_M);
		referMenu.addSeparator();
		menubar.add(referMenu);
		
		JMenuItem discussItem= new JMenuItem("Discuss", root.icn1);
		referMenu.add(discussItem);
		discussItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink(root.discuss);
			}
		});
		
		JMenuItem licenseItem= new JMenuItem("License", root.icn2);
		referMenu.add(licenseItem);
		licenseItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					readFile("./src/LICENSE.txt", "ECL-2.0 LICENSE");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem contactItem= new JMenuItem("Contact", root.icn3);
		referMenu.add(contactItem);
		contactItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink(root.contact);
			}
		});

		//menu4
		JMenu helpMenu= new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.addSeparator();
		menubar.add(helpMenu);
		
		JMenuItem tutorialItem= new JMenuItem("How to play?", root.Icn1);
		helpMenu.add(tutorialItem);
		tutorialItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink(root.how_to_play);
			}
		});
		
		JMenuItem reportItem= new JMenuItem("Report bugs", root.Icn2);
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
				try {
					readFile("./src/LICENSE.txt", "ECL-2.0 LICENSE");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
			
		//frame
		frame.add(panel);
		frame.setTitle("Snake Game OOP - v2.0");
		frame.setSize(500, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		Image imageIcon = root.icon.getImage();
		frame.setIconImage(imageIcon);
	}
	
	//create a function to resize the image 
    public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(root.list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
    }
	
	public static void readFile(String file, String name)throws IOException{
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String aLineFromFile = null;
	    StringBuilder everything = new StringBuilder();
	    while ((aLineFromFile = br.readLine()) != null){
	    	everything.append(aLineFromFile + "\n");
	    }
	    JTextArea textArea = new JTextArea(everything.toString());
	    textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 0)));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize( new Dimension( 570, 450 ));
		
	    JOptionPane.showMessageDialog(null, scrollPane, name, JOptionPane.INFORMATION_MESSAGE);
	    br.close();
	    return;
	}
	
	 public static void openLink(String uri) {
			try {		
				 Desktop.getDesktop().browse(new URI(uri));
			} catch (IOException | URISyntaxException e) {
			   e.printStackTrace();
			}
	  }

	public void play() {	
		System.out.println("The speed you choose is: " + DELAY);
		timer = new Timer(DELAY, (ActionListener) this);
		timer.start();
	}
}