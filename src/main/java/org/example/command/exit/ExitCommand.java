package org.example.command.exit;

import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.command.history.HistoryTable;
import org.example.utils.ConsoleTool;

import static java.lang.System.exit;

public class ExitCommand extends VisitHistoryCommand {
    public ExitCommand(HistoryTable historyTable) {
        super(historyTable);
    }

    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Exiting...");
        historyTable.saveLog();
        exit(0);
        return 0;
    }
}


