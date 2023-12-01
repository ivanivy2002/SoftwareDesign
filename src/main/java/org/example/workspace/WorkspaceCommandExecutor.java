package org.example.workspace;

import org.example.command.CommandExecutor;
import org.example.command.abstractCommand.ICommand;
import org.example.utils.ConsoleTool;

import java.util.HashMap;
import java.util.Map;

public class WorkspaceCommandExecutor extends CommandExecutor {
    private final Map<String, ICommand> commands;
//    HistoryTable historyTable;

    public WorkspaceCommandExecutor() {
        this.commands = new HashMap<>();
//        this.historyTable = historyTable;
    }

    public void registerCommand(String commandString, ICommand command) {
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
        commands.put(args[0], command);
    }

    public int executeCommand(String[] args) {
//        if(commandString.trim().isEmpty())    return -1;
//        commandString = HeadParser.parseCommandHead(commandString);
//        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        ICommand command = commands.get(args[0]);
        if (command != null) {
            if (command.execute(newArgs) >= 0) {
//                ConsoleTool.println("Command executed successfully.");
                return 0;
            }
            ConsoleTool.printERR("CommandExecutor.executeCommand", "Command executed failed.");
            return -1;
        } else {
            ConsoleTool.println("Unknown command: " + args[0]);
        }
        return -1;
    }
}