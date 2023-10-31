package org.example.command.observe;

import org.example.Editor;
import org.example.command.abstractCommand.EditorCommand;

public class ListCommand extends EditorCommand {

    public ListCommand(Editor editor) {
        super(editor);
    }
    @Override
    public void execute(String[] newArgs) {
        editor.printLines();
    }
}


