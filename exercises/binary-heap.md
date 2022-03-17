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

##### 1. Utilisation de l'input space partitioning 
Pour chacune des méthodes présentées dans la classe BinaryHeap, nous nous appuyons sur les spécifications données par le TP pour établir nos premiers tests. Ces derniers tiennent compte des différentes méthodes proposées. Les blocs identifiés dans le cas des arbres binaires portent sur la possibilité que les valeurs (Integer) soient négatives, positives ou vides au départ. 
Nous allons donc établir dans un premier temps deux tests pour chaque spécification présentée par le TP. La première reprenant les données valides et l'autre celles non valides. Nous nous assurons aussi que les tests qui doivent échouer lèvent les bonnes exceptions. Afin de s'assurer du fonctionnement, trois listes sont créées : une vide, une contenant des nombres aléatoires et une dernière contenant uniquement des doublons. 

##### 2. Evaluation de la couverture des instructions des cas de test
Le taux de couverture est de 100% pour les lignes de code. Il n'est pas nécessaire de rajouter des tests à ce niveau. 

##### 3. Prédicat utilisant plus de deux opérateurs booléens
Nous ne rencontrons pas ce cas de figure ici. Nous avons néanmoins dans la méthode `rearrangeMaxHeap()` trois conditions if venant chacune vérifier quelle est la plus grosse valeur afin de déplacer cette dernière au nœud parent. Nous nous sommes assurés dans les tests précédemment écrits que nous passons dans ces différentes parties du code. 

##### 4. Utilisation de PIT pour évaluer la suite de test
A la première utilisation de la commande `Pitest:mutationCoverage`, nous atteignons un taux de couverture de mutation de 85%, soit 27 mutants créés et 23 tués. 
Ainsi à la lecture du rapport, nous avons pu constater que le mutant ou les mutants se rapportant aux lignes suivantes n'étaient pas tués : 
```java
for (int i = nbElement; i >= 0; i--){
    rearrangeMaxHeap(nbElement,i);
    // ...
}
```    

Nous avons décidé de rajouter un cas de test afin d'affiner notre suite de test : 
```java
@Test
public void testSortEmptyBinaryHeap2(){
    binaryHeapEmpty.push(90);
    binaryHeapEmpty.push(0);
    binaryHeapEmpty.sort();
    assertEquals(binaryHeapEmpty.count(), 2);
    assertEquals(binaryHeapEmpty.peek(), 0);
}
```
L'ajout de ce cas de test améliore considérablement notre taux de couverture car nous avons maintenant 26 mutants tués pour 27 mutants créés, soit un taux de 96%.  
