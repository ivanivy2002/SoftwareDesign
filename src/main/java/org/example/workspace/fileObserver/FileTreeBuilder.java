//package org.example.workspace.fileObserver;
//
//import org.example.utils.ConsoleTool;
//import org.example.utils.StringTool;
//
//public class FileTreeBuilder {
//    public static String[] tree;
//    public static int[] level;
//    public static FileNode treeRoot;
//
//    public static void build(String[] lines) {
//        if (lines == null || lines.length == 0) {
//            ConsoleTool.println("ERR: Empty Lines!");
//            return;
//        }
//        int[] levelLine = parseLevel(lines);
//        FileNode root = buildTreeByLevel(levelLine);
//        reformatTree(lines, root);
//        reformatTreeVertical();
//    }
//
//    public static int[] parseLevel(String[] lines) {
//        int[] levelLine = new int[lines.length];
//        for (int i = 0; i < lines.length; i++) {
////            lines[i] = args[0];
//            levelLine[i] = StringTool.countHashes(lines[i].split("\\s+", 2)[0]);
//            if (levelLine[i] == 0) {
////                levelLine[i] = LEAF_VALUE;
//            }
//        }
//        level = levelLine;
//        return levelLine;
//    }
//
//    public static FileNode buildTreeByLevel(int[] level) {
//        if (level == null || level.length == 0) {
//            return null;
//        }
//        FileNode root = new FileNode(-1, 0);
//        FileNode cur = root;
//        int i = 0;
//        while (i < level.length) {
//            FileNode fileNode = new FileNode(name);
//            // 构建子节点
////            if (level[i] == LEAF_VALUE) {
//                cur.children.add(fileNode);
//                fileNode.parent = cur;
////                cur = node;
//            } else {
//                while (cur.parent != null && cur.value >= level[i]) {
//                    cur = cur.parent;
//                }
//                cur.children.add(fileNode);
//                fileNode.parent = cur;
//                cur = fileNode;
//            }
//            i++;
//        }
//        treeRoot = root;
//        return root;
//    }
//
//    public static void reformatTree(String[] lines, FileNode root) {
//        if (root == null) {
//            return;
//        }
//        String[] treeLine = new String[lines.length];
//        int levelBase = getNode(root, 0).value - 1;
////        int lineNum = root.lineNum;
//        for (int lineNum = 0; lineNum < lines.length; lineNum++) {
//            FileNode cur = getNode(root, lineNum);
//            String content = null;
//            if (cur.value != 0) {
//                String marker = lines[lineNum].split("\\s+", 2)[0];
//                content = lines[lineNum].split("\\s+", 2)[1];
//                int repeatTimes = level[lineNum] - 1;
//                if (cur.value == LEAF_VALUE) {
//                    marker = marker.replace("*", "·");
//                    repeatTimes = cur.parent.value;
//                } else {
//                    marker = "";
//                }
//                repeatTimes -= levelBase;
//                content = String.format("%s%s %s%s", StringTool.repeatString("    ", repeatTimes), "├──", marker, content);
////                if ((lineNum < lines.length - 1) && (level[lineNum + 1] != (level[lineNum])) || (lineNum == lines.length - 1)) {
//                if (cur.parent.children.size() - 1 == cur.parent.children.indexOf(cur)) {
//                    content = content.replace("├──", "└──");
//                }
//                treeLine[lineNum] = content;
//            }
//        }
////        for (Node child : root.children) {
////            reformatTree(lines, child, tab + 1);
////        }
//        tree = treeLine;
//    }
//
//    public static void reformatTreeVertical() {
//        for (int i = 0; i < tree.length; i++) {
//            FileNode root = getNode(treeRoot, i);
//            if (root.parent.children.size() - 1 != root.parent.children.indexOf(root)) {
//                if (!root.children.isEmpty()) {
//                    int index = Math.max(tree[i].indexOf("├──"), tree[i].indexOf("└──"));
////                    for (Node child : root.children) {
//                    for (int j = i + 1; j < tree.length; j++) {
//                        if (belong(getNode(treeRoot, j), root)) {
//                            tree[j] = StringTool.replaceStringAt(tree[j], index, "│  ");
//                        }
//                    }
////                    }
//                }
//            }
//        }
//    }
//
//    public static void printTreeAll() {
//        if (tree == null) {
//            ConsoleTool.println("ERR: Empty Tree!");
//            return;
//        }
//        for (int i = 0; i < tree.length; i++) {
////            System.out.println("["+i+":"+level[i]+"]"+tree[i]);
//            System.out.println("[" + StringTool.convertLineNumber(tree.length, i) + "] " + tree[i]);
//        }
//    }
//
//    public static void printTree(FileNode root) {
//        if (root == null) {
//            return;
//        }
//        System.out.println(tree[root.lineNum]);
//        for (FileNode child : root.children) {
////            System.out.println("L"+root.lineNum+":"+root.value+" child: L"+child.lineNum +":"+ child.value + " ");
//            printTree(child);
//        }
////        printTree(root.right);
////        System.out.println();
//    }
//
//    public static FileNode getNode(FileNode root, int lineNum) {
//        if (root == null) {
//            return null;
//        }
//        if (root.lineNum == lineNum) {
//            return root;
//        }
//        for (FileNode child : root.children) {
//            FileNode fileNode = getNode(child, lineNum);
//            if (fileNode != null) {
//                return fileNode;
//            }
//        }
//        return null;
//    }
//
//    public static boolean belong(FileNode cur, FileNode root) {
//        while (cur.parent != null) {
//            if (cur.parent == root) {
//                return true;
//            }
//            cur = cur.parent;
//        }
//        return false;
//    }
//
//    public static void showDir(String[] lines, Integer matchingLineNum) {
//        int[] levelLine = parseLevel(lines);
//        int i = matchingLineNum;
//        for (; i < lines.length; i++) {
//            if (level[i] < level[matchingLineNum]) {
////                System.out.println(tree[i]);
//                break;
//            }
//        }
//        i--;
//        String[] treeLine = new String[i - matchingLineNum + 1];
//        System.arraycopy(lines, matchingLineNum, treeLine, 0, treeLine.length);
//        levelLine = parseLevel(treeLine);
//        FileNode root = buildTreeByLevel(levelLine);
//        reformatTree(treeLine, root);
//        reformatTreeVertical();
//        printTreeAll();
//    }
//}