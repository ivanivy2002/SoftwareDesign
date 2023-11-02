package org.example.command.history;

import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.utils.ConsoleTool;

public class UndoCommand extends VisitHistoryCommand {

    public UndoCommand(HistoryTable historyTable) {
        super(historyTable);
    }

    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Undoing...");
        if (!historyTable.checkLastCommand()) {
            ConsoleTool.println("ERR: Cannot undo");
            return -1;
        }
        historyTable.revert();
        return 0;
    }
}


