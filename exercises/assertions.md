# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1.  L'utilisation d'un assertTrue avec une comparaison de deux doubles n'est pas appropriée, assertEquals aurait été plus judicieux. Et pour la comparaison de 2 double il faut utiliser un delta pour avoir un environ égal entre 2 double. 

2.  assertSame permet de vérifier que 2 objet référence le même objet, tandis que assertEquals vérifie que 2 objets sont égaux. 
    ```
    Personne p1 = new Personne(); 
    Personne p2 = new Personne();  
    
    @Test
            void assertSame_true() {
                assertSame(p1, p1); // return true
            }
    @Test
            void assertSame_false() {
                assertSame(p1, p2); // return false
            }
            
    @Test
            void assertEquals_true() {
                assertSame(p1, p1); // return true ( si seulement on ajoute le @Override equals() dans la classe personne )
            } 
    ```

3.  On peut aussi utiliser `fail` pour retourner un message après une condition par example :
    ```
    @Test
    public void testingCondition() {
        int result = randomInteger();
        if(result > Integer.MAX_VALUE) {
            fail("Result cannot exceed integer max value");
        }
    }
    ```
    Ou alors pour retourner une erreur si la fonction n'a pas été break/return quand c'était voulu, example : 
    ```
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
    ```

4.  La méthode assertThrows() permet un contrôle plus précis de la logique d'assertion d'exception car nous pouvons l'utiliser autour de parties spécifiques du code. Avec `@Test` on peut déclarer une execption qui doit être levée ``` @Test(expected = NullPointerException.class) ``` mais pour déclarer plusieurs exceptions on doit passer par ExpectedException rule. 
