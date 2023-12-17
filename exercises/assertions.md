# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. This assertion fails because of the floating point precision error. It could be use by comapring the difference between the two numbers and a set precision:
```java
float precision = 0.0000001;
assertTrue((3 * .4 - 1.2) <= precision);
```

2. AssertEquals compare the values of two objects (behavior defined by the equals method of an object) and AssertSame compares the references, insuring that both objects are the same and point to the same instance.

3. The `fail` statement can be used to verify conditions, or even verify that a return was executed:
```java
@Test
public void testReturn() {
  int n = randomInteger();
  for (int i = 0; i < 10; i++) {
    if (i + n % 2 == 0) {
      return;
    }
  }
  fail("Should have returned before this point");
}
```

4. Using `assertThrows` instead of a `@Test` annotation allows us to test the exception with a specific call instead of the whole block inside the test. It can also check the exception message sent.
