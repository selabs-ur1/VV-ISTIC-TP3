# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### 1.

The assertion fails due to floating-point precision issues in computer arithmetic. Computers may not represent decimal numbers exactly, leading to small rounding errors. To address this, it's recommended to use a tolerance or delta when comparing floating-point numbers. This involves checking if the absolute difference between the expected and actual values is within an acceptable range. Adjusting the tolerance allows for more flexible comparisons in tests, accommodating the inherent limitations of floating-point arithmetic.

### 2.

AssertSame() is comparing the references of 2 objects while AssertEquals() checks the values

### 3.

While the primary purpose of the fail method in testing frameworks is to mark code that should not be executed due to an expected exception, it can also be used in other scenarios where explicit failure is desired. One such use case is when a certain condition should never be true during the test, and its occurrence indicates an unexpected state.

Use Case: Asserting a Negative Condition

```
public void testNegativeValue() {
    int result = someOperationThatShouldNotProduceNegativeValues();

    if (result < 0) {
        fail("The result should never be a negative value.");
    }

    // Continue with other assertions or test logic
    assertEquals(42, result);
}

```

In this example, if someOperationThatShouldNotProduceNegativeValues() unexpectedly returns a negative value, the fail method is used to immediately mark the test as failed, preventing further unintended execution and providing a clear indication of the unexpected condition.

This usage of fail helps in identifying issues that might not be caught by regular assertions but are still crucial for ensuring the correctness of the tested functionality.

### 4. The assertThrows method in JUnit 5 offers several advantages over the older approach of declaring expected exceptions using annotations:

Precision and Clarity:

assertThrows allows for more precise and fine-grained assertions by specifying the expected exception type and providing a lambda expression for the code that should throw the exception. This makes the test code more explicit and readable.
Dynamic Exception Type Checking:

Unlike the annotation-based approach, assertThrows dynamically checks the type of the thrown exception at runtime, allowing for more flexibility in handling different exception scenarios within a single test.
Assertion on Exception Details:

assertThrows enables assertions on the exception itself, allowing for further verification of exception details, such as specific error messages or conditions related to the exception.
Support for Checked Exceptions:

While the annotation-based approach in JUnit 4 was limited to unchecked exceptions, assertThrows provides support for both checked and unchecked exceptions, expanding its applicability in various testing scenarios.
In summary, assertThrows in JUnit 5 offers a more expressive, flexible, and precise way of handling and asserting expected exceptions, enhancing the overall quality and readability of unit tests.
