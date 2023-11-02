package org.example.command.history;

import org.example.Editor;
import org.example.utils.ConsoleTool;
import org.example.utils.StringSet;
import org.example.utils.StringTool;
import org.example.utils.TimeTool;

import java.util.*;

public class HistoryTable {
    protected final Editor editor;
    protected final LinkedHashMap<String, String[]> historyMap;

    protected final LinkedHashMap<String, String[]> redoMap;

    protected List<CommandLog> Logs = new ArrayList<>();
    public HistoryTable(Editor editor) {
        this.editor = editor;
        this.historyMap = new LinkedHashMap<>();
        this.redoMap = new LinkedHashMap<>();
        this.historyMap.put("init", editor.getLines());
    }
    //    private final CommandHistory[] commandHistories;
//    private int index = 0;
    public void push(String commandString) {
        if (StringSet.EssenceSet.contains(StringTool.getCommandName(commandString))) {
//    !(name.equals("list") || name.equals("list-tree") || name.equals("dir-tree") ||
//                name.equals("history") || name.equals("redo") || name.equals("undo")  ||
//                name.equals("exit") ||
//                name.equals("h") || name.equals("ls")
//            !name.matches("(?i:list|list-tree|dir-tree|history|redo|undo|exit)")
//            commandHistories.add(new CommandHistory(TimeTool.getCurrentTime(), commandString));
            historyMap.put(commandString, editor.getLines());
//            ConsoleTool.println("pushed");
//            index = commandHistories.size();
        }
    }

    public void pushLog(String commandString) {
        if (!StringSet.DebugSet.contains(StringTool.getCommandName(commandString))) {
            Logs.add(new CommandLog(TimeTool.getCurrentTime(), commandString));
        }
    }

    public String lastKey(LinkedHashMap<String, String[]> map) {
//        return historyMap.entrySet().iterator().next().getKey();
//        Iterator<Map.Entry<CommandHistory, String[]>> iterator = historyMap.entrySet().iterator();
        String lastKey = null;
//        String lastValue = null;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            lastKey = entry.getKey();
//            lastValue = entry.getValue();
        }
        return lastKey;
    }

    public boolean checkLastCommand() {
        String lastKey = this.lastKey(historyMap);
        String commandName = StringTool.getCommandName(lastKey);
        return !commandName.equals("save") && !commandName.equals("load") && !commandName.equals("init");
    }

    public void revert() {
        //revert to last status
        String lastKey = this.lastKey(historyMap);
        redoMap.put(lastKey, historyMap.get(lastKey));
        historyMap.remove(lastKey);
        lastKey = this.lastKey(historyMap);
        editor.setLines(historyMap.get(lastKey));
    }

    public int recover() {
        //recover according to redoMap
        if (redoMap.isEmpty()) {
            return -1;
        }
        String lastKey = this.lastKey(redoMap);
        historyMap.put(lastKey, redoMap.get(lastKey));
        redoMap.remove(lastKey);
        lastKey = this.lastKey(redoMap);
        editor.setLines(redoMap.get(lastKey));
        return 0;
    }



    public void list() {
        ConsoleTool.println("C List");
        int i = 0;
        for (Map.Entry<String, String[]> entry : historyMap.entrySet()) {
            System.out.println("[C" + (i++) + "] " + entry.getKey());
        }
        i = 0;
        ConsoleTool.println("R List");
        for (Map.Entry<String, String[]> entry : redoMap.entrySet()) {
            System.out.println("[R" + (i++) + "] " + entry.getKey());
        }

//        for (String commandString : historyMap.keySet()) {
////            if (commandHistory.commandString.equals("init")) continue;
//            System.out.println(commandString);
//        }
    }

    public void listLog() {
        for (CommandLog commandHistory : Logs) {
            System.out.println(commandHistory.timestamp + " " + commandHistory.commandString);
        }
    }
//    public boolean isEmpty() {
//        return index == 0;
//    }
}
