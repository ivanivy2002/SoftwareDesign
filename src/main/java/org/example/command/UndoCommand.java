package org.example.command;

import org.example.Editor;
import org.example.command.abstractCommand.EditorCommand;
import org.example.utils.ConsoleTool;

public class UndoCommand extends EditorCommand {

    public UndoCommand(Editor editor) {
        super(editor);
    }
    @Override
    public void execute(String[] newArgs) {
        ConsoleTool.println("Undoing...");
    }
}


