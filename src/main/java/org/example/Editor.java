package org.example;

import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

public class Editor {
    public String[] lines;
    public  String subdir;

    Editor(String subdir) {
        this.subdir = subdir;
    }
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
//        for (int i=0;i< lines.length;i++) {
//            System.out.println("[" + (i + 1) + "] " + lines[i]);
//        }
        ConsoleTool.prints(lines);
    }
    public void setFileString(String fileString) {
        this.fileString = fileString;
    }
    public String getFileString() {
//        System.out.println(fileString);
        return fileString;
    }

    public int insert(int lineNum, String lineContent) {
        if (lineNum == -1) {
            lineNum = lines.length;
            ConsoleTool.println("Default insert to the end");
        }
        if (lineContent.split("\\s+", 2).length != 2) {
            ConsoleTool.println("ERR: lineContent without Marking Symbol");
            return -1;
        }
        if (lineNum > lines.length + 1) {
            ConsoleTool.println("ERR: lineNum out of range");
            return -1;
        }
        lines = StringTool.insertLine(lines, lineNum, lineContent);
        return 0;
    }
}