package org.example.command.observe;

import java.util.ArrayList;
import java.util.List;

// 容器节点
class Composite extends Component {
    private List<Component> children = new ArrayList<>();


    public Composite() {
    }

    public Composite(List<Component> children) {
        this.children = children;
    }

    @Override
    void add(Component component) {
        children.add(component);
    }

    @Override
    void remove(Component component) {
        children.remove(component);
    }

    @Override
    void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        System.out.println("目录：" + content);
        for (Component child : children) {
            child.display(depth + 1);
        }
    }

    public void setContent(String name) {
        this.content = name;
    }

    public String getName() {
        return content;
    }
}