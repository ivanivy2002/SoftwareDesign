package org.example.command.observe;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;

public class ListTreeCommand extends VisitEditorCommand {

    public ListTreeCommand(Editor editor) {
        super(editor);
    }
    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("ListTree ing...");
        return 0;
    }
}


