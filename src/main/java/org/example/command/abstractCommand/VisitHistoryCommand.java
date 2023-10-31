package org.example.command.abstractCommand;

import org.example.Editor;
import org.example.command.history.HistoryTable;

public class VisitHistoryCommand implements ICommand {
    //    protected Editor editor;
    protected HistoryTable historyTable;

    public VisitHistoryCommand(HistoryTable historyTable) {
//        this.editor = editor;
        this.historyTable = historyTable;
    }

    @Override
    public int execute(String[] newArgs) {
        return 0;
    }
}
