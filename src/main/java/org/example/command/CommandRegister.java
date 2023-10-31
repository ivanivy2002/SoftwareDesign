package org.example.command;


import org.example.Editor;
import org.example.command.history.HistoryCommand;
import org.example.command.history.HistoryTable;
import org.example.command.history.RedoCommand;
import org.example.command.history.UndoCommand;
import org.example.command.loadSave.LoadCommand;
import org.example.command.loadSave.SaveCommand;
import org.example.command.observe.DirTreeCommand;
import org.example.command.observe.ListCommand;
import org.example.command.observe.ListTreeCommand;
import org.example.command.update.AppendHeadCommandVisit;
import org.example.command.update.AppendTailCommandVisit;
import org.example.command.update.DeleteCommandVisit;
import org.example.command.update.InsertCommandVisit;

public class CommandRegister {

    public void commandReg(CommandExecutor executor, Editor editor, HistoryTable historyTable) {
        //Load and Save
        executor.registerCommand("load", new LoadCommand(editor));
        executor.registerCommand("save", new SaveCommand(editor));
        //Update
        executor.registerCommand("insert", new InsertCommandVisit(editor));
        executor.registerCommand("append-head", new AppendHeadCommandVisit(editor));
        executor.registerCommand("append-tail", new AppendTailCommandVisit(editor));
        executor.registerCommand("delete", new DeleteCommandVisit(editor));
        //Undo/Redo and History
        executor.registerCommand("undo", new UndoCommand(historyTable));
        executor.registerCommand("redo", new RedoCommand(historyTable));
        executor.registerCommand("history", new HistoryCommand(historyTable));
        //Observer
        executor.registerCommand("list", new ListCommand(editor));
        executor.registerCommand("list-tree", new ListTreeCommand(editor));
        executor.registerCommand("dir-tree", new DirTreeCommand(editor));
    }
}
