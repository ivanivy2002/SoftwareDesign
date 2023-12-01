package org.example.command.observe;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;

public class ListCommand extends VisitEditorCommand {

    public ListCommand(Editor editor) {
        super(editor);
    }
    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Listing...");
        editor.printLines();
        return 0;
    }
}


