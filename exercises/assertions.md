# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1 - This assertion fail because the way float and doubles are stored in java leads to imprecision, in our example 3 * .4 = 1.2000000000000002 in java

In order to fix this, we can do a compare function with an error margin or we can use BigDecimal to store decimal values.

2 - assertEquals compare the values of the specified objects and assertSame compare the references of the specified objects

Example :
int a = 3;
int b = 3;
assertEquals(a,b) => true
assertSame(a,b) => false

assertEquals(a,a) => true
assertSame(a,a) => true

3 - We can call fail() assertion in a catch of a try .... catch ... to catch unexpected exceptions occurred during tested code in order to fail the test

Use case : - We want to test a method which can throw exception but our test case should not throw exceptions
- We want to catch exceptions if they occurs and fail the test with the fail assertion

```java
@Test
public void unexpectedException() {
    try {
        method();
        // more testing code
    } catch (Exception e) {
        fail("Unexpected exception was thrown");
    }
}
```

5 -

assertThrows() upgrades code lisibility

It allows you to test multiple exceptions in same test method

Moreover you can test if the wanted line triggers the exception
Example:
```java
@Test
public void testInvalidData(){
    prepareTestData();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
        userService.fetchUser(1234);
    });
}
```



