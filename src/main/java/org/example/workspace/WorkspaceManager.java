package org.example.workspace;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.ConsoleTool;
import org.example.utils.FileAccessor;
import org.example.utils.HeadParser;
import org.example.utils.StringTool;
import org.example.workspace.fileObserver.FileNode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@Getter
@Setter
//@NoArgsConstructor
public class WorkspaceManager {
    protected Map<String, Workspace> workspaceMap = new HashMap<>();
    protected Workspace cur = null;
    FileNode originNode = null;

    public WorkspaceManager() {
//        this.loadWorkspaceMap();
        this.activateWorkspaceMapSerialization();
    }

    private void loadWorkspaceMap() {
        workspaceMap = fromString(FileAccessor.readJson("./data/system/workspace.json"));
        if (workspaceMap == null) {
            workspaceMap = new HashMap<>();
            return;
        }
        ConsoleTool.println("workspaceMap loaded");
        for (String key : workspaceMap.keySet()) {
            Workspace tmp = workspaceMap.get(key);
            tmp.getRegister().commandReg(tmp.getExecutor(), tmp.getEditor(), tmp.getHistoryTable());
            if (tmp.isActive()) {
                cur = tmp;
            }
        }
    }
    public void updateCur(String name) {
        if (cur != null) cur.setActive(false);
        cur = workspaceMap.get(name);
        cur.setActive(true);
    }
    public int execute(String commandString) {
//        ConsoleTool.printDebug(cur.name);
        if (commandString.trim().isEmpty()) return -1;
        commandString = HeadParser.parseCommandHead(commandString);
        String[] args = commandString.split("\\s+"); // 使用空格作为分隔符切割命令字符串
//        解决超级命令
        int ret = this.superCommandProcess(args);
        if (ret == 0) return 0;
        else if (ret == -1) {
            ConsoleTool.printERR("WorkspaceManager.execute", "Invalid superCommand");
            return -1;
        }
//        其他命令或者load的后部分(加载文件)
        if (cur == null) {
            ConsoleTool.printERR("WorkspaceManager.execute", "Please create a workspace first");
            return -1;
        }
        if (cur.executeCommand(args) == 0) {
            if (cur.pushLog(commandString) != 0) {
                ConsoleTool.printERR("CommandExecutor.executeCommand", "No session started");
                return -1;
            }
            cur.push(commandString);
        }
        return 0;
    }
    private int superCommandProcess(String[] args) {
        if (args[0].equals("load")) {
            this.loadProcess(args[1]);
            // 还需要继续执行后面的命令
            return 1;
        }
        if (args[0].equals("list-workspace")) {
            this.listWorkspaceProcess();
            return 0;
        }
        if (args[0].equals("open")) {
            ConsoleTool.println("# Opening workspace " + args[1]);
            this.updateCur(args[1]);
            return 0;
        }
        if (args[0].equals("exit")) {
            if (this.exitProcess() == 0) {
                if (args.length > 1) {
                    if (args[1].equals("weak")) {
                        ConsoleTool.println("# Exit weakly, for test only");
                        return 0;
                    }
                }
                ConsoleTool.println("# Bye");
                System.exit(0);
            }
            return 0; //! theoretically unreachable
        }
        if (args[0].equals("close")) {
            return this.closeProcess();
        }
        if (args[0].equals("ls")) {
            return this.lsProcess();
        }
        if (args[0].equals("restart")) {
            ConsoleTool.println("# Restarting...");
//            loadWorkspaceMap();
            activateWorkspaceMapSerialization();
            return 0;
        }
        //不是超级命令，继续执行
        return 1;
    }

