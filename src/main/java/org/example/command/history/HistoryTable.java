package org.example.command.history;

import org.example.Editor;
import org.example.utils.ConsoleTool;
import org.example.utils.StringSet;
import org.example.utils.StringTool;

import java.io.IOException;
import java.util.*;

public class HistoryTable {

    protected final Editor editor;
    //    protected final LinkedHashMap<String, String[]> historyMap;
    protected final Stack<HistoryMap> historyMap;
    protected final Stack<HistoryMap> redoMap;
//    protected final LinkedHashMap<String, String[]> redoMap;

    //    protected List<CommandLog> logs = new ArrayList<>();
    protected Session session;
    public HistoryTable(Editor editor) {
        this.editor = editor;
//        this.historyMap = new LinkedHashMap<>();
//        this.redoMap = new LinkedHashMap<>();
        this.historyMap = new Stack<>();
        this.redoMap = new Stack<>();
//        this.historyMap.put("init", editor.getLines());
        this.historyMap.push(new HistoryMap("init", editor.getLines()));
        session = new Session(editor.getSubdir());
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
//            historyMap.put(commandString, editor.getLines());
            historyMap.push(new HistoryMap(commandString, editor.getLines()));
            redoMap.clear();
//            ConsoleTool.println("pushed");
//            index = commandHistories.size();
        }
    }

    public void pushLog(String commandString) {
        if (!StringSet.DebugSet.contains(StringTool.getCommandName(commandString))) {
            session.push(commandString);
        }
    }

    public String lastKey(LinkedHashMap<String, String[]> map) {
        String lastKey = null;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            lastKey = entry.getKey();
        }
        return lastKey;
    }

    public boolean checkLastCommand() {
//        String lastKey = this.lastKey(historyMap);
        String lastKey = historyMap.peek().getRawCommand();
        String commandName = StringTool.getCommandName(lastKey);
        return !commandName.equals("save") && !commandName.equals("load") && !commandName.equals("init");
    }

    public void revert() {
        //revert to last status
//        String lastKey = this.lastKey(historyMap);
//        redoMap.put(lastKey, historyMap.get(lastKey));
//        historyMap.remove(lastKey);
        redoMap.push(historyMap.pop());
//        lastKey = this.lastKey(historyMap);
//        editor.setLines(historyMap.get(lastKey));
        editor.setLines(historyMap.peek().getLines());
    }

    public int recover() {
        //recover according to redoMap
        if (redoMap.isEmpty()) {
            return -1;
        }
//        String lastKey = this.lastKey(redoMap);
//        historyMap.put(lastKey, redoMap.get(lastKey));
//        redoMap.remove(lastKey);
        historyMap.push(redoMap.pop());
//        if (redoMap.isEmpty()) {
////            lastKey = this.lastKey(historyMap);
////            editor.setLines(historyMap.get(lastKey));
//            editor.setLines(historyMap.peek().getLines());
//        } else {
////            lastKey = this.lastKey(redoMap);
////            editor.setLines(redoMap.get(lastKey));
//            editor.setLines(redoMap.peek().getLines());
//        }
        editor.setLines(historyMap.peek().getLines());
        return 0;
    }

    public void list() {
        ConsoleTool.println("C List");
        int i = 0;
//        for (Map.Entry<String, String[]> entry : historyMap.entrySet()) {
//            System.out.println("[C" + (i++) + "] " + entry.getKey());
//        }
        for (HistoryMap history : historyMap) {
            System.out.println("[C" + (i++) + "] " + history.getRawCommand());
        }
        i = 0;
        ConsoleTool.println("R List");
//        for (Map.Entry<String, String[]> entry : redoMap.entrySet()) {
//            System.out.println("[R" + (i++) + "] " + entry.getKey());
//        }
        for (HistoryMap history : redoMap) {
            System.out.println("[R" + (i++) + "] " + history.getRawCommand());
        }
    }

    //    public static <K, V> V getElementAtIndex(LinkedHashMap<K, V> map, int index) {
//        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
//        int count = 0;
//        while (iterator.hasNext()) {
//            Map.Entry<K, V> entry = iterator.next();
//            if (count == index) {
//                return entry.getValue();
//            }
//            count++;
//        }
//        throw new IndexOutOfBoundsException("Index out of range: " + index);
//    }
//
    public static HistoryMap getElementAtIndex(Stack<HistoryMap> map, int index) {
        return map.get(index);
    }

    public void showHistoryMap(int index) {
//        for (String ele : getElementAtIndex(historyMap, index)) {
//            System.out.println(ele);
//        }
        ConsoleTool.prints(historyMap.get(index).getLines());
    }

    public void showRedoMap(int index) {
//        for (String ele : getElementAtIndex(redoMap, index)) {
//            System.out.println(ele);
//        }
        ConsoleTool.prints(redoMap.get(index).getLines());
    }

    public void listLog() {
        session.listLog();
    }

    public void listLogRecent(int num) {
        int size = session.getLogs().size();
        if (num > size) {
            num = size;
        }
        for (int i = size - 1 - num + 1; i <= size - 1; i++) {
            System.out.println(session.getLogs().get(i).timestamp + " " + session.getLogs().get(i).commandString);
        }
    }

    public void statusCurrent() {
        session.printStatus();
    }

    public void statusAll() {
        session.printAll();
    }

    public void saveLog() {
        session.saveLog();
    }
//    public boolean isEmpty() {
//        return index == 0;
//    }
}
