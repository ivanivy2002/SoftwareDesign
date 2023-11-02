package org.example;

import org.example.utils.ConsoleTool;

public class Editor {
    public String[] lines;
    public  String subdir;
    public String fileString;
    public String[] getLines() {
        return lines;
    }
    public void setLines(String[] lines) {
        this.lines = lines;
    }
    public String getSubdir() {
        return subdir;
    }
    public void setSubdir(String subdir) {
        this.subdir = subdir;
    }
    public void printLines() {
        for (int i=0;i< lines.length;i++) {
            System.out.println("[" + (i + 1) + "] " + lines[i]);
        }
    }
    public void setFileString(String fileString) {
        this.fileString = fileString;
    }
    public String getFileString() {
//        System.out.println(fileString);
        return fileString;
    }
}