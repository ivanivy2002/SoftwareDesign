package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class Node {
    final int lineNum;
    int value;
    List<Node> children;
    Node parent;

    public Node(int lineNum, int value) {
        this.lineNum = lineNum;
        this.value = value;
        children = new ArrayList<>();
        parent = null;
    }
}
