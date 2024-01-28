import java.util.ArrayList;
import java.util.Stack;

public class MyBST<K extends Comparable<K>,V> {
    Node rootNode;
    //true if this symbol table is empty; false otherwise
    public boolean isEmpty() {
        if (rootNode == null) {
            return true;
        } else {
            return false;
        }
    }
    //Returns th     e number of key-value pairs in this symbol table.
    public int size (){
       return  size(rootNode);
    }
    public int size(Node node) {
        if (node == null) {
            return 0;
        }
        return size(node.getLeft()) + 1 + size(node.getRight());
    }


    //true if this symbol table contains key and false otherwise
    public boolean containsKey( K key){
        return  containsKey(rootNode, key);
    }
    public boolean containsKey(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (key.equals(node.getKey())) {
            return true;
        }
        int comparedKey = key.compareTo( (K)node.getKey());
        if (comparedKey < 0) {
            return containsKey(node.getLeft(), key);
        } else if (comparedKey > 0) {
            return containsKey(node.getRight(), key);
        } else {
            return true;
        }
    }
    //true if this symbol table contains value and false otherwise
    public boolean containsValue ( V value){
        return  containsValue(rootNode, value);
    }
    public boolean containsValue(Node rootNode, V value) {
        if (rootNode == null) {
            return false;
        }
        if (value.equals(rootNode.getValue())) {
            return true;
        }
        return containsValue(rootNode.getLeft(), value) || containsValue(rootNode.getRight(), value);
    }

    //the value associated with the given key if the key is in the symbol table and null if the key is not in the symbol table
  public V get (K key ){
        return get( rootNode, key);
  }
    public V get(Node rootNode, K key) {
        if (key == null || rootNode == null) {
            return null;
        }

        int comparedKey = key.compareTo((K) rootNode.getKey());
        if (comparedKey < 0) {
            return get(rootNode.getLeft(), key);
        } else if (comparedKey > 0) {
            return get(rootNode.getRight(), key);
        } else {
            return (V) rootNode.getValue();
        }
    }
    //Inserts the specified key-value pair into the symbol table, overwriting the old value with the new value if the symbol table already contains the specified key. Deletes the specified key (and its associated value) from this symbol table if the specified value is null.
  public Node put(K key , V val){
        rootNode = put(rootNode, key, val);
        return rootNode;
  }
    private Node put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }
        int comparedKey = key.compareTo((K) node.getKey());
        if (comparedKey < 0) {
            node.setLeft(put(node.getLeft(),key, val));
        } else if (comparedKey > 0) {
            node.setRight(put(node.getRight(),key, val));
        } else {
            node.setValue(val);
        }
        return node;
    }

    //Removes the smallest key and associated value from the symbol table.
    public Node deleteMin ( ){
        rootNode=  deleteMin(rootNode);
        return rootNode;
    }
    public Node deleteMin(Node rootNode) {
        if (rootNode.getLeft()==null) {
            return rootNode.getRight();
            }
        rootNode.setLeft(deleteMin(rootNode.getLeft()));
        return rootNode;
        }

    //Removes the largest key and associated value from the symbol table.
