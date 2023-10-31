package org.example.command;

import org.example.Editor;
import org.example.command.abstractCommand.EditorCommand;
import org.example.utils.ConsoleTool;

public class RedoCommand extends EditorCommand {

    public RedoCommand(Editor editor) {
        super(editor);
    }
    @Override
    public void execute(String[] newArgs) {
        ConsoleTool.println("Undoing...");
    }
}


