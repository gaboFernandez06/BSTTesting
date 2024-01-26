public class Node <K,V> {
    private Node left ;
    private Node right ;
    private K key ;
    private V value ;

    public <K extends Comparable<K>, V> Node(K key, V val) {
    }

    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }

}
