package org.example.command.abstractCommand;

import org.example.Editor;

public class EditorCommand implements ICommand{
    protected Editor editor;
    public EditorCommand(Editor editor) {
        this.editor=editor;
    }
    @Override
    public void execute(String[] newArgs) {

    }
}
