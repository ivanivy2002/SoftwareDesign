package org.example.command;


import org.example.Editor;
import org.example.command.debug.*;
import org.example.command.history.*;
import org.example.command.loadSave.*;
import org.example.command.observe.*;
import org.example.command.update.*;
import org.example.exit.ExitCommand;

public class CommandRegister {

    public void commandReg(CommandExecutor executor, Editor editor, HistoryTable historyTable) {
        //Load and Save
        executor.registerCommand("load", new LoadCommand(editor));
        executor.registerCommand("save", new SaveCommand(editor));
        executor.registerCommand("reset", new ResetCommand(editor));
        //Update
        executor.registerCommand("insert", new InsertCommand(editor));
        executor.registerCommand("append-head", new AppendHeadCommand(editor));
        executor.registerCommand("append-tail", new AppendTailCommand(editor));
        executor.registerCommand("delete", new DeleteCommand(editor));
        //Undo/Redo and History
        executor.registerCommand("undo", new UndoCommand(historyTable));
        executor.registerCommand("redo", new RedoCommand(historyTable));
        executor.registerCommand("history", new HistoryCommand(historyTable));
        executor.registerCommand("status", new StatusCommand(historyTable));
        //Observer
        executor.registerCommand("list", new ListCommand(editor));
        executor.registerCommand("list-tree", new ListTreeCommand(editor));
        executor.registerCommand("dir-tree", new DirTreeCommand(editor));
        //Debug
        executor.registerCommand("c", new CheckCommand(historyTable));
        //Exit
        executor.registerCommand("exit", new ExitCommand(historyTable));
    }
}
