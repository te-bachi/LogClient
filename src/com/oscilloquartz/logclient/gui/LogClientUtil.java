package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;

public class LogClientUtil {

    private static final boolean HIDPI_MODE = true;
    private static final double HIDPI_SCALE_FACTOR = 2.0;

    /**
     * Provide info, whether we run in HiDPI mode
     * @return {@code true} if we run in HiDPI mode, {@code false} otherwise
     */
    public static boolean getHiDPIMode() {
        return HIDPI_MODE;
    }

    /**
     * Provide info about the HiDPI scale factor
     * @return the factor by which we should scale elements for HiDPI mode
     */
    public static double getHiDPIScaleFactor() {
        return HIDPI_SCALE_FACTOR;
    }
    public static int scale(int len) {
        return (int) (len * getHiDPIScaleFactor());
    }

    /**
     * Apply HiDPI mode management to {@link JTable}
     * @param table the {@link JTable} which should be adapted for HiDPI mode
     */
    public static void applyHiDPI(JTable table) {
        if (LogClientUtil.getHiDPIMode()) {
            table.setRowHeight((int) Math.round(table.getRowHeight() * LogClientUtil.getHiDPIScaleFactor()));

        }
    }

    /**
     * Apply HiDPI scale factor on font if HiDPI mode is enabled
     */
    public static void applyHiDPIOnFonts() {
        if (!getHiDPIMode()) {
            return;
        }
        applyScaleOnFonts((float) getHiDPIScaleFactor());
    }

    /**
     * Apply HiDPI scale factor on fonts
     * @param scale float scale to apply
     */
    public static void applyScaleOnFonts(final float scale) {
        //SwingUtilities.invokeLater(() -> {
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
            // If I iterate over the entrySet under ubuntu with jre 1.8.0_121
            // the font objects are missing, so iterate over the keys, only
            for (Object key : new ArrayList<>(defaults.keySet())) {
                Object value = defaults.get(key);
                if (value instanceof Font) {
                    Font font = (Font) value;
                    final float newSize = font.getSize() * scale;
                    if (font instanceof FontUIResource) {
                        defaults.put(key, new FontUIResource(font.getName(),
                                font.getStyle(), Math.round(newSize)));
                    } else {
                        defaults.put(key, font.deriveFont(newSize));
                    }
                }
            }
            refreshUI();
        //});
    }

    public static final void refreshUI() {
        for (Window w : Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(w);
            if (w.isDisplayable() &&
                    (w instanceof Frame ? !((Frame)w).isResizable() :
                            w instanceof Dialog ? !((Dialog)w).isResizable() :
                                    true)) {
                w.pack();
            }
        }
    }
}
