package org.example.command;

import org.example.command.abstractCommand.ICommand;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, ICommand> commands;

    public CommandExecutor() {
        this.commands = new HashMap<>();
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
            command.execute(newArgs);
        } else {
            System.out.println("Unknown command: " + commandString);
        }
    }
}