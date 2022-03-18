package fr.istic.vv;

import java.util.Comparator;

class BinaryHeap<T> {

    private Comparator<T> comp;

    private Node head;

    public BinaryHeap(Comparator<T> comparator) { 
        comp = comparator;
     }

    public T pop() {
        Node node = head; 
        while(node.low != null){
            node = node.low;
        }
        T value = node.value;
        node = null;
        return value;
     }

    public T peek() { 
        System.out.println(head.value);
        Node node = head; 
        while(node.low != null){
            node = node.low;
        }
        T value = node.value;
        return value;
     }

    public void push(T element) { 
        if(head == null){
            head = new Node(element);
        } else {
   
        Node node = head; 
        boolean canGoDown = true;
        while(canGoDown){
            int val = comp.compare(node.value, element);
            if(val>0) {
                if(node.high == null) {
                    node.high = new Node(element);
                    canGoDown = false;
                } else {
                    node = node.high;
                }
            }
            else if(val<=0) {
                if(node.high == null) {
                    node.low = new Node(element);
                    canGoDown = false;
                } else {
                    node = node.low;
                }
            }
                     
        }
        }
     }

    public int count() { 
        if(head == null) return 0;
        return head.getSize(); }

    private class Node{
        public T value;
        public Node low;
        public Node high;
        
        public Node(T value){
            this.value = value;
            low = null;
            high = null;
        };

        public int getSize(){
            int num = 1;
            if(low != null) num += low.getSize();
            if(high != null) num += high.getSize();
            return num;
        }
    }

}