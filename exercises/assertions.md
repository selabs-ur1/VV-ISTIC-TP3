# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Cette assertion n'est pas valide car l'une des valeur n'est mathématiquement pas égale à l'autre à cause des valeurs flottantes - c'est à dire de chiffres après la virgule. 
Par exemple on ne pourra jamais avoir une assertion de ce type valide avec un nombre irrationnel Q′. Pour manipuler des assertions avec ces types, il faut utiliser un delta, qui 
sert de marge d'erreur. Par exemple, pour cette assertion, on souhaite surtout une marge à un centième :
```Java 
public class TestClass {
    // FAIL
    @Test
    void test1(){
        assertTrue(3*.4 == 1.20);
    }
    //SUCCEED
    @Test
    void test2(){
        // on compare avec un delta de 0.01 
        assertEquals(3*.4, 1.20,0.01);
    }
 }
```
2. assertEquals utilise la méthode equals() de la classe de l'objet et assertSame utilise l'opérateur ==.
assertEquals compare donc la valeur de chacun des objets ( par exemple la valeur des entiers dans les types primitifs)
alors que assertSame vérifie que chaque objet est le même, qu'ils pointent vers les mêmes emplacements en 
mémoire.
```Java
public class TestClass {
    private String a,b,c;
    @BeforeEach
    void init(){
        a = "randowWord";
        b = a;
        c =  new String("randowWord");
    }
    
    // Same values but different object -> FAIL and SUCCESS
    @Test
    void test1_1(){
        assertSame(c,a);
    }
    @Test
    void test1_2(){
        assertEquals(c,a);
    }

    // Same object and same values -> SUCCESS and SUCCESS
    @Test
    void test2_1(){
        assertSame(a,b);
    }
    @Test
    void test2_2(){
        assertEquals(a,b);
    }
}
```
3. On pourrait utiliser fail pour s'assurer qu'un cas mauvais renvoit une exception. Par exemple, insérer volontairement une donnée erronée qui provoque une division par 0 et au lieu que le système crash complètement,
renvoyer un Exception qui explicite la cause (la donnée insérée est mauvaise et non le calcul).
```Java
public class TestClass {

    private class BadDenominator extends Exception{ }

    double divise(double a, double b) throws BadDenominator {
        if (b==0) throw new BadDenominator();
        return a/b;
    }

    @Test
    void badDenominatorExceptionTest(){
        assertThrows(BadDenominator.class,() -> divise(0,0));
    }

}
```
4. L'avantage de spécifier un assertThrows, au lieu de juste mettre @Test(expected = Exception.class) est que c'est tout de même plus explicite et plus visible sur la sortie standard.