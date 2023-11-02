package org.example.command.history;

import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.utils.ConsoleTool;

public class StatusCommand extends VisitHistoryCommand {
    public StatusCommand(HistoryTable historyTable) {
        super(historyTable);
    }

    @Override
    public int execute(String[] newArgs) {
        if (newArgs.length == 0 || newArgs[0].equals("all")) {
            ConsoleTool.println("All Status...");
            historyTable.statusAll();
            return 0;
        }
        if (newArgs[0].equals("current")) {
            ConsoleTool.println("Current Status...");
            historyTable.statusCurrent();
        }
        return 0;
    }
}


