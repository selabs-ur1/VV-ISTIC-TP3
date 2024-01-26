# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The assertion fails due to rounding errors associated with the representation of float numbers. To perform float verification, it's recommend using methods that compare numbers with a specified precision. `assertEquals` with a delta (margin of error) can work well. For example:
```java
double result = 3 * 0.4;
double expected = 1.2;
double delta = 0.0001; // Error margin

assertEquals(expected, result, delta);
```

2. `assertSame()` : checks the object reference using the == operator.
`assertEquals()` : If primitive values are passed and then the values are compared. If objects are passed, then the equals() method is invoked.
```java
BigDecimal b1 = new BigDecimal("1.0");
BigDecimal b2 = new BigDecimal("1.0");
BigDecimal b3 = b1;
 
@Test
public void BigDecimaltest() throws Exception {
  // if(b1 == b2)
  assertSame(b1, b2);    // THIS TEST WILL FAIL

  // b1.equals(b2)
  assertEquals(b1, b2);  // should pass

  // (b1 == b3)
  assertSame(b1, b3);    // will pass

  //(b1.equals(b3))
  assertEquals(b1, b3);  // will pass
}
```

3. A common use case is the creation of unit tests where certain parts of the code are not yet implemented, but the code signature is already in place. In this way, the test deliberately fails to remind developers that they must implement this part of the code.
```java
public class SampleClass {
    public int addNumbers(int a, int b) {
        // TODO
        fail("Not yet implemented");
    }
}
```

4. In JUnit 4, `@Test` didn't allow you to check the specific details of the exception. With JUnit 5, the introduction of `assertThrows` offers a more flexible and powerful way of handling expected exceptions. It allows you not only to check the type of exception, but also to inspect its details.