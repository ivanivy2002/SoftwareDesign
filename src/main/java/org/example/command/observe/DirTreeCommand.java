package org.example.command.observe;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;

public class DirTreeCommand extends VisitEditorCommand {

    public DirTreeCommand(Editor editor) {
        super(editor);
    }
    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("DirTree ing...");
        return 0;
    }
}


