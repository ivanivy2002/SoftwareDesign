package org.example.command.abstractCommand;

import org.example.Editor;

public class VisitEditorCommand implements ICommand {
    protected Editor editor;

    public VisitEditorCommand(Editor editor) {
        this.editor=editor;
    }
    @Override
    public int execute(String[] newArgs) {
        return 0;
    }
}
