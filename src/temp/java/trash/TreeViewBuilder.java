package trash;

import org.example.utils.StringTool;

public class TreeViewBuilder {
    static final int LEAF_VALUE = 99;
    public static String[] tree;
    public static int[] level;

    //    public static int[][] levelMatrix;
    TreeViewBuilder() {
//        System.out.println("LineTree");
    }

    public static void buildTree(String[] lines) {
        String[] treeLine = new String[lines.length];
        String[] heads = new String[lines.length];
        String[] contents = new String[lines.length];
        int[] levelLine = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] args = lines[i].split("\\s+", 2);
            heads[i] = args[0];
            contents[i] = args[1];
            levelLine[i] = StringTool.countHashes(heads[i]);
            if (levelLine[i] == 0) {
                heads[i] = heads[i].replace("*", "·");
                heads[i] = StringTool.repeatString("\t", levelLine[i - 1]) + "\t├── " + heads[i];
                levelLine[i] = LEAF_VALUE;
            } else {
                heads[i] = StringTool.repeatString("\t", levelLine[i]) + "├── ";
            }
        }
//        int[][] mat = new int[max+1][lines.length];
        for (int i = 0; i < lines.length; i++) {
            if ((i < lines.length - 1) && (!heads[i + 1].equals(heads[i])) || (i == lines.length - 1)) {
                heads[i] = heads[i].replace("├──", "└──");
            }
            treeLine[i] = heads[i] + contents[i];

        }
        tree = treeLine;
        level = levelLine;
    }

    public static void printTree() {
        for (int i = 0; i < tree.length; i++) {
            System.out.println("[" + i + ":" + level[i] + "]" + tree[i]);
        }
    }

    public static void showDir(Integer n) {
        if (n == tree.length - 1) {
            System.out.println(tree[n]);
            return;
        }
        int i = n;
        do {
            System.out.println("[" + i + ":" + level[i] + "]" + tree[i]);
        } while (i <= tree.length - 1 && level[n] < level[(i++) + 1]);
//        while(n < tree.length-1 && level[n]<level[n+1]){
//            System.out.println("["+n+":"+level[n]+"]"+tree[n]);
//            n++;
//        }
    }
}
