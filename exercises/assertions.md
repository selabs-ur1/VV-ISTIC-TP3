# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Le problème vient de la précision des floats qui n'est pas connue. Il est impossible de faire des comparaisons avec "==" sur des floats. Pour faire ce type de vérification il faut un type de "assert" bien particulier et spécifiquement fait pour les float: "Assert.assertEquals(expected, actual, delta)" avec delta la différence maximale autorisée entre les deux nombres.

2. La différence entre `assertEquals` et `assertSame` est que "Equals" cherche à voir si deux objets sont égaux, alors que "Same" cherche à voir si deux objets référencent le même objet.

Pour montrer les scénarios, nous avons récupéré des exemples provenant de "stackoverflow.com".

Example 1: equals method was not overridden, so assertSame and assertEquals return the same result, since they compare the objects' reference.

public class A {
    private int i;
    public A(int i){ this.i = i; }
}

public class TestA {
    final A a1 = new A(0);
    final A a2 = new A(0);

    @Test
    public void assertsame_testAssertSame(){
        assertSame(a1, a2); // AssertionError: expected:<test2.A@7f13d6e> but was:<test2.A@51cdd8a>
    }

    @Test
    public void assertsame_testAssertEquals(){
        assertEquals(a1, a2); // AssertionError: expected:<test2.A@7f13d6e> but was:<test2.A@51cdd8a>
    }
}

Example 2: equals method was overridden, so assertSame and assertEquals return the not same result, since the equals method will be used by assertEquals this time.

public class A {
    private int i;
    public A(int i){ this.i = i; }

    @Override
    public boolean equals(Object o){
        // self check
        if(this == o){ return true; } else
        // null check
        if(o == null){ return false;} else
        // type check and cast
        if(getClass() != o.getClass()){ return false; } else {
            final A a = (A) o;
            // field comparison
            return Objects.equals(a, a);
        }
    }
}
public class TestA {
    final A a1 = new A(0);
    final A a2 = new A(0);

    @Test
    public void assertsame_testAssertSame(){
        assertSame(a1, a2); // AssertionError: expected:<test2.A@7f13d6e> but was:<test2.A@51cdd8a>
    }

    @Test
    public void assertsame_testAssertEquals(){
        assertEquals(a1, a2); // OK
    }
}


3. Peut être utilisé pour marquer un test incomplet par exemple, ou pour développer des assertions spécifiques à un projet, et générer un "fail" basé sur des test non encapsulés par d'autres méthodes.
Par exemple:
//pour tester si un String contient une valeur attendue
public void AssertStringContains(string expected, string actual, string message)
{
    if (actual.IndexOf(expected) < 0)
        Assert.Fail(message);
}

4. C'est plus clair d'avoir un "assertThrows" car au moins il indique clairement qu'il s'attend à récupérer une exception et donc c'est important c'est quelque chose est censé ne pas être autorisé de vérifier qu'effectivement, cela renvoie une exception. Avec seulement un "@Test" on ne comprend pas que c'est censé renvoyer une exception, mais plutôt c'est un cas de test comme un autre, ce n'est pas clair.

