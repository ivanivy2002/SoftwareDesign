package trash;

abstract class Component {
    protected String content;

    abstract void add(Component component);

    abstract void remove(Component component);

    abstract void display(int depth);
}