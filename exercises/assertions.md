# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes, we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### 1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

The assertion assertTrue(3 * .4 == 1.2) in Java is meant to check if the product of 3 multiplied by 0.4 is equal to 1.2. However, due to **floating-point precision issues** in computer arithmetic, the result might not be exactly equal to 1.2.

Floating-point numbers in computers can sometimes result in rounding errors, leading to imprecise calculations. In this case, the multiplication of 3 by 0.4 may not yield the exact value of 1.2 due to these inherent precision issues.

To perform this type of check correctly when dealing with floating-point numbers, you should use an assertion that accounts for a certain level of tolerance or acceptable difference, considering the precision limitations of floating-point arithmetic. 

```java
double result = 3 * 0.4;
double expected = 1.2;
double delta = 0.0001; // Tolerance level

assertEquals(expected, result, delta);
```
### 2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

`assertEquals` and `assertSame` are both assertion methods in JUnit, but they serve different purposes:

#### 1. Differences
##### assertEquals

- Purpose: Checks content equality.
- Method Signature: assertEquals(expected, actual)
- Comparison: Uses equals method.
- Example Scenarios:
  - Comparing two strings for equal content.
  - Verifying the equality of two instances of a custom class with overridden equals method.

##### assertSame
- Purpose: Checks reference equality.
- Method Signature: assertSame(expected, actual)
- Comparison: Uses == for reference check. 
- Example Scenarios:
  - Verifying that two references point to the same object instance.
  - Checking if an object retrieved from a method is the same instance as the one passed to the method.

#### 2. Scenarios
##### Same result
```java
int a = 5, b = 5;
assertEquals(a, b);  // Pass
assertSame(a, b);     // Pass
```
##### Different Result
```java
Object obj1 = new Object();
Object obj2 = obj1;
assertEquals(obj1, obj2);  // Pass
assertSame(obj1, obj2);     // Pass
```


### 3. In classes, we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

#### 1. Custom Assertions
When creating custom assertions, fail can be used to indicate a failure when a specific condition is not met.
```java
public static void assertPositive(int number) {
    if (number <= 0) {
        fail("Expected a positive number, but got: " + number);
    }
}
```
#### 2. Unreachable Code
Marking code as unreachable can be done with fail to emphasize that it should not be executed.
```java
public void processData(String input) {
    if (input == null) {
        fail("Input should not be null");
    } else {
        // Process the input
    }
}
```
#### 3. Testing Incomplete Features
In test cases where certain features are not fully implemented, fail can be used to indicate that the test is intentionally incomplete.
```java
@Test
void testIncompleteFeature() {
    // ... Test setup
    fail("Test incomplete: Feature under development");
    // ... Test execution
}
```

### 4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?
`assertThrows` in JUnit 5 offers advantages over JUnit 4's annotation-based exception handling:

1. Readability:

    `assertThrows` provides clear and readable code compared to annotations.

2. Granular Checks:

    Allows detailed inspection of thrown exceptions.

3. Improved Failure Messages:

    Offers informative failure messages for better debugging.

4. Lambda Expressions:

    Supports concise and expressive exception-checking code.

5. Java 8+ Compatibility:

    Works seamlessly with modern Java versions.

6. Consistency:

    Promotes a consistent approach to assertions.

7. Decoupling:

    Separates code invocation from exception assertion.

Overall, `assertThrows` enhances testing flexibility, readability, and maintenance.
