import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;
import org.example.utils.TimeTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Test {

//    @Test
//    public void testInsertCommand() {
//        ConsoleTool.println("test");
//    }

    public static void main(String[] args) {
        ConsoleTool.println(StringTool.repeatString("Ass", 0));
        ConsoleTool.println(TimeTool.getCurrentTime());
        String c = "aaaabbbbcccc";
        ConsoleTool.println(String.valueOf(c.indexOf("bbbb")));
        ConsoleTool.println(String.valueOf(c.indexOf("dd")));


        ConsoleTool.println(StringTool.replaceStringAt(c, c.indexOf("bbbb"), "re"));

//        String regex = "^(?<=\\#{0,}|\\*{0,}|\\d{0,}\\.)\\S.*";
//        String regex ="(?<![#*\\d.])[#*]*(?=\\S)";
//        String regex ="(?<=[#*])[#*]*\\S.*";
//        String regex ="(?![#*])(?!\\d+\\.)[#*]*\\S.*";
//        Pattern pattern = Pattern.compile(regex);
//
//        [#*]+|\\d+\\.
//
//        while(true){
//            System.out.print("$ ");
//            input = scanner.nextLine();
//            if(input.equals("exit")){
//                break;
//            }
//            Matcher matcher = pattern.matcher(input.replaceAll("\s",""));
//            if(matcher.find()){
//                ConsoleTool.println("find");
//                ConsoleTool.println("group(0): "+matcher.group(0));
////                ConsoleTool.println("group(1): "+matcher.group(1));
//            }
//            else{
//                ConsoleTool.println("not find");
//            }
//        }
    }
}