public Node deleteMax(){
        rootNode = deleteMax(rootNode);
        return   rootNode;
}
private Node deleteMax(Node rootNode) {
        if (rootNode.getRight() == null) {
             return rootNode.getLeft();
        }
        rootNode.setRight(deleteMax(rootNode.getRight()));
        return rootNode;
    }

    //Removes the specified key and its associated value from this symbol table (if the key is in this symbol table).
  public Node delete ( K key){
        rootNode = delete(rootNode, key);
        return rootNode ;
  }
    public Node delete(Node rootNode, K key) {
        if (rootNode == null) {
            return null;
        }
        int comparedKey = key.compareTo((K) rootNode.getKey());
        if (comparedKey < 0) {
            rootNode.setLeft((Node) delete(rootNode.getLeft(), key));
        } else if (comparedKey > 0) {
            rootNode.setRight((Node) delete(rootNode.getRight(), key));
        } else {
            //found the node
            // has no left or right
            if (rootNode.getRight() == null) {
                return rootNode.getLeft();
            } else if (rootNode.getLeft() == null) {
                return rootNode.getRight();
            } else {
                // two child
                Node newReplacement = findMin2(rootNode.getRight());
                rootNode.setKey(newReplacement.getKey());
                rootNode.setValue(newReplacement.getValue());
                rootNode.setRight(deleteMin2(rootNode.getRight()));
            }
        }
        return rootNode;
    }
    public  Node deleteMin2 (Node rootNode ){
    if ( rootNode.getLeft() == null ){
    return null;
    }
     while ( rootNode.getRight() != null ){
    rootNode = rootNode.getRight();
     }
      return rootNode;
    }
    public Node findMin2 ( Node rootNode){
        if (rootNode == null) {

            return null;
        }
        while ( rootNode.getLeft()!= null){
          rootNode = rootNode.getLeft();
        }
        return  rootNode;
    }
    // create helper method to find minimum key in subtree
    // create helper method to delete min value within subtree
    //the smallest key in the symbol table
    public K min() {
        Node minNode = findMin2(rootNode);
        if (rootNode != null) {
            return (K) minNode.getKey();
        } else {
            return null;
        }
    }

    //the largest key in the symbol table
    public K max() {
        if (rootNode == null) {
            return null;
        }
        Node maxNode = rootNode;
        while (maxNode.getRight() != null) {
            maxNode = maxNode.getRight();
        }
        return (K) maxNode.getKey();
    }
    private Node findMax( Node rootNode){
        if  ( rootNode == null){
            return null;
        }
        while ( rootNode.getRight()!=null){
            rootNode= rootNode.getRight();

        }
        return  rootNode;

    }

    //Returns the height of the BST
    public int height(){
        return height(rootNode);
    }
    public int height(Node rootNode) {
        int leftCount = 0;
        int rightCount = 0;
        Node leftSide = rootNode;
        Node rightSide = rootNode;
        if (rootNode == null) {
            return 0;
        }
        while (rightSide.getRight() != null) {
            rightSide = rightSide.getRight();
            rightCount++;
        }
        while (leftSide.getLeft() != null) {
            leftSide = leftSide.getLeft();
            leftCount++;
        }
        return Math.max(leftCount, rightCount);
    }

    //Returns the BST in Pre Order Traversal
    //
    public ArrayList<Node> preOrderTraversal() {
// create array
        // create stack
        // move down the tree going right
        // puush right child to stakc
        // go left
        //move left to array
        // pop the stack to go to the right child
        /// repreat proces of left then right then
        ArrayList<Node> result = new ArrayList<>();
        Stack<Node> stack = new Stack();
        Node currentNode = rootNode;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                result.add(currentNode);
                if (currentNode.getRight() == null) {
                    stack.push(currentNode.getRight());
                }
                currentNode = currentNode.getLeft();
            }
            if (!stack.isEmpty()) {
                currentNode = stack.pop();
            }
        }
        return result;
    }


    //Returns the BST in In Order Traversal
    public ArrayList<Node> inOrderTraversal() {
        ArrayList<Node> result = new ArrayList<>();
        Stack<Node> stack = new Stack();
        Node currentNode = rootNode;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
            }
            if (!stack.isEmpty()) {
                currentNode = stack.pop();
                result.add(currentNode);
                currentNode = currentNode.getRight();
            }
        }
        return result;
    }


    //Returns the BST in Post Order Traversal
    public ArrayList<Node> postOrderTraversal() {
        ArrayList<Node> result = new ArrayList<>();
        Stack<Node> stack = new Stack();
        Node currentNode = rootNode;
        Node lastNode = null;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
            }
            Node topOfStack = stack.peek();
            if (topOfStack.getRight() == null || topOfStack.getRight() == lastNode) {
                result.add(topOfStack);
                stack.pop();
                lastNode = topOfStack;
            } else {
                currentNode = topOfStack.getRight();
            }
        }
        return result;
        //
    }
}

