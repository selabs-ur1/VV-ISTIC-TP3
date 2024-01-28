# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The result of the expression 3 * .4 might not be precisely equal to 1.2 due to the limitations of floating-point representation and rounding errors.
   To address this issue, it is recommended to perform a floating-point comparison with a tolerance or delta value. Instead of checking for exact equality, compare whether the absolute difference between the two    values is within an acceptable range.

2. assertEquals will check values of two object and assertSame will check references
   Exemple :

    String str1 = "toto";
    String str2 = "toto";
    assertEquals(str1, str2); // Will pass because toto is the value of both String
    assertSame(str1, str2); // Will failed because it is 2 different String even if they have the same value
   
3. When we create a test and tag it as TODO, we can add a fail() statement to ensure that not all tests will succeed, and to prevent forgetting about any tests that have not been implemented.

4. With assertThrows, you can dynamically check for the specific type of exception thrown or details attached to the exception. And assertThrows utilizes lambda expressions, enabling a more concise and expressive syntax for specifying the code that is expected to throw an exception.
