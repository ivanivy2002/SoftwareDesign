package org.example;

import org.example.client.Client;
import org.example.command.*;

//public class Main {
//    public static void main(String[] args) {
//        String filePath = "./data/test.md"; // 替换为你的文件路径
//        String[] fileLines = FileReader.readFile(filePath);
//
//        System.out.println("File content:");
//        for (String line : fileLines) {
//            System.out.println(line);
//        }
//
//    }
//}
public class Main {
    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();
        Editor editor = new Editor();
        editor.setSubdir("./data/");
        //注册
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.commandReg(executor, editor);
        Client client = new Client();
        executor.executeCommand("load test.md");
        executor.executeCommand("list");
        executor.executeCommand("append-head ## head");
        executor.executeCommand("append-tail ## tail");
        executor.executeCommand("list");
        executor.executeCommand("save");

//        executor.executeCommand("insert ## end");
//        executor.executeCommand("list");
        client.clientRun(executor);
//        executor.executeCommand("save");

//        executor.executeCommand("unknown");
    }
}