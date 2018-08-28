package com.oscilloquartz.logclient.gui.statusbar;

import com.oscilloquartz.logclient.gui.LogClientUtil;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 *
 */
public class StatusBar extends JPanel {

    private Icon   icon;
    private JLabel iconLabel;
    private JLabel stateLabel;
    private JLabel textLabel;

    public StatusBar() {
        JPanel rightPanel;
        JPanel bottomPanel;
        Border lineBorder;
        Border paddingBorder;

        setLayout(new BorderLayout());
        setBackground(SystemColor.control);
        setPreferredSize(new Dimension(LogClientUtil.scale(10), LogClientUtil.scale(23)));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        paddingBorder = BorderFactory.createEmptyBorder(0, 2, 0, 0);
        lineBorder = new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                super.paintBorder(c, g, x, y, width, height);
                g.setColor(Color.DARK_GRAY);
                g.drawLine(x + 1, 0, x + 1, height);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(0, 10, 0, 0);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        };

        this.stateLabel = new JLabel("");
        this.stateLabel.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        this.stateLabel.setPreferredSize(new Dimension(120, 10));

        this.icon       = new AngledLinesWindowsCornerIcon();
        this.iconLabel  = new JLabel(this.icon);

        bottomPanel     = new JPanel(new BorderLayout());
        bottomPanel.add(this.iconLabel, BorderLayout.SOUTH);
        bottomPanel.setOpaque(false);

        rightPanel      = new JPanel(new BorderLayout());
        rightPanel.add(bottomPanel, BorderLayout.EAST);
        rightPanel.add(this.stateLabel, BorderLayout.WEST);
        rightPanel.setOpaque(false);

        this.textLabel  = new JLabel("");
        add(rightPanel, BorderLayout.EAST);
        //add(this.stateLabel, BorderLayout.WEST);
        add(this.textLabel, BorderLayout.CENTER);
    }

    public void setState(String state) {
        this.stateLabel.setText(state);
    }


    public void setText(String text) {
        this.textLabel.setText(text);
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
