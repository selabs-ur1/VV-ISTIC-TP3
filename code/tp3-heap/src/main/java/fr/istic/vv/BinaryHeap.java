package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    private Comparator<T> comparator;
    ArrayList<Integer> array;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    public BinaryHeap(Comparator<T> comparator, ArrayList<Integer> array) {
        this.comparator = comparator;
        this.array = array;
    }

    public ArrayList<Integer> getArray() {
        return array;
    }

    public Integer pop() {
        if(this.array.size()>0){
            Integer value = this.array.get(this.array.size()-1);
            this.array.remove(value);
            System.out.println(array.toString());
            return value;
        }else{
            throw new NoSuchElementException("No value in heap");
        }
    }

    public Integer peek() {
        System.out.println(array);
        if(this.array.size()>0){
            Integer value = this.array.get(this.array.size()-1);
            return value;
        }else{
            throw new NoSuchElementException("No value in heap");
        }    }

    public void push(Integer element) {
        System.out.println(array);
        if(this.array.size()<1){
            array.add(element);
        }else {
            this.arrange(element);
        }
    }

    public int count() {
        System.out.println(array);
        return this.array.size();
    }

    public void arrange(Integer element) {
        array.add(element);
        swap_values(0, array.size()-1, array);
        int parent_index = 0;
        int left_child_index = getLeftChild(parent_index);
        boolean is_left_valid = is_valid_index(left_child_index);

        while (is_left_valid){
            int smaller_index = left_child_index;
            int right_child_index = getRightChild(parent_index);
            boolean is_right_valid = is_valid_index(right_child_index);
            if(is_right_valid && is_left_valid){
                System.out.println("parent : " + array.get(parent_index) + " left : " + array.get(left_child_index) + " right : " + array.get(right_child_index) );
            }

            if(is_right_valid && array.get(right_child_index) < array.get(left_child_index)){
                smaller_index = right_child_index;
            }
            if (array.get(smaller_index) < array.get(parent_index)){
                System.out.println("parent : " + array.get(parent_index) + " small : " + array.get(smaller_index));

                swap_values(smaller_index, parent_index, array);
                parent_index = smaller_index;
                left_child_index = getLeftChild(parent_index);
                is_left_valid = is_valid_index(left_child_index);
            }else {
                break;
            }
        }

    }

    private int getLeftChild(int parent_index){
        return (2 * parent_index) + 1;
    }

    private int getRightChild(int parent_index){
        return (2 * parent_index) + 2;
    }

    private void swap_values(int index_a, int index_b, ArrayList array){
        int temp_value = (int) array.get(index_a);
        int val_b = (int) array.get(index_b);
        array.set(index_a, val_b);
        array.set(index_b, temp_value);
    }

    private boolean is_valid_index(int index){
        return index >= 0 && index < this.array.size();
    }


}