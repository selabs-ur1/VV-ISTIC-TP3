# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer


### 1. Input Space Partitioning

- Characteristics and blocks identified for pop()

|Characteristics  |  Blocks  |   |  |
|---|---|---|---|
| size of items    | 0 | 1 |  &gt; 1 | 

- Characteristics and blocks identified for peek()

|Characteristics  |  Blocks  |   |  |
|---|---|---|---|
| size of items    | 0 | 1 |  &gt; 1 | 

- Characteristics and blocks identified for push(T element)

|Characteristics  |  Blocks  |   | 
|---|---|---|
| element is null  | True | False |  
| element is the minimum object   | True | False |  
| element is the maximum object   | True | False | 

- Characteristics and blocks identified for count()

|Characteristics  |  Blocks  |   |  |
|---|---|---|---|
| size of items    | 0 | 1 |  &gt; 1 | 

- Characteristics and blocks identified for siftDown(int i)

|Characteristics  |  Blocks  |   |  |
|---|---|---|---|
| i  | 0 | &gt; 0 and &lt; count() |  count()|

- Characteristics and blocks identified for siftUp(int i)

|Characteristics  |  Blocks  |   |  |
|---|---|---|---|
| i  | 0 | &gt; 0 and &lt; count() |  count() |

The characteristics *size of items* are common to pop(), peek(), and count().
The characteristic of *i* is common to siftDown(int i) and siftUp(int i)

### 2. Statement coverage
Les cas de test qui utilisent un push() et puis un pop() déclenchent l'exception :
java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0.
Par conséquent, j'ai ajouté une condition if(count()==1) pour la méthode pop() et peek().
Suite à la correction, la coverage est 83%(44/53).
En suite, la méthode non utilisée rightChildIndex(int i) est enlevé. 
Le nouveau cas de test testPushPop6() est ajouté pour augmenter la coverage à 98%.

### 3. Logic coverage
Il n'y a pas de prédicat qui utilise plus de 2 opérateurs booléens dans mon code.

### 4. PIT
Mutation score :
>> Generated 37 mutations Killed 28 (76%)
>> Ran 105 tests (2.84 tests per mutation)

Live mutants :
1. siftDown(0); : removed call to fr/istic/vv/BinaryHeap::siftDown → SURVIVED
2. else if (count() == 1) : negated conditional → SURVIVED
3. return (i-1)/2; : replaced int return with 0 for fr/istic/vv/BinaryHeap::parentIndex → SURVIVED
4. int rightIndex = leftIndex + 1; : Replaced integer addition with subtraction → SURVIVED
5. if (compare(items.get(rightIndex), selectedE) < 0) : 
   1. changed conditional boundary → SURVIVED
   2. negated conditional → SURVIVED
6. if (compare(e, items.get(parentIndex)) < 0) : changed conditional boundary → SURVIVED
7. if (compare(selectedE, e) < 0) : changed conditional boundary → SURVIVED
8. if (compare(e, items.get(parentIndex)) < 0) : changed conditional boundary → SURVIVED

Les nouveaux cas de test sont ajoutés : testPushPopPeek5() et testPushPop7() qui augmentent le mutation score à 86%.
>> Generated 35 mutations Killed 30 (86%)
>> Ran 91 tests (2.6 tests per mutation)
