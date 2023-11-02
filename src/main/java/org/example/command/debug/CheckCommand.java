package org.example.command.debug;

import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.command.history.HistoryTable;
import org.example.utils.ConsoleTool;

public class CheckCommand extends VisitHistoryCommand {
    public CheckCommand(HistoryTable historyTable) {
        super(historyTable);
    }

    @Override
    public int execute(String[] newArgs) {
//        ConsoleTool.println("Essential History Listing...");
//        historyTable.list();
        if (newArgs.length == 0) {
            historyTable.list();
            return 0;
        } else {
            int index = Integer.parseInt(newArgs[1]);
            if (newArgs[0].equals("c")) {
                ConsoleTool.println("HistoryMap " + index);
                historyTable.showHistoryMap(index);
                return 0;
            }
            if (newArgs[0].equals("r")) {
                ConsoleTool.println("RedoMap " + index);
                historyTable.showRedoMap(index);
                return 0;
            }
        }
        return 0;
    }
}


