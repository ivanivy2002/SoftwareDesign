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
        if (newArgs.length != 0) {
            ConsoleTool.println("Recent History " + newArgs[0] + " or all");
            historyTable.listLogRecent(Integer.parseInt(newArgs[0]));
        } else {
            ConsoleTool.println("History Listing...");
            historyTable.listLog();
        }
        return 0;
    }
}


