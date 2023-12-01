package org.example.workspace;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.utils.HeadParser;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class WorkspaceManager {
    protected Map<String, Workspace> workspaceMap = new HashMap<>();

    protected Workspace cur = null;
    public void updateCur(String name) {
        if (cur != null) cur.setActive(false);
        cur = workspaceMap.get(name);
        cur.setActive(true);
    }
    public int execute(String commandString) {
//        ConsoleTool.printDebug(cur.name);
        if (commandString.trim().isEmpty()) return -1;
        commandString = HeadParser.parseCommandHead(commandString);
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
//        解决超级命令
        if (this.superCommandProcess(args) == 0) return 0;
//        其他命令或者load的后部分(加载文件)
        if (cur == null) {
            ConsoleTool.printERR("WorkspaceManager.execute", "Please create a workspace first");
            return -1;
        }
        if (cur.executor.executeCommand(args) == 0) {
            if (cur.historyTable.pushLog(commandString) != 0) {
                ConsoleTool.printERR("CommandExecutor.executeCommand", "No session started");
                return -1;
            }
            cur.historyTable.push(commandString);
        }
        return 0;
    }

    private int superCommandProcess(String[] args) {
        if (args[0].equals("load")) {
            this.loadProcess(args[1]);
            // 还需要继续执行后面的命令
            return 1;
        }
        if (args[0].equals("list-workspace")) {
            this.listWorkspaceProcess();
            return 0;
        }
        if (args[0].equals("open")) {
            this.updateCur(args[1]);
            return 0;
        }
        if (args[0].equals("exit")) {
            this.exitProcess();
            return 0; //! theoretically unreachable
        }
        if (args[0].equals("exit-slowly")) {
            this.exitSlowlyProcess();
            return 0;
        }
        if (args[0].equals("close")) {
            if (args.length > 1) {
                return this.closeProcess(args[1]);
            }
            if (cur.unsaved()) {
                ConsoleTool.println("Do you want to save the unsaved workspace [Y\\N]? ");
                System.out.print("$ ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    cur.executor.executeCommand(new String[]{"save"});
                    this.getWorkspaceMap().remove(cur.name);
                    cur = null;
                    return 0;
                } else if (input.equalsIgnoreCase("N")) {
                    // do nothing
                    this.getWorkspaceMap().remove(cur.name);
                    cur = null;
                    return 0;
                } else {
                    ConsoleTool.println("Invalid input");
                    return -1;
                }
            }
        }
        return -1;
    }

    private void exitSlowlyProcess() {
        if (this.checkAllWorkspaceUnsaved()) {
            for (String key : workspaceMap.keySet()) {
                Workspace tmp = workspaceMap.get(key);
                if (tmp.unsaved()) {
                    tmp.executor.executeCommand(new String[]{"save"});
                    tmp.setActive(false);
                }
            }
        }
    }

    private void exitProcess() {
        if (this.checkAllWorkspaceUnsaved()) {
            ConsoleTool.println("Do you want to save all unsaved workspace [Y\\N]? ");
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                for (String key : workspaceMap.keySet()) {
                    Workspace tmp = workspaceMap.get(key);
                    if (tmp.unsaved()) {
                        tmp.executor.executeCommand(new String[]{"save"});
//                        tmp.setActive(false);
                    }
                }
                System.exit(0);
            } else if (input.equalsIgnoreCase("N")) {
                System.exit(0);
            } else {
                ConsoleTool.println("Invalid input");
            }
        } else {
            System.exit(0);
        }
    }

    private int closeProcess(String ans) {
        if (ans.equalsIgnoreCase("Y")) {
            if (cur.unsaved()) {
                cur.executor.executeCommand(new String[]{"save"});
            }
            this.getWorkspaceMap().remove(cur.name);
            cur = null;
            return 0;
        } else if (ans.equalsIgnoreCase("N")) {
            this.getWorkspaceMap().remove(cur.name);
            cur = null;
            return 0;
        } else {
            ConsoleTool.println("Invalid input");
            return -1;
        }
    }

    private void loadProcess(String arg1) {
        String tmpName = StringTool.parseFileNameFromExtension(arg1);
        if (workspaceMap.get(tmpName) == null) {
            workspaceMap.put(tmpName, new Workspace(tmpName));
        } else {
            // 重复加载的处理
            ConsoleTool.println("Already loaded");
        }
        this.updateCur(tmpName);
    }

    private void listWorkspaceProcess() {
        ConsoleTool.println("Workspaces:");
        for (String key : workspaceMap.keySet()) {
            // NOTE: 2023/12/01 是判断名字相等还是判断active好呢? 目前是判断active更好，因为cur可能为空
            Workspace tmp = workspaceMap.get(key);
            if (tmp.isActive()) { //key.equals(cur.name)
                if (tmp.unsaved()) System.out.println("->" + key + " *");
                else System.out.println("->" + key);
            } else {
                if (tmp.unsaved()) System.out.println(key + " *");
                else System.out.println(key);
            }
        }
    }

    private boolean checkAllWorkspaceUnsaved() {
        for (String key : workspaceMap.keySet()) {
            Workspace tmp = workspaceMap.get(key);
            if (tmp.unsaved()) {
                return true;
            }
        }
        return false;
    }
}
