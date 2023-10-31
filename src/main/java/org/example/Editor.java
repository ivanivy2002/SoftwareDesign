package org.example;

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

//    public void loadFile(String filePath) {
//        // 模拟从文件中加载内容
//        // 在实际应用中，你可以使用适当的方式读取文件内容到 `content` 字段
//        System.out.println("Loading file: " + filePath);
//        lines = "File content loaded from " + filePath;
//    }

//    public void saveFile(String filePath) {
//        // 模拟将内容保存到文件
//        // 在实际应用中，你可以使用适当的方式将 `content` 字段的内容保存到文件
//        System.out.println("Saving file: " + filePath);
//        System.out.println("Content saved: " + content);
//    }
}