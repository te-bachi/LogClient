package com.oscilloquartz.logclient.gui;

import javax.swing.*;

/**
 *
 */
public class MainMenuBar extends JMenuBar {
    public MainMenuBar() {
        super();

        JMenu file;
        JMenuItem exit;


        file = new JMenu("File");
        exit = new JMenuItem("Exit");

        file.add(exit);
        add(file);
    }
}
