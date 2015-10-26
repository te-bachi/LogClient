package com.oscilloquartz.logclient;

import com.oscilloquartz.logclient.gui.MainFrame;

import java.awt.*;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
                if (!frame.setupNetwork()) {
                    frame.close();
                }
            }
        });
    }
}
