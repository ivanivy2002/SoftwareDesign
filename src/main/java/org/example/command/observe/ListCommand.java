package org.example.command.observe;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;

public class ListCommand extends VisitEditorCommand {

    public ListCommand(Editor editor) {
        super(editor);
    }
    @Override
    public int execute(String[] newArgs) {
        editor.printLines();
        return 0;
    }
}


