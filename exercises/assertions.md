# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The assertion fails because of floating-point arithmetic. The correct way to check this is to use `assertEquals(1.2, 3 * .4, 0.0001)`. 
This way, the assertion will pass if the difference between the expected and actual values is less than 0.0001.

2. `assertEquals` compares the values of two objects (using the .equals() method), while `assertSame` compares the references of two objects.

Example where the assertions don't produce the same result:
```java
class DifferentResultsTest {
    final String a1 = new String("test");
    final String a2 = new String("test");

    @Test
    void testAssertSame() {
        assertSame(a1, a2); // AssertionFailedError: expected: String@e720b71[test] but was: String@1b26f7b2[test]
    }

    @Test
    void testAssertEquals() {
        assertEquals(a1, a2); // Success
    }
}
```
Example where the assertions produce the same result:
```java
class SameResultsTest {
    String a1;
    String a2;

    @BeforeEach
    void setUp() {
        a1 = a2 = new String("test");
    }

    @Test
    void testAssertSame() {
        assertSame(a1, a2); // Success
    }

    @Test
    void testAssertEquals() {
        assertEquals(a1, a2); // Success
    }
}
```

3. `fail` can be used to test preconditions, such as values that should be set in the setUp method :

```java
class PreconditionTest {
    Object obj;

    @Before
    void setUp() {
        obj = new Object();
    }

    @Test
    void testOnMyObject() {
        if(obj == null) {
            fail("obj should not be null");
        }

        // Rest of the test
    }
}
```

It can also be used to mark a test as incomplete or to indicate that a certain branch of code is not yet implemented.

```java
class ToImplementTest {
    @Test
    void testNotImplemented() {
        fail("Not yet implemented");
    }
}
```

4. `assertThrows` allows to test exceptions more precisely without the need of a lot a boilerplate code. Previously, using junit 4, testing an exception's message required more code, with a "rule" system.

Now with `assertThrows`, it is possible to test the exception message directly in the assertion. It also avoids the need for try-catch blocks in the test method, making the code cleaner and more concise.

The new assertion is placed directly in the test method's body, amongst the other assertions. To a human reader, this improves the readability: the oracle isn't split in two places as before (the method's annotations and body).