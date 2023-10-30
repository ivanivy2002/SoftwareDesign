package org.example.command;

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
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
//        if(args.length==0){
//            System.out.println("no command");
//            return;
//        }
//        if(args.length==1){
//            System.out.println("no args");
//        }
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