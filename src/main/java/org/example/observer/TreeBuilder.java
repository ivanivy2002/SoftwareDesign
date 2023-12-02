package org.example.observer;

import org.example.utils.ConsoleTool;
import org.example.utils.StringTool;

public class TreeBuilder {

    static final int LEAF_VALUE = 99;

    public static String[] tree;
    public static int[] level;
    public static Node treeRoot;
    public static void build(String[] lines) {
        if (lines == null || lines.length == 0) {
            ConsoleTool.println("ERR: Empty Lines!");
            return;
        }
        int[] levelLine = parseLevel(lines);
        Node root = buildTreeByLevel(levelLine);
        reformatTree(lines, root);
        reformatTreeVertical();
    }

    public static int[] parseLevel(String[] lines) {
        int[] levelLine = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
//            lines[i] = args[0];
            levelLine[i] = StringTool.countHashes(lines[i].split("\\s+", 2)[0]);
            if (levelLine[i] == 0) {
                levelLine[i] = LEAF_VALUE;
            }
        }
        level = levelLine;
        return levelLine;
    }

    public static Node buildTreeByLevel(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        Node root = new Node(-1, 0);
        Node cur = root;
        int i = 0;
        while (i < level.length) {
            Node node = new Node(i, level[i]);
            // 构建子节点
            if (level[i] == LEAF_VALUE) {
                cur.children.add(node);
                node.parent = cur;
//                cur = node;
            } else {
                while (cur.parent != null && cur.value >= level[i]) {
                    cur = cur.parent;
                }
                cur.children.add(node);
                node.parent = cur;
                cur = node;
            }
            i++;
        }
        treeRoot = root;
        return root;
    }

    public static void reformatTree(String[] lines, Node root) {
        if (root == null) {
            return;
        }
        String[] treeLine = new String[lines.length];
        int levelBase = getNode(root, 0).value - 1;
//        int lineNum = root.lineNum;
        for (int lineNum = 0; lineNum < lines.length; lineNum++) {
            Node cur = getNode(root, lineNum);
            String content = null;
            if (cur.value != 0) {
                String marker = lines[lineNum].split("\\s+", 2)[0];
                content = lines[lineNum].split("\\s+", 2)[1];
                int repeatTimes = level[lineNum] - 1;
                if (cur.value == LEAF_VALUE) {
                    marker = marker.replace("*", "·");
                    repeatTimes = cur.parent.value;
                } else {
                    marker = "";
                }
                repeatTimes -= levelBase;
                content = String.format("%s%s %s%s", StringTool.repeatString("    ", repeatTimes), "├──", marker, content);
//                if ((lineNum < lines.length - 1) && (level[lineNum + 1] != (level[lineNum])) || (lineNum == lines.length - 1)) {
                if (cur.parent.children.size() - 1 == cur.parent.children.indexOf(cur)) {
                    content = content.replace("├──", "└──");
                }
                treeLine[lineNum] = content;
            }
        }
//        for (Node child : root.children) {
//            reformatTree(lines, child, tab + 1);
//        }
        tree = treeLine;
    }

    public static void reformatTreeVertical() {
        for (int i = 0; i < tree.length; i++) {
            Node root = getNode(treeRoot, i);
            if (root.parent.children.size() - 1 != root.parent.children.indexOf(root)) {
                if (!root.children.isEmpty()) {
                    int index = Math.max(tree[i].indexOf("├──"), tree[i].indexOf("└──"));
//                    for (Node child : root.children) {
                    for (int j = i + 1; j < tree.length; j++) {
                        if (belong(getNode(treeRoot, j), root)) {
                            tree[j] = StringTool.replaceStringAt(tree[j], index, "│  ");
                        }
                    }
//                    }
                }
            }
        }
    }

    public static void printTreeAll() {
        if (tree == null) {
            ConsoleTool.println("ERR: Empty Tree!");
            return;
        }
        for (int i = 0; i < tree.length; i++) {
//            System.out.println("["+i+":"+level[i]+"]"+tree[i]);
            System.out.println("[" + StringTool.convertLineNumber(tree.length, i) + "] " + tree[i]);
        }
    }

    public static void printTree(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(tree[root.lineNum]);
        for (Node child : root.children) {
//            System.out.println("L"+root.lineNum+":"+root.value+" child: L"+child.lineNum +":"+ child.value + " ");
            printTree(child);
        }
//        printTree(root.right);
//        System.out.println();
    }

    public static Node getNode(Node root, int lineNum) {
        if (root == null) {
            return null;
        }
        if (root.lineNum == lineNum) {
            return root;
        }
        for (Node child : root.children) {
            Node node = getNode(child, lineNum);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    public static boolean belong(Node cur, Node root) {
        while (cur.parent != null) {
            if (cur.parent == root) {
                return true;
            }
            cur = cur.parent;
        }
        return false;
    }

    public static void showDir(String[] lines, Integer matchingLineNum) {
        int[] levelLine = parseLevel(lines);
        int i = matchingLineNum;
        for (; i < lines.length; i++) {
            if (level[i] < level[matchingLineNum]) {
//                System.out.println(tree[i]);
                break;
            }
        }
        i--;
        String[] treeLine = new String[i - matchingLineNum + 1];
        System.arraycopy(lines, matchingLineNum, treeLine, 0, treeLine.length);
        levelLine = parseLevel(treeLine);
        Node root = buildTreeByLevel(levelLine);
        reformatTree(treeLine, root);
        reformatTreeVertical();
        printTreeAll();
    }
}