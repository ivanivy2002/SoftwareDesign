package org.example.command;

import org.example.command.abstractCommand.ICommand;
import org.example.command.history.HistoryTable;
import org.example.utils.ConsoleTool;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, ICommand> commands;
    HistoryTable historyTable;

    public CommandExecutor(HistoryTable historyTable) {
        this.commands = new HashMap<>();
        this.historyTable = historyTable;
    }
    public void registerCommand(String commandString, ICommand command) {
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
        commands.put(args[0], command);
    }
    public void executeCommand(String commandString) {
        if(commandString.trim().isEmpty())    return;
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        ICommand command = commands.get(args[0]);
        if (command != null) {
            if (command.execute(newArgs) >= 0) {
//                System.out.println("Command executed successfully.");
                historyTable.push(commandString);
            }
            //                System.out.println("Command executed failed.");
        } else {
            ConsoleTool.println("Unknown command: " + commandString);
        }
    }
}