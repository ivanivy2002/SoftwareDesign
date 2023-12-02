package org.example.command.history;

import lombok.Getter;
import lombok.Setter;
import org.example.Editor;
import org.example.utils.ConsoleTool;
import org.example.utils.StringSet;
import org.example.utils.StringTool;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class HistoryTable implements Serializable {

    protected final Editor editor;
    //    protected final LinkedHashMap<String, String[]> historyMap;
    protected final Stack<HistoryMap> historyMap;
    protected final Stack<HistoryMap> redoMap;
//    protected final LinkedHashMap<String, String[]> redoMap;
    //    protected List<CommandLog> logs = new ArrayList<>();
    protected Session session;

    protected int unsaved;
    public HistoryTable(Editor editor) {
        this.editor = editor;
//        this.historyMap = new LinkedHashMap<>();
//        this.redoMap = new LinkedHashMap<>();
        this.historyMap = new Stack<>();
        this.redoMap = new Stack<>();
//        this.historyMap.put("init", editor.getLines());
        this.historyMap.push(new HistoryMap("init", editor.getLines()));
        session = new Session(editor.getSubdir());
        unsaved = 0;
    }
    //    private final CommandHistory[] commandHistories;
//    private int index = 0;
    public void push(String commandString) {
        if (StringSet.EssenceSet.contains(StringTool.getCommandName(commandString))) {
            historyMap.push(new HistoryMap(commandString, editor.getLines()));
            redoMap.clear();
        }
        if (StringSet.UnsaveSet.contains(StringTool.getCommandName(commandString))) {
            unsaved++;
        }
    }

    public int pushLog(String commandString) {
        if (!StringSet.DebugSet.contains(StringTool.getCommandName(commandString))) {
            if (session.push(commandString) != 0) {
                ConsoleTool.println("ERR:[History.pushLog] No session started");
                return -1;
            }
        }
        return 0;
    }

    public String lastKey(LinkedHashMap<String, String[]> map) {
        String lastKey = null;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            lastKey = entry.getKey();
        }
        return lastKey;
    }

    public boolean checkLastCommand() { // check if last command is skip-able
        String lastKey = historyMap.peek().getRawCommand();
        String commandName = StringTool.getCommandName(lastKey);
        return !commandName.equals("save") && !commandName.equals("load") && !commandName.equals("init");
    }

    public void revert() {
        //revert to last status
        redoMap.push(historyMap.pop());
        editor.setLines(historyMap.peek().getLines());
        unsaved--;
    }

    public int recover() {
        //recover according to redoMap
        if (redoMap.isEmpty()) {
            return -1;
        }
        historyMap.push(redoMap.pop());
        editor.setLines(historyMap.peek().getLines());
        unsaved++;
        return 0;
    }

    public void list() {
        ConsoleTool.println("C List");
        int i = 0;
        for (HistoryMap history : historyMap) {
            System.out.println("[C" + (i++) + "] " + history.getRawCommand());
        }
        i = 0;
        ConsoleTool.println("R List");
        for (HistoryMap history : redoMap) {
            System.out.println("[R" + (i++) + "] " + history.getRawCommand());
        }
    }

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

    public boolean checkUnsaved() {
        return unsaved > 0 && checkLastCommand();
    }

}
