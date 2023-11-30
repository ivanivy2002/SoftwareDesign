package org.example.workspace;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.HeadParser;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class WorkspaceManager {
    protected Map<String, Workspace> workspaceMap = new HashMap<>();

    protected Workspace cur;

    public WorkspaceManager() {
//        String tmpName = "init";
//        workspaceMap.put(tmpName, new Workspace(tmpName));
//        cur = workspaceMap.get(tmpName);
//        this.updateCur(tmpName);
    }

    public void updateCur(String name) {
        cur = workspaceMap.get(name);
    }

    public int execute(String commandString) {
//        ConsoleTool.printDebug(cur.name);
        if (commandString.trim().isEmpty()) return -1;
        commandString = HeadParser.parseCommandHead(commandString);
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
//        解决load命令
        if (args[0].equals("load")) {
            String tmpName = StringTool.parseFileNameFromExtension(args[1]);
            if (workspaceMap.get(tmpName) == null) {
                workspaceMap.put(tmpName, new Workspace(tmpName));
                this.updateCur(tmpName);
            } else {
                // 重复加载的处理
                ConsoleTool.println("Already loaded");
                this.updateCur(tmpName);
            }
        }
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
}
