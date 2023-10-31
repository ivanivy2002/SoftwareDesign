package org.example.command.history;

import org.example.Editor;
import org.example.utils.TimeTool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HistoryTable {
    protected final Editor editor;
    protected final LinkedHashMap<CommandHistory, String[]> historyMap;

    //    List<CommandHistory> commandHistories = new ArrayList<>();
    public HistoryTable(Editor editor) {
        this.editor = editor;
        this.historyMap = new LinkedHashMap<>();
        this.historyMap.put(new CommandHistory(TimeTool.getCurrentTime(), "init"), editor.getLines());
    }
    //    private final CommandHistory[] commandHistories;
//    private int index = 0;
    public void push(String commandString) {
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
        if (!(args[0].equals("list") || args[0].equals("list-tree") || args[0].equals("dir-tree") ||
                args[0].equals("history") || args[0].equals("redo") || args[0].equals("undo") || args[0].equals("exit"))) {
//            commandHistories.add(new CommandHistory(TimeTool.getCurrentTime(), commandString));
            historyMap.put(new CommandHistory(TimeTool.getCurrentTime(), commandString), editor.getLines());
//            index = commandHistories.size();
        }
    }

    public CommandHistory lastKey() {
//        return historyMap.entrySet().iterator().next().getKey();
//        Iterator<Map.Entry<CommandHistory, String[]>> iterator = historyMap.entrySet().iterator();
        CommandHistory lastKey = null;
//        String lastValue = null;
        for (Map.Entry<CommandHistory, String[]> entry : historyMap.entrySet()) {
            lastKey = entry.getKey();
//            lastValue = entry.getValue();
        }
        return lastKey;
    }

    public void pop() {
//        historyMap.remove(historyMap.size() - 1);
//        commandHistories.remove(commandHistories.size() - 1);
        historyMap.remove(this.lastKey());
    }

    public void list() {
        for (CommandHistory commandHistory : historyMap.keySet()) {
            if (commandHistory.commandString.equals("init")) continue;
            System.out.println(commandHistory.timestamp + " " + commandHistory.commandString);
        }
//        for (CommandHistory commandHistory : commandHistories) {
//            System.out.println(commandHistory.timestamp + " " + commandHistory.commandString);
//        }
    }
//    public boolean isEmpty() {
//        return index == 0;
//    }
}
