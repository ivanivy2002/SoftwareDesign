package org.example.command.history;

import org.example.Editor;
import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.utils.ConsoleTool;

public class UndoCommand extends VisitHistoryCommand {

    public UndoCommand(HistoryTable historyTable) {
        super(historyTable);
    }

    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Undoing...");
        CommandHistory lastKey = historyTable.lastKey();
        if (lastKey.commandString.equals("save") || lastKey.commandString.split(" ")[0].equals("load")) {
            ConsoleTool.println("ERR: Cannot undo save or load command.");
            return -1;
        }
        historyTable.pop();
        historyTable.editor.setLines(historyTable.historyMap.get(lastKey));
        return 0;
    }
}


