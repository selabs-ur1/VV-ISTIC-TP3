# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. En fonction de où nous lançons le test, il est possible que la virgule soit refusé et à la place il est demandé un point par exemple aux US.
il faudrait donc par précaution utiliser DecimalFormat. Il y a aussi la précission des floats, 0.4 n'est pas exactement égale à 0.4 donc assertEquals(double,double,delta) est plus utile étant donné qu'on peut avoir l'insertitude. 

2. assertSame vérifie si l'objet est le même mais aussi l'emplacement en mémoire des deux. assertEquals quant à lui vérifie la valeur (avec isEquals()) mais aussi avec le type de l'objet.  
Nous pouvons avoir comme scénario : 
Si nous avons 2 objets de même type ( par exemple A), si on override pas la méthode equals() alors assertEquals et assertSame comparent tous les deux si les deux objets proviennent de la même adresse. Mais si on override alors assertEquals compare si les deux objets ont la même valeur d'objet.

3. On peut utiliser fail() lorsque un test est incomplet ou lorsque nous voulons retourner une exception dans un try catch. Par exemple, nous voulons éxécuter un code et si nous nous attendions à une exception mais nous en avions pas eux, le fail() nous permet de stopper le test.

```java
 try {
        foo.add(NIL);                      
        fail("No NullPointerException");               
     } catch (NullNullPointerException e) {
        // OK got the expected exception
    }
```
4. 