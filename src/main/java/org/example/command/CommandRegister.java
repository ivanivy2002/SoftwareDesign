package org.example.command;


import org.example.Editor;
import org.example.command.loadSave.LoadCommand;
import org.example.command.loadSave.SaveCommand;
import org.example.command.observe.DirTreeCommand;
import org.example.command.observe.ListCommand;
import org.example.command.observe.ListTreeCommand;
import org.example.command.update.AppendHeadCommand;
import org.example.command.update.AppendTailCommand;
import org.example.command.update.DeleteCommand;
import org.example.command.update.InsertCommand;

public class CommandRegister {

    public void commandReg(CommandExecutor executor, Editor editor) {
        executor.registerCommand("load", new LoadCommand(editor));
        executor.registerCommand("save", new SaveCommand(editor));
        executor.registerCommand("list", new ListCommand(editor));
        executor.registerCommand("insert", new InsertCommand(editor));
        executor.registerCommand("append-head", new AppendHeadCommand(editor));
        executor.registerCommand("append-tail", new AppendTailCommand(editor));
        executor.registerCommand("delete", new DeleteCommand(editor));
        executor.registerCommand("undo", new UndoCommand(editor));
        executor.registerCommand("redo", new RedoCommand(editor));
        executor.registerCommand("list-tree", new ListTreeCommand(editor));
        executor.registerCommand("dir-tree", new DirTreeCommand(editor));
    }
}
