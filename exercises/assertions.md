# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer


## Enoncé 1:
*The following assertion fails assertTrue(3 * .4 == 1.2). Explain why and describe how this type of check should be done.*


3 est un nombre décimal, or, certains nombres decimaux (comme 3 par exemple) ne peuvent pas être représentés de manière exacte
en nombre à virgule en raison de leur représentation binaire. 
Cela peut ainsi induire des imprecisions dont le decimal est arrondi.
Pour résoudre ce problème on peut par exemple calculer la difference entre la valeure attendue et la valeure obtenue et accepter une taille limite de cette différence :

```
double result = 3 * .4;
double expected = 1.2;
double difference = 0.000001;
assertTrue(Math.abs(result - expected) < difference);
```


## Enoncé 2:
*What is the difference between assertEquals and assertSame? Show scenarios where they produce the same result and scenarios where they do not produce the same result.*

Sur des objets, assertSame va comparer l'adresse de chaque objet, alors que assertEquals va comparer le statut de chaque objet.
Pour un objet 
```
class Test { public int a; }
``` 

Le Scenario suivant est identique pour les 2 asserts (ils passent tout les deux) :
```
Test test1;
Test test2;
test1.a = 1;
test2 = test1;
assertEquals(test1, test2);
assertSame(test1, test2);
```

le Scenario suivant est différent pour les 2 asserts (Equals passe mais pas Same) :

```
Test test1;
Test test2;
test1.a = 1;
test2.a = test1.a;
assertEquals(test1, test2);
assertSame(test1, test2);
```
    

## Enoncé 3:
*In classes we saw that fail is useful to mark code that should not be executed because an exception was expected before. Find other uses for fail. Explain the use case and add an example.*
Nous pouvons utiliser l'assertion fail afin de marquer le code par exemple :
    - non atteignable (une fonction pas encore implémentée)
    - une division par zero
    - le default d'un switch case, si tout les cas ont été gérés auparavant dans le switch
    - dans un try catch
    - verifier des pre-conditions 

exemple avec un try catch :
```
public void test() {
    try {
        otherMethod();
    } catch (Exception e) {
        fail("Unexpected exception was thrown");
    }
}
```

exemple avec un switch case :

```
switch(choix){
    case 1: 
        System.out.println("Bonjour");
        break;
    case 2:
        System.out.println("Hello");
        break
    default:
        fail("Unexpected exception was thrown");
        break;
    }
```

## Enoncé 4:
*In JUnit 4, an exception was expected using the @Test annotation, while in JUnit 5 there is a special assertion method assertThrows. In your opinion, what are the advantages of this new way of checking expected exceptions?*

Dans Junit4, il fallait déclarer 

```
@Test(expected = NullPointerException.class)
``` 

afin d'intercépter une exception. Depuis Junit5, le assertThrows permet de detecter une exception de manière plus cohérente avec les autres assert qui existent, mais aussi detecter à quel ligne exactement l'erreur est soulevée.
