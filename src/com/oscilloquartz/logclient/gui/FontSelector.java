package com.oscilloquartz.logclient.gui;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class FontSelector {

    private static final String[]   MONOSPACE_FONTS     = new String[] { "Consolas", "DejaVu Sans Mono", "Courier New" };
    private static final int        MONOSPACE_STYLE     = Font.PLAIN;
    private static final int        MONOSPACE_SIZE      = 12;

    private static FontSelector fontSelector = null;

    private Font monospaceFont;

    public static FontSelector getInstance() {
        if (fontSelector == null) {
            fontSelector = new FontSelector();
        }
        return fontSelector;
    }

    private FontSelector() {
        String[]    fonts;
        Set<String> fontSet;

        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontSet = new TreeSet<String>(Arrays.asList(fonts));

        selectMonospaceFont(fontSet);
    }

    private void selectMonospaceFont(Set<String> fontSet) {
        for (String font : MONOSPACE_FONTS) {
            if (fontSet.contains(font)) {
                this.monospaceFont = new Font(font, MONOSPACE_STYLE, LogClientUtil.scale(MONOSPACE_SIZE));
                return;
            }
        }

        this.monospaceFont = new Font(Font.MONOSPACED, MONOSPACE_STYLE, LogClientUtil.scale(MONOSPACE_SIZE));
    }

    public Font getMonospaceFont() {
        return this.monospaceFont;
    }
}
