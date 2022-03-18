package fr.istic.vv;

import java.util.Comparator;

public class IntegerHeap extends BinaryHeap<Integer,NotSuchElementException> {
    public IntegerHeap(Comparator<Integer> comparator) {
        super(comparator);
    }
//
//    private final Comparator<Integer> comparator ;
//
//    private Integer value = null;
//    private IntegerHeap leftChild = null;
//    private IntegerHeap rightChild = null;
//
//    public IntegerHeap(Comparator<Integer> comparator) {
//        this.comparator = comparator;
//        leftChild = new IntegerHeap(comparator);
//        rightChild = new IntegerHeap(comparator);
//    }
//
//    @Override
//    public Integer pop() throws NotSuchElementException, TreeCourseError {
//        if(value==null) throw  new NotSuchElementException();
//        else if(leftChild == null && rightChild != null){
//            int result = value;
//            this.value = rightChild.value;
//            this.rightChild = null;
//            return result;
//        }else {
//            throw new TreeCourseError();
//        }
//    }
//
//    @Override
//    public Integer peek() throws NotSuchElementException {
//        if (leftChild == null) return value;
//        else return leftChild.peek();
//    }
//
//    @Override
//    public void push(Integer element) {
//        if (value == null || value == element) this.value = element;
//        else if(comparator.compare(value,element) < 0) leftChild.push(element);
//        else rightChild.push(element);
//    }
//
//    @Override
//    public int count() {
//        if(this.value == null) return 0;
//        else return 1+leftChild.count()+ rightChild.count();
//    }
}
