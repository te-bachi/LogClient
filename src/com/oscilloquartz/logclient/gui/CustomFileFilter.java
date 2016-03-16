package com.oscilloquartz.logclient.gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 *
 */
public class CustomFileFilter extends FileFilter {

    private String extension;
    private String description;

    public CustomFileFilter(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

    public boolean accept(File f) {
        String extension = parseExtension(f);
        if (f.isDirectory()) {
            return true;
        }
        if (extension.equalsIgnoreCase(this.extension)) {
            return true;
        }

        return false;
    }

    private String parseExtension(File f) {
        int dot = f.getAbsolutePath().lastIndexOf('.');
        return f.getAbsolutePath().substring(dot + 1);
    }

    public String getDescription() {
        return description;
    }

    public String getExtension() {
        return this.extension;
    }
}
