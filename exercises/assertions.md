# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. Operations on float are not the same as operations on real numbers. Floats approximate number and introduce precision errors and `3 * 0.4` might be equals to 1.20000…1 for example, which not equals to 1.2. To check the actual equality between float you could verify if the absolute value of the subtraction is lower than a value that is the acceptable difference :

```java
assertTrue(Math.abs(1.2 - (3 * 0.4) < 0.000001));
```

2. assertEquals check if the objects are equals according to their equals method, assertSame check if the objets have the same references.

3. We can use fail method to ensure that test we haven’t implement yet don’t pass, so when we try to run our test we don’t forget to implement them the other reason is that we can specify why the test fail, so it is easier to understand what went wrong.

4. In my opinion the main advantage is that we can easily test our custom Exception in our methods.
