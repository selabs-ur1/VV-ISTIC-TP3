# On assertions

Answer the following questions.

## Question 1

The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.
 
## Answer 1

This is because floating point numbers should not be compared with `==` or `!=`, due to machine imprecision. Instead, the difference of the two numbers should be compared to some delta (the delta is the imprecision we allow as being equal).

```java
float delta = 0.01;
assertTrue((3 * .4) - 1.2 < delta);
```

## Question 2

What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

## Answer 2

Both methods are used to compare objects, but the way they opperate that comparison is not the same. `assertEquals` uses the `.equals()` operator of the objects and `assertSame` uses the `==` comparation operation to compare the memory adresses. As such, the two are not interchangeable.

## Question 3

In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

## Answer 3

When you create custom assertions, if the condition isn't met, the `fail` method can be used to signal a test failure.

## Question 4

In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer 4

In my opinion, the main advantages of the `assertThrows` method in comparison to the ` @Test(expected = ...)` annotation of JUnit 4 are twofold :
1. `assertThrows` allows to manipulate the thrown exception, to print relevant information or to handle any specific logic;
2. `assertThrows` is type-safe, and as such, is safer to use to avoid runtime exceptions.
