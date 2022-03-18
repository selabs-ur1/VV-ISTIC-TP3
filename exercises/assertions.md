# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1) Cette assertion n'est pas correcte car nous essayons déjà de comparer des doubles. Il faudrait comparer 
avec un Delta : une valeur très petite comme 0.000001 par exemple. 
De plus, l'utilisation d'assertTrue n'est pas judicieuse quand nous pouvons utiliser assertEquals.

Le résultat peut être :

    assertEquals(3 * .4, 1.2, 0.0000001);

2) AssertEquals permet de savoir si 2 objets sont les mêmes (mêmes valeurs) tandis que AssertSame permet 
de savoir si 2 objets réfèrent le même objet (ont la même référence en mémoire).

Par exemple :
Chien c = new Chien(); --> assertSame(c, c) sera vrai, assertEquals(c, c) également.

Chien c1 = new Chien(); Chien c2 = new Chien(); --> 
assertSame(c1, c2) sera faux, 
assertEquals(c, c) vrai si on ajoute le @Override equals dans la classe chien, sinon faux également.

Il n'y pas de cas ou assertSame est vrai mais pas assertEquals.

3) La méthode fail est souvent utilisée de manière à jeter une exception. Mais elle eut l'être aussi pour
signaler qu'un test n'a pas été encore implémenté :


    @Test
    public void incompleteTest() {
        fail("Not yet implemented");
    }


De plus, il est possible de l'utiliser lors d'une exception non voulue :

    @Test
    public void unexpectedException() {
        try {
            safeMethod();
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }
    }

Egalement, nous pouvons l'utiliser lorsque le résultat d'une condition if n'est pas désiré :

    @Test
    public void testingCondition() {
        int result = randomInteger();
        if(result > Integer.MAX_VALUE) {
            fail("Result cannot exceed integer max value");
        }
    }

Enfin, nous pouvons l'utiliser pour fail un test qui aurait du être retourné :

    @Test
    public void returnBefore() {
        int value = randomInteger();
        for (int i = 0; i < 5; i++) {
            // returns when (value + i) is an even number
            if ((i + value) % 2 == 0) {
                return;
            }
        }
        fail("Should have returned before");
    }

4) L'assertThrows est selon nous, plus lisible et plus flexible car il permet de vérifier directement
l'exception dans le corps du test et tester une partie précise du code. 
De plus, il est possible avec le assertThrows d'ajouter ensuite d'autres assertions à la suite. 
Il y a également la possibilité de mettre un message.
