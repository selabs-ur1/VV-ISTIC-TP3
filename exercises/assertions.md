# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### Question 1

The problem here is a rounding problem. Calculate with float is an
approximation in IT. We should check with a delta to manage the acceptable error.

```java
class TestExample(unittest.TestCase):
    def test_float_comparison(self):
        result = 3 * 0.4
        expected = 1.2
        tolerance = 1e-9  # Adjust the tolerance based on your specific needs

        self.assertTrue(abs(result - expected) < tolerance)
```

### Question 2
AssertEquals use the method equals of an object.
AssertSame use the reference of the object

Here is the scenario that produce same result
```java
String str = "hello";
String expected = str;
String actual = str;
assertSame(expected, actual);
assertEquals(expected, actual);
```


Here is the scenario that do not produce the same
```java
String expected = "hello";
String actual = new String("hello");
assertSame(expected, actual);
assertEquals(expected, actual);
```


### Question 3

It's useful to indicate not implemented test.

```java
@Test
public void testIncompleteFeature() {
    // TODO: Implement this feature
    fail("Test case is incomplete - feature not implemented yet");
}
```

It also can help when specifying fail under conditions.

```java
@Test
public void testConditionalFailure() {
    if (shouldFailUnderCertainCondition()) {
        fail("Test failed due to a specific condition");
    }

    // Rest of the test logic
}

private boolean shouldFailUnderCertainCondition() {
    // Logic to determine whether the test should fail
    return true; // For the sake of example
}
```

### Question 4

According to me the new way is better because it's easier to 
understand what the test should do.


