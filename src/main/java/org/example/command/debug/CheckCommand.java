package org.example.command.debug;

import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.command.history.HistoryTable;

public class CheckCommand extends VisitHistoryCommand {
    public CheckCommand(HistoryTable historyTable) {
        super(historyTable);
    }

    @Override
    public int execute(String[] newArgs) {
//        ConsoleTool.println("Essential History Listing...");
//        historyTable.list();
        historyTable.list();
        return 0;
    }
}


