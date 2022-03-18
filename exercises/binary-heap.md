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

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
 - Dans un premier temps, nous avons identifié les blocs qui doivent être conçu. vérifier si le binaryheap est rempli, si binaryHeap n'est pas vide. Ensuite nous avons commencé à écrire des test : 


```java
    @Before
    public void setup() {
        // créer un tabelaua vec 4 elements 
        heap = new Integer[4];
        //initaliser unn binaryHeap avec 4 elemnt 
        binaryHeap = new BinaryHeap<Integer>(compa, heap);
    }
    /**
     * Test de la fonction pop 
     * */
    @Test
    public void testPop(){

        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);

        assertEquals(1, binaryHeap.pop());
        assertEquals(false, binaryHeap.isFull());
        assertEquals(false, binaryHeap.isEmpty());
    }
    @After
    public void tearDownPop(){

    }

}
```

Nous avons égelement identifié les caractéristiques communes entre méthodes: ajout d'element doit l'ajouter en bas de BinaryHeap, et on déplace les élément vers le haut si nécissaire tout en comparant avec l'élément parent. Vice-versa pour supprimer un élément.  

2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
    - Nous avons procédé de cette manière :

        - Ecrire la méthode de testPop
        - implémeter la methode Pop()
        - Ecrire le code test de la méthode push testPush()
        - Implémenter la methode push. 

Nous avons ajouté des méthodes comme vérifier si le BinaryHeap est plein, vide. 

```java
class BinaryHeap<T> {

    private int heapSize;
    private T[] heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Constructor
     **/
    public BinaryHeap(Comparator<T> comparator, T[] heap) {
        heapSize = 0;
        this.comparator = comparator;
        this.heap = heap;

    }

    /**
     * get minimum from HeapBinary
     * @returns and removes the minimum object in the heap.
     * If the heap is empty it throws a NotSuchElementException
     */
    public T pop() {
        if(isEmpty()) throw new NoSuchElementException("Heap is empty");

        T minimum = this.heap[0];
        delete(0);
        return minimum;
    }

        /**
     * Check if BinaryHeap is empty
     * @return
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * check if binaryHeap is full
     * @return Boolean
     */
    public boolean isFull() {
        return heapSize == heap.length;
    }

    /**
     * @param index
     * @return index parent of index
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Function to get index of k th child of i
     * @param index child
     * @param k k=1 left child k=2 right child
     * @return index of k th child of index
     */
    private int kthChild(int index, int k) {
        return 2 * index + k;
    }
```

Quand on ajoute un élément, on l'ajoute à la fin de BinaryHeap, et réorganise l'arbre. en appelant la méthode heapifyUp qui permet de réorganiser l'arbre en remontant dans l'arbre et en comparant avec l'élément parent et en échangeant si nécessaire.
Quand on appelle la méthode Pop() qui permet de récupérer le minimum et le supprimer. pour supprimer l'élément on doit appeler la méthode Delete() qui elle appelle la méthode heapifyDown qui permet de réorganiser l'arbre. la suppression d'un élélement 
d'un élément est éffectué en échangeant l'élément supérieur avec le dernier élément en bas de l'arbre, en retirant le dernier élément, puis en descendant le nouvel élément supérieur vers le bas pour maintenir la propriété de BinaryHeap.

```java
    /**
     * Function to remove element at an index
     * Remove element and call HeapifyDown function
     * @param index of element to remove
     * @return element removed
     */
    public T delete(int index) {
        if (isEmpty()) throw new NoSuchElementException("BinaryHeap is empty");
        T keyItem = heap[index];
        heap[index] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(index);
        return keyItem;
    }

    private void heapifyDown(int index)
    {
        int child;
        T tmp = heap[index];
        while (kthChild(index, 1) < heapSize) {
            child = minimumChild(index);
            if (comparator.compare(heap[child],tmp)==-1)
                heap[index] = heap[child];
            else
                break;
            index = child;
        }
        heap[index] = tmp;
    }

```

3.  If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
- Tous les tests que nous avons faits n'ont pas d'opérateurs booléens multiples. Chaque assertion que nous
  avons faites est indépendante des autres même si elle peut être liée.


4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

   - Nous avons lancé la commande suivant: mvn test-compile org.pitest:pitest-maven:mutationCoverage
   - Le taux de code coverage est à 98% et le score de muation est à 66%. 

Nous avons rajouté des tests pour améliorer le taux de code coverage.  

```java
        /**
 * test push element in binaryheap full
 */
@Test
public void testPushNotOKBinaryHeapIsFull(){
        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);
        binaryHeap.push(8);
        binaryHeap.push(11);
        binaryHeap.push(19);

        assertEquals(true, binaryHeap.isFull());
        assertThrows(NoSuchElementException.class, () -> binaryHeap.push(5), "Heap is full");
        }
/**
 * test method peek not ok
 * peek element from the empty heap
 */
@Test
public void testPeekNotOKHeapEmpty(){
        assertThrows(NoSuchElementException.class, () -> binaryHeap.peek(), "Heap is empty");
        }

/**
 * test push element in binaryheap full
 */
@Test
public void testPushNotOKBinaryHeapIsFull(){
        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);
        binaryHeap.push(8);
        binaryHeap.push(11);
        binaryHeap.push(19);

        assertEquals(true, binaryHeap.isFull());
        assertThrows(NoSuchElementException.class, () -> binaryHeap.push(5), "Heap is full");
        }
```
Après l'ajout de ces tests nous sommes arrivé à 100% de taux de code coverage, mais malheuresment nous n'avons pas réussi à augmenter le taux de mutation. 