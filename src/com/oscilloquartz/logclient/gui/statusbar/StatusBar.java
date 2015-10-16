package com.oscilloquartz.logclient.gui.statusbar;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class StatusBar extends JPanel {

    private Icon   icon;
    private JLabel iconLabel;
    private JLabel textLabel;

    public StatusBar() {
        JPanel rightPanel;

        setLayout(new BorderLayout());
        setBackground(SystemColor.control);
        setPreferredSize(new Dimension(10, 23));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        this.icon       = new AngledLinesWindowsCornerIcon();
        this.iconLabel  = new JLabel(this.icon);
        rightPanel      = new JPanel(new BorderLayout());
        rightPanel.add(this.iconLabel, BorderLayout.SOUTH);
        rightPanel.setOpaque(false);

        add(rightPanel, BorderLayout.EAST);
        add(new JLabel("Ready!"), BorderLayout.CENTER);
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int y = 0;
        g.setColor(new Color(156, 154, 140));
        g.drawLine(0, y, getWidth(), y);
        y++;
        g.setColor(new Color(196, 194, 183));
        g.drawLine(0, y, getWidth(), y);
        y++;
        g.setColor(new Color(218, 215, 201));
        g.drawLine(0, y, getWidth(), y);
        y++;
        g.setColor(new Color(233, 231, 217));
        g.drawLine(0, y, getWidth(), y);

        y = getHeight() - 3;
        g.setColor(new Color(233, 232, 218));
        g.drawLine(0, y, getWidth(), y);
        y++;
        g.setColor(new Color(233, 231, 216));
        g.drawLine(0, y, getWidth(), y);
        y = getHeight() - 1;
        g.setColor(new Color(221, 221, 220));
        g.drawLine(0, y, getWidth(), y);

    }

}
