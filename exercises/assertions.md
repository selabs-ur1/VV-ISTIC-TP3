# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

##### 1. 
L'assertion ```assertTrue(3 * .4 == 1.2)```échoue car le résultat obtenu par ce calcul n'est pas le bon. En effet, normalement nous obtenons un double rendant cette valeur : 1.2000000000000002
Ainsi, dès que l'on effectue des calculs impliquant des doubles ou des floats, il vaut mieux employer assertEquals et indiquer le delta maximum entre la valeur attendue et la valeur réel pour lequel les deux nombres sont toujours considérés comme égaux : ```assertEquals(1.2,3.0 * 0.4, 1e-15);```

##### 2.
La différence entre assertEquals et assertSame porte sur le fait qu'```assertEquals()``` vient vérifier si deux objets sont égaux, c'est-à-dire que leurs valeurs sont identiques. Dans le cas de valeurs primitives, les valeurs sont comparées. Cependant, s’il s'agit d'objets, c'est la méthode equals() qui est alors invoquée. ```AssertSame()``` vérifie si les deux objets font référence à la même entité, au même objet. 

Exemple : 
```java
    private Float d1 = Float.valueOf(("3.0"));
    private Float d2 = Float.valueOf(("3.0"));
    private Float d3 = d1;

    @Test
    public void testDecimal() {
        assertSame(d1, d2);    // Ce test va échouer

        assertEquals(d1, d2);  // Le test passe

        assertSame(d1, d3);    // Le test passe

        assertEquals(d1, d3);  // Le test passe
    }
```

##### 3.
Fail peut être utilisé pour vérifier qu'une exception réelle est levée ou pour faire échouer un test lors de son développement. Il est aussi possible de préciser un message à fail ```fail(string message)``` afin de transmettre certaines informations dans la console. 

Dans le user case présenté nous allons vérifier si l'exception est bien levée. En cas d'échec, cela affichera le message d'erreur suivant dans le Failure Trace :  ``` java.lang.AssertionError: Should have thrown an exception ```

Exemple : 
```java
public int largest(final int[] list) {
    int index, max = Integer.MAX_VALUE;
    for (index = 0; index < list.length - 1; index++) {
        if (list[index] > max) {
            max = list[index];
        }
    }
    return max;
}

@Test
public void testEmpty() {
    try {
        largest(new int[] {});
        fail("Should have thrown an exception");
    } catch (final RuntimeException e) {
        assertTrue(true);
    }
}
```

##### 4.
```AssertThrows()``` permet simplement de tester plusieurs exceptions dans le même test. Si aucune exception n'est levée ou si une exception de type différent est jetée, cette méthode échoue. Dans le cas de JUnit 4, il fallait utiliser un attribut de l'annotation @Test ou protéger le code dans un bloc try/catch. Le problème du @Test vient du fait que l'utiliser est considéré comme une mauvaise pratique. En effet, le code peut lancer une exception à d'autres endroits non attendus et le test passera toujours. 



