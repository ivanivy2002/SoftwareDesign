package org.example.command.update;

import org.example.Editor;
import org.example.command.abstractCommand.EditorCommand;
import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

public class InsertCommand extends EditorCommand {
    public InsertCommand(Editor editor) {
        super(editor);
    }
    @Override
    public void execute(String[] newArgs) {
        ConsoleTool.println("Inserting...");
        int lineNum = 0;
        String[] lines = editor.getLines();
        String lineContent ="";
        //TIPS: lineContent shd be including all rest args from Args[1] to the end
        if(StringTool.isNaturalNumber(newArgs[0])){
            ConsoleTool.println("Default insert to the end");
            lineNum = lines.length+1;
            lineContent = StringTool.catString(newArgs,0);
        }
        else{
            lineNum = Integer.parseInt(newArgs[0]);
            lineContent = StringTool.catString(newArgs,1);
        }
        if(lineContent.split("\\s+", 2).length != 2){
            ConsoleTool.println("ERR: lineContent without Marking Symbol");
            return;
        }
//        ConsoleTool.println("lineContent: "+lineContent);
//        ConsoleTool.println("lineNum: "+lineNum);
//        ConsoleTool.println("lines.length: "+lines.length);
        if(lineNum<1||lineNum>lines.length+1){
            ConsoleTool.println("ERR: lineNum out of range");
            return;
        }
        lineNum--;
        //这是纵容的写法，插入嘛，无所谓，!已经删去
//        lineNum =Math.max(0,Math.min(lineNum,lines.length));
//        ConsoleTool.println("lineNum: "+lineNum);
        lines = StringTool.insertLine(lines,lineNum,lineContent);
        editor.setLines(lines);
//        editor.insert(lineNum, lineContent);
    }
}
