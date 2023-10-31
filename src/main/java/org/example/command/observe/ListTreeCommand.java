package org.example.command.observe;

import org.example.Editor;
import org.example.command.abstractCommand.EditorCommand;
import org.example.utils.ConsoleTool;

public class ListTreeCommand extends EditorCommand {

    public ListTreeCommand(Editor editor) {
        super(editor);
    }
    @Override
    public void execute(String[] newArgs) {
        ConsoleTool.println("Undoing...");
    }
}


