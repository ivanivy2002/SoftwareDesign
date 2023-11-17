package trash;

// 使用组合模式管理目录
public class DirectoryManager {
    public static void main(String[] args) {
        // 创建目录树
        Composite root = new Composite();
        root.setContent("根目录");
        Composite dir1 = new Composite();
        dir1.setContent("目录1");
        Composite dir2 = new Composite();
        dir2.setContent("目录2");
        Leaf file1 = new Leaf("文件1");
        Leaf file2 = new Leaf("文件2");

        // 添加子节点
        root.add(dir1);
        root.add(dir2);
        dir1.add(file1);
        dir2.add(file2);

        // 显示目录树
        root.display(0);
    }
}