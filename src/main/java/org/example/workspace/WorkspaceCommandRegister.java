package org.example.workspace;


import org.example.Editor;
import org.example.command.*;
import org.example.command.history.*;
import org.example.command.loadSave.*;
import org.example.workspace.workspaceCommand.*;

public class WorkspaceCommandRegister extends CommandRegister {

    @Override
    public void commandReg(CommandExecutor executor, Editor editor, HistoryTable historyTable) {
        //Load and Save
        executor.registerCommand("load", new WorkspaceLoadCommand(editor));
        executor.registerCommand("list-workspace", new ListWorkspaceCommand(editor));
    }
}
