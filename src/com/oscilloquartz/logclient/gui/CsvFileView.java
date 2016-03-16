package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 *
 */
public class CsvFileView extends FileView {

    Icon jarIcon = new ImageIcon("yourFile.gif");

    public String getName(File file) {
        String filename = file.getName();
        if (filename.endsWith(".java")) {
            String name = filename + " : " + file.length();
            return name;
        }
        return null;
    }
    public String getTypeDescription(File file) {
        String typeDescription = null;
        String filename = file.getName().toLowerCase();

        if (filename.endsWith(".java") || filename.endsWith(".class")) {
            typeDescription = "Java Source";
        }
        return typeDescription;
    }

    public Icon getIcon(File file) {
        if (file.isDirectory()) {
            return null;
        }
        Icon icon = null;
        String filename = file.getName().toLowerCase();
        if (filename.endsWith(".java") || filename.endsWith(".class")) {
            icon = jarIcon;
        }
        return icon;
    }
}
