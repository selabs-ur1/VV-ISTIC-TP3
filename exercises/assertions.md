# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes, we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

You can see our test in tp2-balanced-strings/src/test folders

1. It fails because 3 * .4 is equal to 1.2000000000000002. We should round the result.
Moreover, it is better to use `assertEquals` than `assertTrue`.
You will find failure test for this one.

3. `assertEquals` use `equals()` method of an object, so we need to override the method to see the difference with `assertSame`.
And this one asserts that two objects refer to the same object. These methods are better to use with object.

4. Commonly, `fail` assertions can be used for a test that is not finish to be implemented or not yet started to be. 
In class, we see this assertion for expected Exception, but it also can be use for an unexpected Exception. Finally,
we can use this assertion when a test has been expected to be failed when enter a specific condition, while, or something like that.
We add `fail()` to `AssertionTest.test` that should fail, you can try them without those instructions.

5. With Junit5 it is simpler to make test throwing exception, we can easily handle exceptions that can be thrown in many way in the code,
it is also easier to verify if the exception message is the one that is attempted.