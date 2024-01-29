# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. En binaire, les nombres flottants sont stockés avec la méthode de la virgule flottante, qui a pour inconvénient que certains nombres décimaux ne peuvent être représentés, mais seulement une approximation. Dans le cas `assertTrue(3 * .4 == 1.2)`, cela ne marche pas car 0.4 ne peut être stocké directement, et c'est donc une approximation qui est multipliée par 3, ce qui ne donne pas la même valeur que 1.2 ou son approximation. Pour que cette comparaison fonctionne, il faut utiliser soit une inégalité avec un delta, ou utiliser `assertEquals(1.2, 3 * 0.4, delta)`

2. `assertEquals` test l'égalité entre 2 objets en utilisant la méthode equals des objets.
`assertSame` test l'égalite entre 2 objets en vérifiant qu'ils ont la même adresse mémoire.
Ces fonctions produisent le même résultat dans le cas où la classe des objets à comparer n'a pas redéfini la méthode `equals` car, par défaut, elle vérifie l'adresse mémoire pour assurer l'égalité.
Si la méthode `equals` est redéfinie pour utiliser autre chose que l'adresse mémoire pour comparer les objets (une implémentation bidon peut être qu'elle renvoie true tout le temps) et qu'on compare pas les 2 mêmes objets, les méthodes `assertEquals` et `assertSame` ne renverront pas le même résultat.

3. De le même manière qu'il est possible de vérifier qu'une exception a bien été lancée, il est possible de vérifier qu'il y ait bien eu un return avant comme dans le code suivant
   ```java
   int value = randomInteger();
   for (int i = 0; i < 5; i++) {
        // returns when (value + i) is an even number
        if ((i + value) % 2 == 0) {
            return;
        }
    }
    fail("Should have returned before");
   ```
Il est aussi possible de vérifier qu'une méthode n'a pas lancée d'exception en plaçant le `fail` dans un catch ou d'envoyer une erreur tant que le test n'est pas encore implémenté.

4. Grâce à la nouvelle méthode `assertThrows` , il est notamment possible de vérifier que l'exception lancée se trouve dans une certaine partie du code alors qu'avec la version de JUnit4, le test vérifie juste que l'exception est lancée quelque part dans le code. Ça permet de controler quelle méthode lance l'exception là où précédemment il n'était pas possible.
Cela permet aussi une unifomisation des méthodes d'assertion dans JUnit5, où toutes les méthodes d'assertion usuelles des méthodes assert alors qu'avant il y avait des méthodes assert comme assertEquals et des annotations servant de méthodes d'assertion.
