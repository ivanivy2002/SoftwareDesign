package org.example.command;

import org.example.Editor;

public class ListCommand extends EditorCommand {

    public ListCommand(Editor editor) {
        super(editor);
    }
    @Override
    public void execute(String[] newArgs) {
        editor.printLines();
    }
}


