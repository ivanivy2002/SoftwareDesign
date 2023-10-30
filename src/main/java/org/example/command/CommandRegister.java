package org.example.command;


import org.example.Editor;

public class CommandRegister {

    public void commandReg(CommandExecutor executor, Editor editor) {
        executor.registerCommand("load", new LoadCommand(editor));
        executor.registerCommand("save", new SaveCommand(editor));
        executor.registerCommand("list", new ListCommand(editor));
        executor.registerCommand("insert", new InsertCommand(editor));
//        executor.registerCommand("append-head", new AppendHeadCommand(editor));
//        executor.registerCommand("append-tail", new AppendTailCommand(editor));
//        executor.registerCommand("delete", new DeleteCommand(editor));
    }
}
