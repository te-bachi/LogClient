package com.oscilloquartz.logclient;

import com.oscilloquartz.logclient.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class GuiMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (InstantiationException ie) {

                } catch (UnsupportedLookAndFeelException ue) {

                } catch (ClassNotFoundException ce) {

                } catch (IllegalAccessException iae) {

                }
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
                if (!frame.setupNetwork()) {
                    frame.close();
                }
            }
        });
    }
}
