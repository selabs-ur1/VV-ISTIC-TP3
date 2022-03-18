package fr.istic.vv;

import java.util.Comparator;

class BinaryHeap<T extends Comparable<T>> {

    private static final int MINIMUM_SIZE = 256;

    private int size = 0;
    private Node<T> root = null;

    private BinaryHeap<T> left;
    private BinaryHeap<T> right;

    private Comparator<T> comparator;

    private T lowestNode;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        root=null;
        size = 0;
    }

    public T pop() {
        size--;
        return null;
    }

    public T peek()
    {
        if(root == null) return null;
        if(size == 1)
        {
            lowestNode = root.value;
            return root.value;
        }
        T lowestRight = null;
        T lowestLeft = null;
        if(right != null) {
            lowestRight = right.peek();
        }
        if(left != null) {
            lowestLeft = left.peek();
        }

        int compareResult = comparator.compare(lowestLeft,lowestRight);
        if( compareResult == 0) {
            lowestNode = root.value;
            return lowestNode;
        } else if ( compareResult < 0) {
            lowestNode = lowestRight;
            return lowestNode;
        } else if( compareResult > 0){
            lowestNode = lowestLeft;
            return lowestNode;
        } else {
            return root.value;
        }
    }

    public void push(T element) {

        if (root == null) {
            root = new Node(element);
        }
        Node<T> node = root;
        int[] directions = getDirection(this.size);
        int compareResult = comparator.compare(root.value,element);
        if( compareResult == 0) {
            root = new Node(element);
        }
        else {
            if (directions != null && directions.length > 0) {
                for (int d : directions) {
                    if (d == 0) {
                        // Go left
                       if(left == null)
                       {
                           left = new BinaryHeap<>(comparator);
                       }
                       left.push(element);
                    } else {
                        if(right == null)
                        {
                            right = new BinaryHeap<>(comparator);
                        }
                        right.push(element);
                    }
                }
            }
        }

        //current node has both children

        size ++;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public BinaryHeap<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryHeap<T> left) {
        this.left = left;
    }

    public BinaryHeap<T> getRight() {
        return right;
    }

    public void setRight(BinaryHeap<T> right) {
        this.right = right;
    }

    public T getLowestNode() {
        return lowestNode;
    }

    public void setLowestNode(T lowestNode) {
        this.lowestNode = lowestNode;
    }
    public int count() { return size; }

    private boolean hasChildren(){
        return this.getLeft() != null;
    }

    private boolean hasChildrenRight() {
        return this.getRight() != null;
    }

    public int[] getDirection(int size)
    {
        int[] direction = null;

        if(size > 0 && size == this.size)
        {
            direction = new int[size-1];
            for(int i = 0; i < direction.length; i++)
            {
                direction[i] = i%2 == 0 ? 1 :0;
            }
        }

        return direction;
    }

}