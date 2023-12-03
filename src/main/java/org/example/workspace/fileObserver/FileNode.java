package org.example.workspace.fileObserver;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FileNode {
    String name;
    int level;
    List<FileNode> children;
    FileNode parent;
    String treeContent;
    boolean leaf;

    public FileNode(String inputName) {
        name = inputName;
        children = new ArrayList<>();
        parent = null;
        treeContent = "";
    }

    public void addChild(FileNode tmp) {
        children.add(tmp);
        tmp.setParent(this);
    }

    public void printNode() {
        String leafString = "file";
        if (!leaf) {
            leafString = "dir";
        }
        System.out.println("name: " + name + " " + leafString); //" level: " + level + " children: " + children.size()
    }

    public void printContent() {
        System.out.println(treeContent);
    }
}
