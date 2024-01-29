Yoann Dewilde  
Enora Danilo  
M2 ILa - Groupe 1  

# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Les nombres décimaux ne sont pas exactement représentés en binaires. Ce qui fait qu'ici 3 * 0.4 ne va pas donner exactement 1.2. C'est donc pour cela que `assertTrue(3 * .4 == 1.2)` ne passe pas. Pour resoudre ce problème, il faut utiliser `assertTrue(Math.abs(resultat - valeurAttendue) < margeErreur)` afin d'indiquer une marge d'erreur. Dans notre cas il faudra donc écrire `assertTrue(Math.abs(3*0.4 - 1.2) < 0.000001)`.

2. La différence entre `assertEquals` et `assertSame` est que `assertEquals` va seulement comparer le contenu de 2 instances alors que `assertSame` va comparer les références mémoires de 2 instances.
    
    Exemple :

```Java
int a = 8;
int b = 8;
assertEquals(a,b);
assertSame(a,b);
```
Ici les assertions `assertEquals` et `assertSame` passent car les int inférieurs à 127 possèdent la même référence mémoire en Java et que les deux variables possèdent les mêmes valeurs. Les deux variables possèdent donc la même référence mémoire.

```Java
int a = 8;
int b = 9;
assertEquals(a,b);
assertSame(a,b);
```

Ici l'assertion `assertEquals` ne passe pas les deux variables ne possèdent pas les mêmes valeurs.
L'assertion `assertSame` passe car comme dit précédement les int inférieurs à 127 possèdent la même référence mémoire, donc a et b possèdent la même référence mémoire.


3. On peut utiliser `fail` avec un message d'erreur pour sortir du test si une valeur inatendue est produite.

    Exemple :  
    Ici nous retournons un message d'erreur si l'une des deux variables possède un nombre négatif.

```Java
@Test
public void test() {
    int a = 42;
    int b = -9;
    if (a >= 0 && b >= 0) {
        System.out.println("Les deux nombres sont supérieur ou égal à 0");
    } else {
        fail("L'une des deux variables contient un nombre négatif");
    }
}
```

4. `assertThrows` permet de capturer l'exception elle-même et d'opérer dessus alors qu'avant en JUnit 4 il était seulement possible de vérifier si la bonne exception était relevée. `assertThrows` est également compatible avec les lambdas.