    private int closeProcess() {
        if (cur.checkUnsaved()) {
            ConsoleTool.println("? Do you want to save the unsaved workspace [Y\\N]? ");
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                cur.executeCommand(new String[]{"save"});
                this.getWorkspaceMap().remove(cur.name);
                cur = null;
                return 0;
            } else if (input.equalsIgnoreCase("N")) {
                // do nothing
                this.getWorkspaceMap().remove(cur.name);
                cur = null;
                return 0;
            } else {
                ConsoleTool.println("# Invalid input");
                return -1;
            }
        }
        return 0;
    }
    private void loadProcess(String arg1) {
        String tmpName = StringTool.parseFileNameFromExtension(arg1);
        if (workspaceMap.get(tmpName) == null) {
            workspaceMap.put(tmpName, new Workspace(tmpName));
        } else {
            // 重复加载的处理
            ConsoleTool.println("Already loaded");
        }
        this.updateCur(tmpName);
    }
    private void listWorkspaceProcess() {
        ConsoleTool.println("Workspaces:");
        for (String key : workspaceMap.keySet()) {
            // NOTE: 2023/12/01 是判断名字相等还是判断active好呢? 目前是判断active更好，因为cur可能为空
            Workspace tmp = workspaceMap.get(key);
            if (tmp.isActive()) { //key.equals(cur.name)
                if (tmp.checkUnsaved()) System.out.println("->" + key + " *");
                else System.out.println("->" + key);
            } else {
                if (tmp.checkUnsaved()) System.out.println(key + " *");
                else System.out.println(key);
            }
        }
    }

    private int lsProcess() {
        ConsoleTool.println("# ls Files:");
//        FileAccessor.getAllFileNames(new File(path));
        originNode = AllFileNamesToNodes(new File(StringTool.restructPathFromName("./data/", cur.getName())));
        return 0;
    }

    private int exitProcess() {
        if (this.checkAllWorkspaceUnsaved()) {
            ConsoleTool.println("? Do you want to save all unsaved workspace [Y\\N]? ");
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                ConsoleTool.println("# Saving all unsaved workspace...");
                for (String key : workspaceMap.keySet()) {
                    Workspace tmp = workspaceMap.get(key);
                    if (tmp.checkUnsaved()) {
                        tmp.executeCommand(new String[]{"save"});
                        tmp.save();
                    }
                }
                this.outputWorkspaceMap();
                this.persistenceWorkspaceMapSerialization();
                workspaceMap.clear();
                return 0;
            } else if (input.equalsIgnoreCase("N")) {
                workspaceMap.clear();
                return 0;
            } else {
                ConsoleTool.println("# Invalid input");
                return -1;
            }
        } else {
            ConsoleTool.println("# All workspace saved, exit now");
            return 0;
        }
    }

    private void outputWorkspaceMap() {
        FileAccessor.writeJson("./data/system/workspace.json", this.WorkspaceMaptoString());
    }

    private void persistenceWorkspaceMapSerialization() {
        FileAccessor.writeFile("./data/system/workspace.txt", workspaceMap.keySet().toArray(new String[0]));
        for (String key : workspaceMap.keySet()) {
            Workspace tmp = workspaceMap.get(key);
            try {
                FileOutputStream fileOut = new FileOutputStream("./data/system/" + key + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(tmp);
                out.close();
                fileOut.close();
                ConsoleTool.println("# Serialized data saved in ./data/system/" + key + ".ser");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void activateWorkspaceMapSerialization() {
        String[] set = FileAccessor.readFile("./data/system/workspace.txt");
        // 反序列化对象
        for (String key : set) {
            try {
                FileInputStream fileIn = new FileInputStream("./data/system/" + key + ".ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Workspace deserializedObject = (Workspace) in.readObject();
                in.close();
                fileIn.close();
                ConsoleTool.println("# VerSerial to: " + deserializedObject.getName());
                if (deserializedObject.isActive()) {
                    cur = deserializedObject;
                }
                workspaceMap.put(key, deserializedObject);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean checkAllWorkspaceUnsaved() {
        for (String key : workspaceMap.keySet()) {
            Workspace tmp = workspaceMap.get(key);
            if (tmp.checkUnsaved()) {
                return true;
            }
        }
        return false;
    }

    // Json Tools
    private String WorkspaceMaptoString() {
        return JSON.toJSONString(this.workspaceMap, SerializerFeature.PrettyFormat);
    }

    public static Map<String, Workspace> fromString(String str) {
        if (str == null || str.trim().isEmpty()) {
            ConsoleTool.println("workspaceManager.fromString(): null or empty string");
            return null;
        }
        return JSON.parseObject(str, new TypeReference<Map<String, Workspace>>() {
        });
    }

    public static FileNode AllFileNamesToNodes(File directory) {
        if (directory.isDirectory()) {
            System.out.println(">" + directory.getName());
            FileNode originNode = new FileNode(directory.getName());
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                        FileNode tmp = new FileNode(file.getName());
                        originNode.addChild(tmp);
                    } else if (file.isDirectory()) {
                        Objects.requireNonNull(AllFileNamesToNodes(file)).setParent(originNode);
                    }
                }
            }
            return originNode;
        }
        return null;
    }



}
