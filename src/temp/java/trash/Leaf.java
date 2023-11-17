package trash;

class Leaf extends Component {
//    private final String content;

    public Leaf(String content) {
        this.content = content;
    }

    @Override
    void add(Component component) {
        throw new UnsupportedOperationException("叶子节点不支持添加子节点");
    }

    @Override
    void remove(Component component) {
        throw new UnsupportedOperationException("叶子节点不支持删除子节点");
    }

    @Override
    void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        System.out.println(content);
    }
}