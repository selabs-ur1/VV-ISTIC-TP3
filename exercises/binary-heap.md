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
1. - Test d'insertion
        - test d'un insertion
        - test de plusieurs insertions
        - test d'insertion après un pop
        - test d'insertion avec des numéros random
        - ...
        - Vérification de tout ces tests avec 

2.  Couverture a 93%, j'ai rajouté une méthode qui push 15 valeurs a la suite avant de faire 15 pop et de vérifier a chaque fois qu'on récupère bien la valeur attendue (testPopItAll).
    Ce test a permis de couvrir tous les cas de la méthode smallerChild qui permet de récupérer l'enfant avec le plus petit numéro pour le tri avec le parent (note, ce test kill aussi quelques mutants au pitest).
    La couverture est mainteant de 98%, il n'est pas possible de faire plus a cause de la dernière ligne de smaller child qui est une sécuritée au cas ou la méthode serait appelée sur un noeud sans enfant (ce n'est pas possible dans l'implé actuelle mais on sais jamais ce que les collègues peuvent bidouiller).

3.  Pas plus de deux prédicats :/

4.  Origine:
        - Line Coverage : 99% 66/67
        - Mutation Coverage : 88% 57/65
        - Test Strength : 90% 57/63
    
    Après ajout d'un test ajoutant une dixaine de nombres aléatoires dans la heap et qui vérifie ensuite qu'ils sont bien dans l'ordre attendu dans la liste heap deux mutants suplémentaire sont éliminés. Il s'agisait de la négation de la condition a la ligne 100 (if (i == 0)) et du remplacement par 0 du retour a la ligne 103 (même fonction).
    Le test d'intégritée de la heap complète a donc permis d'éliminer des mutants.

    Le nouveau score est:
        - Line Coverage : 99% 66/67
        - Mutation Coverage : 91% 59/65
        - Test Strength : 94% 59/63