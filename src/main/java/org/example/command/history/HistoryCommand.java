package org.example.command.history;

import org.example.Editor;
import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.utils.ConsoleTool;

public class HistoryCommand extends VisitHistoryCommand {
    public HistoryCommand(HistoryTable historyTable) {
        super(historyTable);
    }
    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("History Listing...");
        historyTable.list();
        return 0;
    }
}


