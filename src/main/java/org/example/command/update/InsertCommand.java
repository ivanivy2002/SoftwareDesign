package org.example.command.update;

import org.example.Editor;
import org.example.command.abstractCommand.VisitEditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

public class InsertCommand extends VisitEditorCommand {
    public InsertCommand(Editor editor) {
        super(editor);
    }
    @Override
    public int execute(String[] newArgs) {
        ConsoleTool.println("Inserting...");
        int lineNum;
//        String[] lines = editor.getLines();
        String lineContent;
        //TIPS: lineContent shd be including all rest args from Args[1] to the end
        if (StringTool.isNotNaturalNumber(newArgs[0])) {
//            lineNum = lines.length+1;
            lineContent = StringTool.catString(newArgs,0);
            return editor.insert(-1, lineContent);
        }
        else{
            lineNum = Integer.parseInt(newArgs[0]);
            lineContent = StringTool.catString(newArgs,1);
            return editor.insert(lineNum - 1, lineContent);
        }
    }
}
