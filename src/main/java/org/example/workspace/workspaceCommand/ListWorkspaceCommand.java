package org.example.workspace.workspaceCommand;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;

public class ListWorkspaceCommand extends VisitEditorCommand {

    public ListWorkspaceCommand(Editor editor) {
        super(editor);
    }

    @Override
    public int execute(String[] newArgs) {
        editor.printLines();
        return 0;
    }
}


