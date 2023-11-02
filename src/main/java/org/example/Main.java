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
//        test1();
//        test0();
//        test2();
//        test3();
        test4();
    }

    private static void test0() {
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
        executor.executeCommand("insert 3 ## TO BE INSERTED");
        executor.executeCommand("delete TO BE DELETED");
        executor.executeCommand("list");
        executor.executeCommand("list-tree");
        executor.executeCommand("dir-tree 四级标题");
//        executor.executeCommand("insert ## end");
//        executor.executeCommand("list");
        client.clientRun(executor);
//        executor.executeCommand("save");
//        executor.executeCommand("unknown");
    }

    public static void test1() {
        Editor editor = new Editor();
        HistoryTable historyTable = new HistoryTable(editor);
        CommandExecutor executor = new CommandExecutor(historyTable);
        editor.setSubdir("./data/");
        //注册
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.commandReg(executor, editor, historyTable);
        Client client = new Client();
        executor.executeCommand("load test1.md");
        executor.executeCommand("insert ## 程序设计");
        executor.executeCommand("append-head # 我的资源");
        executor.executeCommand("append-tail ### 软件设计");
        executor.executeCommand("append-tail #### 设计模式");
        executor.executeCommand("append-tail 1. 观察者模式");
        executor.executeCommand("append-tail 3. 单例模式");
        executor.executeCommand("insert 6 2. 策略模式");
        executor.executeCommand("delete 单例模式");
        executor.executeCommand("append-tail 3. 组合模式");
        executor.executeCommand("list-tree");
        executor.executeCommand("append-tail ## 工具箱");
        executor.executeCommand("append-tail ### Adobe");
        executor.executeCommand("list-tree");
        executor.executeCommand("save");
    }

    public static void test2() {
        Editor editor = new Editor();
        HistoryTable historyTable = new HistoryTable(editor);
        CommandExecutor executor = new CommandExecutor(historyTable);
        editor.setSubdir("./data/");
        //注册
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.commandReg(executor, editor, historyTable);
        Client client = new Client();
        executor.executeCommand("reset test2.md");
//        executor.executeCommand("list-tree");

        executor.executeCommand("load test2.md");
        executor.executeCommand("append-head # 旅行清单");
        executor.executeCommand("append-tail ## 亚洲");
        executor.executeCommand("append-tail 1. 中国");
        executor.executeCommand("append-tail 2. 日本");
        executor.executeCommand("delete 亚洲");
        executor.executeCommand("undo");
        executor.executeCommand("redo");
        executor.executeCommand("list-tree");
        executor.executeCommand("save");
        client.clientRun(executor);
    }

    public static void test3() {
        Editor editor = new Editor();
        HistoryTable historyTable = new HistoryTable(editor);
        CommandExecutor executor = new CommandExecutor(historyTable);
        editor.setSubdir("./data/");
        //注册
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.commandReg(executor, editor, historyTable);
        Client client = new Client();
        executor.executeCommand("reset test3.md");

        executor.executeCommand("load test3.md");
        executor.executeCommand("append-head # 书籍推荐");
        executor.executeCommand("append-tail * 《深入理解计算机系统》");
        executor.executeCommand("undo");
        executor.executeCommand("append-tail ## 编程");
        executor.executeCommand("append-tail * 《设计模式的艺术》");
        executor.executeCommand("redo");
        executor.executeCommand("list-tree");
        executor.executeCommand("append-tail * 《云原生：运用容器、函数计算和数据构建下一代应用》");
        executor.executeCommand("append-tail * 《深入理解Java虚拟机》");
        executor.executeCommand("undo");
        executor.executeCommand("redo");
        executor.executeCommand("list-tree");
        executor.executeCommand("save");

        client.clientRun(executor);
//        executor.executeCommand("list-tree");
//        executor.executeCommand("save");
    }

    public static void test4() {
        Editor editor = new Editor();
        HistoryTable historyTable = new HistoryTable(editor);
        CommandExecutor executor = new CommandExecutor(historyTable);
        editor.setSubdir("./data/");
        //注册
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.commandReg(executor, editor, historyTable);
        Client client = new Client();
//        executor.executeCommand("reset test3.md");
        executor.executeCommand("reset test4.md");

        executor.executeCommand("load test4.md");
        executor.executeCommand("append-head # 旅行清单");
        executor.executeCommand("append-tail ## 亚洲");
        executor.executeCommand("save");
        executor.executeCommand("append-tail 1. 中国");
        executor.executeCommand("append-tail 2. 日本");
        executor.executeCommand("append-tail ## 欧洲");
        executor.executeCommand("load test3.md");
        executor.executeCommand("list-tree");
        executor.executeCommand("load test4.md");
        executor.executeCommand("list-tree");
        client.clientRun(executor);
//        executor.executeCommand("list-tree");
//        executor.executeCommand("save");
    }
}