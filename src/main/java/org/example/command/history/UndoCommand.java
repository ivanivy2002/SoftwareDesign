package org.example.command.history;

import org.example.Editor;
import org.example.command.abstractCommand.VisitHistoryCommand;
import org.example.utils.ConsoleTool;

public class UndoCommand extends VisitHistoryCommand {

    public UndoCommand(Editor editor, HistoryTable historyTable) {
        super(editor, historyTable);
    }

    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Undoing...");
        return 0;
    }
}


