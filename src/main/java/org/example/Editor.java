package org.example;

import lombok.Getter;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

@Getter
public class Editor {
    public String[] lines;
    public  String subdir;

    public Editor(String subdir) {
        this.subdir = subdir;
//        if(subdir.isEmpty()){
//            subdir= "./data/";
//        }
    }

    //        System.out.println(fileString);
    public String fileString;
    public void setLines(String[] lines) {
        this.lines = lines;
    }

    public void setSubdir(String subdir) {
        this.subdir = subdir;
    }
    public void printLines() {
        if (lines == null) {
            ConsoleTool.println("ERR: lines is null");
            return;
        }
        ConsoleTool.prints(lines);
    }
    public void setFileString(String fileString) {
        this.fileString = fileString;
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