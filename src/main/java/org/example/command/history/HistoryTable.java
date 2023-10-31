package org.example.command.history;

import org.example.utils.TimeTool;

import java.util.ArrayList;
import java.util.List;

public class HistoryTable {
    List<CommandHistory> commandHistories = new ArrayList<>();

    //    private final CommandHistory[] commandHistories;
//    private int index = 0;
    public void push(String commandString) {
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
        if (!(args[0].equals("list") || args[0].equals("list-tree") || args[0].equals("dir-tree") || args[0].equals("history"))) {
            commandHistories.add(new CommandHistory(TimeTool.getCurrentTime(), commandString));
//            index = commandHistories.size();
        }
    }

//    public void pop() {
//        commandHistories.remove(commandHistories.size() - 1);
//    }

    public void list() {
        for (CommandHistory commandHistory : commandHistories) {
            System.out.println(commandHistory.timestamp + " " + commandHistory.commandString);
        }
    }
//    public boolean isEmpty() {
//        return index == 0;
//    }
}
