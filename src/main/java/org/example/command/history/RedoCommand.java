package org.example.command.history;

import org.example.Editor;
import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.utils.ConsoleTool;

public class RedoCommand extends VisitHistoryCommand {

    public RedoCommand(HistoryTable historyTable) {
        super(historyTable);
    }

    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Redoing...");
        if (historyTable.recover() < 0) {
            ConsoleTool.println("ERR: Cannot redo");
            return -1;
        }
        return 0;
    }
}


