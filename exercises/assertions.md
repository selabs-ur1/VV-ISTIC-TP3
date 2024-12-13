# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Le problème est que la comparaison est strict or, le résultat exact est ``<1.2000000000000002>`` donc l'égalité n'est pas respecté.
   De plus c'est plus approprié d'utiliser ``assertEquals()``. Cependant là aussi l'égalité ne sera pas respecté à cause du même problème mais, pour cela on peut préciser une précision : ` assertEquals(3 * .4,1.2,0.001);` ici on a une précision de 0.001 donc one ne prend que le résultat comme : 1.200

2. 
- ``AssertEquals`` utilise l'égalité de valeur/contenu, par exemple si une classe a une methode ``equals``, elle sera utiliser pour la comparaison.
- ``AssertSame`` Compare l'adresse en mémoire, si 2 objets ont la même adresse en mémoire alors ils sont strictement identiques.
### Le cas ou les 2 assert renvoie la même chose
Ici str1 contient le string ``test``et str2 contient l'adresse mémoire de str1. Donc le contenu des 2 variable est bien test (comparé par assertEquals) et l'adresse mémoire est aussi égal.
`````java
@Test
public void testAssertSameEtEquals() {
    String str1 = "test";
    String str2 = str1; 
    
    assertEquals(str1, str2); 
    assertSame(str1, str2);   
}
`````
Ici on crée un nouveau objet String, leur contenu est identique mais pas leur adresse en mémoire
`````java
@Test
public void testAssertNotSameEtEquals() {
    String str1 = new String("test");
    String str2 = "test"; 
    
    assertEquals(str1, str2); 
    assertSame(str1, str2);   
}
`````

3.
Si fail est utiliser pour vérifier que quelque chose supposé impossible se passe ou pas.
````java
@Test
    public void testExempleFail() {
        int result = methodeQuiNeRenvoieQueDesNombrePositifs();
        if (result < 0) {
            fail("Le resultat est négatif");
        }
    }
````

4.
Avoir une assertion plutôt que le test dans le @Test améliore la lisibilité, permet de plus facilement tester l'exception renvoyé 
et de la combiner à plusieurs autres assertions.