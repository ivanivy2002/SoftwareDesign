package org.example.workspace.fileObserver;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FileNode {
    //    final int lineNum;
    String name;
    //    String[] names;
    int level;
    List<FileNode> children;
    FileNode parent;

    public FileNode(String fullname) {
//        this.lineNum = lineNum;
//        names = fullname.split("/");
//        names[names.length- 1] = names[names.length - 1].split(".md")[0];
//        level = names.length - 1;
        children = new ArrayList<>();
        parent = null;
    }

    public void addChild(FileNode tmp) {
        children.add(tmp);
        tmp.setParent(this);
    }
}
