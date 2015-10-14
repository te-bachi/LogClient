package com.oscilloquartz.logclient.gui;

import com.oscilloquartz.logclient.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        ArrayList<Image>    imageList;
        Container           contentPane;

        imageList = new ArrayList<>();
        imageList.add(new ImageIcon(Main.class.getResource("images/icon64x64.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon40x40.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon32x32.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon20x20.png")).getImage());

        setTitle("Simple menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImages(imageList);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(800, 600));

        setJMenuBar(new MainMenuBar());

        contentPane = getContentPane();
        contentPane.add(new ConnectPanel(), BorderLayout.PAGE_START);
        contentPane.add(new LogPanel(), BorderLayout.CENTER);
    }


}
