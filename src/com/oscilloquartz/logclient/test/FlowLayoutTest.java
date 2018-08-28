package com.oscilloquartz.logclient.test;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(new FlowLayout());
        JButton button = new JButton("Button 1");
        button.setPreferredSize(new Dimension(300, 100));
        panel.add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
