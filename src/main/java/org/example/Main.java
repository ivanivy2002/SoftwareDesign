package org.example;

import org.example.client.Client;
import org.example.command.*;
import org.example.command.history.HistoryTable;
import org.example.utils.FileAccessor;
import org.example.workspace.WorkspaceManager;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.clientRun();
//        test1();
//        test0();
//        test2();
//        test3();
//        test4();
//        testHakuro();
    }

    private static void test0() {
        Client client = new Client();
        WorkspaceManager workspaceManager = new WorkspaceManager();
        workspaceManager.execute("load sim.md");
        workspaceManager.execute("save");
        workspaceManager.execute("list");
        workspaceManager.execute("append-head # head");
        workspaceManager.execute("append-tail # tail");
//        workspaceManager.execute("list");
        workspaceManager.execute("insert 3 ## TO BE INSERTED");
        workspaceManager.execute("delete TO BE DELETED");
        workspaceManager.execute("list");
        workspaceManager.execute("list-tree");
        workspaceManager.execute("dir-tree 四级标题");

    }

    public static void test1() {
        Client client = new Client();
        WorkspaceManager workspaceManager = new WorkspaceManager();
//        client.initWorkspaceManager();
        workspaceManager.execute("load test1.md");
        workspaceManager.execute("insert ## 程序设计");
        workspaceManager.execute("append-head # 我的资源");
        workspaceManager.execute("append-tail ### 软件设计");
        workspaceManager.execute("append-tail #### 设计模式");
        workspaceManager.execute("append-tail 1. 观察者模式");
        workspaceManager.execute("append-tail 3. 单例模式");
        workspaceManager.execute("insert 6 2. 策略模式");
        workspaceManager.execute("delete 单例模式");
        workspaceManager.execute("append-tail 3. 组合模式");
        workspaceManager.execute("list-tree");
        workspaceManager.execute("append-tail ## 工具箱");
        workspaceManager.execute("append-tail ### Adobe");
        workspaceManager.execute("list-tree");
        workspaceManager.execute("save");
    }

    public static void test2() {
        Client client = new Client();
        FileAccessor.clearFile("./data/test2.md");
        client.execute("save");
        client.execute("load test2.md");
        client.execute("append-head # 旅行清单");
        client.execute("append-tail ## 亚洲");
        client.execute("append-tail 1. 中国");
        client.execute("append-tail 2. 日本");
        client.execute("delete 亚洲");
        client.execute("undo");
        client.execute("redo");
        client.execute("list-tree");
        client.execute("save");
        client.clientRun();
    }

    public static void test3() {
        Client client = new Client();
        WorkspaceManager workspaceManager = new WorkspaceManager();
//        client.initWorkspaceManager();
//        workspaceManager.execute("reset test3.md");
        FileAccessor.clearFile("./data/test2.md");
        workspaceManager.execute("load test3.md");
        workspaceManager.execute("append-head # 书籍推荐");
        workspaceManager.execute("append-tail * 《深入理解计算机系统》");
        workspaceManager.execute("undo");
        workspaceManager.execute("append-tail ## 编程");
        workspaceManager.execute("append-tail * 《设计模式的艺术》");
        workspaceManager.execute("redo");
        workspaceManager.execute("list-tree");
        workspaceManager.execute("append-tail * 《云原生：运用容器、函数计算和数据构建下一代应用》");
        workspaceManager.execute("append-tail * 《深入理解Java虚拟机》");
        workspaceManager.execute("undo");
        workspaceManager.execute("redo");
        workspaceManager.execute("list-tree");
        workspaceManager.execute("save");
//        client.clientRun(executor);
//        workspaceManager.execute("list-tree");
//        workspaceManager.execute("save");
    }

    public static void test4() {
        Client client = new Client();
//        client.initWorkspaceManager();
//        workspaceManager.execute("reset test3.md");
//        workspaceManager.execute("reset test4.md");
        FileAccessor.clearFile("./data/test2.md");
        client.execute("load test4.md");
        client.execute("append-head # 旅行清单");
        client.execute("append-tail ## 亚洲");
        client.execute("save");
        client.execute("append-tail 1. 中国");
        client.execute("append-tail 2. 日本");
        client.execute("append-tail ## 欧洲");
        client.execute("load test3.md");
        client.execute("list-tree");
        client.execute("load test4.md");
        client.execute("list-tree");
        client.clientRun();
//        workspaceManager.execute("list-tree");
//        workspaceManager.execute("save");
    }

    public static void testHakuro() {
        Client client = new Client();
        WorkspaceManager workspaceManager = new WorkspaceManager();
//        client.initWorkspaceManager();
        workspaceManager.execute("hakuro");
        workspaceManager.execute("help");
        client.clientRun();
    }

}