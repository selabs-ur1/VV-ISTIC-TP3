# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. It fails because operation on double makes not the exact value, so it should be tested with an aproximative result range, for example `Math.abs(3 * .4 - 1.2) < 0.01`

2. assertEquals run the `equals` method while assertSame run the `==` method

```assertEquals("a","a")
assertSame("a","a") 
```

On both cases it return true since "a" == "a" and ("a").equals("a") are both true

```
String t1 = new String("a")
String t2 = new String("a")
assertEquals(t1,t2)
assertSame(t1,t2)
```

On the equals, it runs t1.equals(t2), wich return true since their content are equals
On the same, it runs t1 == t2, wich return false since they are two different objetcs.

3. fail can be used to ignore failed tests to fix them later while checking other tests, like for complicated tests wich require long investigation time.

```
ComplexObject c1 = new ComplexObject("a","d",2)
ComplexObject c2 = new ComplexObject("a","d",2)
fail() // TODO : investigate fix
assertEquals(c1,c2) // return true on V1.0.2, return false with fix 42
```

4. the advantages of this new way of checking exception is for developpers, assertThrows is more explicit than @Test for exceptions.
