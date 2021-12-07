package utils;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BorderFrame extends JFrame{
    JLabel label = new JLabel("Welcome!", JLabel.CENTER);
    public BorderFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(200, 200));
        add(label, BorderLayout.CENTER);
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        setVisible(true);

    }
    public static void main(String[] args) {
        new BorderFrame();
    }

} 