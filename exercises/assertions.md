# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. **Assertion Failure (assertTrue):**
   The assertion `assertTrue(3 * 0.4 == 1.2)` fails because floating-point arithmetic can lead to precision errors. In this case, the result of `3 * 0.4` might not be exactly equal to `1.2` due to the way floating-point numbers are represented in computers. Floating-point numbers are approximations and can have rounding errors.

   For such checks involving floating-point numbers, it's better to use an assertion that considers a small tolerance for floating-point imprecision. For example, you can use `assertEquals` with a delta parameter to specify an acceptable margin of error:

   ```java
   assertEquals(1.2, 3 * 0.4, 0.0001);
   ```

   The third parameter `0.0001` represents the maximum allowed absolute difference between the expected and actual values.

2. **Difference between assertEquals and assertSame:**
   - `assertEquals`: Compares the values of two objects for equality. It uses the `equals` method of the objects to perform the comparison. It is typically used for comparing values of primitive types or the contents of objects.

     ```java
     assertEquals("Hello", new String("Hello"));
     ```

   - `assertSame`: Compares the references of two objects to check if they refer to the same object in memory. It uses the `==` operator for comparison. It is used to verify that two references point to the exact same object.

     ```java
     String str1 = "Hello";
     String str2 = str1;
     assertSame(str1, str2);
     ```

   Scenarios where they produce the same result:
   - When comparing primitive values using `assertEquals`.
   - When comparing objects that override the `equals` method and have the same content.

   Scenarios where they do not produce the same result:
   - When comparing distinct objects with the same content, `assertEquals` may pass, but `assertSame` will fail.
   - When comparing distinct objects with different content, both assertions may fail.

3. **Other Uses for `fail`:**
   - **Marking Unreachable Code:** `fail` can be used to mark code that should not be executed, helping to ensure that certain branches of code are not reached.
  
     ```java
     public void myMethod(int value) {
         if (value < 0) {
             throw new IllegalArgumentException("Value must be non-negative");
         } else if (value > 100) {
             // This branch should not be reached
             fail("Value should not exceed 100");
         } else {
             // Code for valid input
             // ...
         }
     }
     ```

     In the above example, if the value is greater than 100, it indicates a logical error in the code, and `fail` helps to highlight that the condition should not be reached.

4. **JUnit 4 vs. JUnit 5 Exception Handling:**
   - **JUnit 4 (Expected Exception):** In JUnit 4, exceptions were expected using the `@Test` annotation with the `expected` attribute.

     ```java
     @Test(expected = MyException.class)
     public void testMethod() {
         // Code expected to throw MyException
     }
     ```

   - **JUnit 5 (assertThrows):** In JUnit 5, the `assertThrows` method provides a more flexible and readable way to check for expected exceptions.

     ```java
     @Test
     public void testMethod() {
         assertThrows(MyException.class, () -> {
             // Code expected to throw MyException
         });
     }
     ```

   **Advantages of `assertThrows` in JUnit 5:**
   - **Lambda Expression:** Allows for more complex exception-checking logic within the lambda expression.
   - **Improved Readability:** Clearly expresses the intent of checking for an exception within the test method.
   - **Supports Supplier:** The exception instantiation can be deferred until it is needed, providing better performance in some cases.
   - **Better Error Messages:** JUnit 5 provides more informative error messages on assertion failures.