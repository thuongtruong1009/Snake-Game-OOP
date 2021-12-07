package utils;

import java.awt.*; 
import javax.swing.*; 
import javax.swing.event.*; 
 
public class ParallelInput extends JFrame implements DocumentListener { 
 
    JTextField entry; 
    JTextField entryToSet = new JTextField(); 
 
    public ParallelInput() { 
        createWindow(); 
        entry.getDocument().addDocumentListener(this); 
    } 
 
    private void createWindow() { 
        JFrame frame = new JFrame("Swing Tester"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        createUI(frame); 
        frame.setSize(560, 200); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true); 
    } 
 
    private void createUI(final JFrame frame) { 
        JPanel panel = new JPanel(); 
        entry = new JTextField(); 
        entryToSet = new JTextField(); 
 
        LayoutManager layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS); 
        panel.setLayout(layout); 
 
        panel.add(this.entry); 
        panel.add(entryToSet); 
        frame.getContentPane().add(panel, BorderLayout.CENTER); 
    } 
 
    public void setTextInTargetTxtField() { 
        String s = entry.getText(); 
        //HERE, BELOW, IS THE ACTION  
        entryToSet.setText(s); 
    } 
 
    public void insertUpdate(DocumentEvent ev) { 
        setTextInTargetTxtField(); 
    } 
 
    public void removeUpdate(DocumentEvent ev) { 
        setTextInTargetTxtField(); 
    } 
 
    public void changedUpdate(DocumentEvent ev) { 
    } 
 
    public static void main(String args[]){ 
        //Schedule a job for the event dispatch thread:        //creating and showing this application's GUI.        SwingUtilities.invokeLater(new Runnable() { 
                new ParallelInput().setVisible(true); 
            } 
    }
