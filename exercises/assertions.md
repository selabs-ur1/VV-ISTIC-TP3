# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1:
Because of the nature of floats (like 0.4 and 1.2), 3 * 0.4 doesn't exactly equal 1.2 but instead gives an approximation due to small rounding errors.
To make the assertion work correctly, you need to use a function or method, such as math.isclose, to account for these small precision errors.

2:
assertEquals checks if values are logically equal, ignoring type differences, while assertSame checks if values are identical, including type and memory reference.
x = 1, y = 1
both assertEquals(x,y) and assertSame(x,y) give out true because they are both logically equal to one and also both integer.
x = 1, y = 1.0
assertEquals(x,y) gives out true while assertSame(x,y) gives out false since one is an integer and the other one a float.

3:
We can use it to mark code that should not be reached like if we check all condition possible with and if else statement
if(b == true) {
}
else if (b == false) {
}
else {
fail("unreachable code was reached")
}

4:
With the new way of checking if an exception was thrown you can also manipulate the exception further and be able to assert other elements of the exception, for exemple being able to tell that the proper message was given to the thrown exception.
