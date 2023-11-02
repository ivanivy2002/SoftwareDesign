package org.example;

import org.example.client.Client;
import org.example.command.*;
import org.example.command.history.HistoryTable;

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
        Editor editor = new Editor();
        HistoryTable historyTable = new HistoryTable(editor);
        CommandExecutor executor = new CommandExecutor(historyTable);
        editor.setSubdir("./data/");
        //注册
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.commandReg(executor, editor, historyTable);
        Client client = new Client();
        executor.executeCommand("load sim.md");
        executor.executeCommand("save");
        executor.executeCommand("list");
        executor.executeCommand("append-head # head");
        executor.executeCommand("append-tail # tail");
//        executor.executeCommand("list");
        executor.executeCommand("insert 3 ## 3 to be deleted");
        executor.executeCommand("delete 3");
        executor.executeCommand("list");
        executor.executeCommand("list-tree");
        executor.executeCommand("dir-tree 四级标题");
//        executor.executeCommand("insert ## end");
//        executor.executeCommand("list");
        client.clientRun(executor);
//        executor.executeCommand("save");

//        executor.executeCommand("unknown");
    }
}