# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The assertion `assertTrue(3 * 0.4 == 1.2)` fails because floating-point numbers may not always represent decimal values exactly due to precision limitations.

   To check floating-point numbers for equality, it's better to use a delta value. 
For example:
   ```java
   double result = 3 * 0.4;
   double expected = 1.2;
   double delta = 0.0001; // Set an acceptable margin of error
   assertTrue(Math.abs(result - expected) < delta);
   ```

2. 
   - `assertEquals`: It checks whether two objects are equal in terms of content/values. It uses the `equals()` method to compare the values.
   - `assertSame`: It checks whether two references refer to the exact same object, i.e., they point to the same memory location.

         Scenarios:
   - **Same Result:**
     ```java
     String s1 = "hello";
     String s2 = new String("hello");
     assertEquals(s1, s2); // Same content
     assertNotSame(s1, s2); // Different references
     ```
   - **Different Result:**
     ```java
     String s1 = "hello";
     String s2 = "hello";
     assertEquals(s1, s2); // Same content
     assertSame(s1, s2); // Same references
     ```

3. - **Unreachable Code:**
  If certain code should never be executed under any circumstances, using `fail` can serve as a marker to indicate that this point in the code should not be reached.
  ```java
  public int divide(int a, int b) {
      if (b == 0) {
          fail("Division by zero not allowed");
      }
      return a / b;
  }
  ```

4. **Advantages of `assertThrows` in JUnit 5:**
    - **Clarity and Readability:**
      `assertThrows` provides a clearer and more expressive way to declare and check for expected exceptions. The annotation in JUnit 4 might not be as visually clear as a dedicated assertion method.
    - **More Granular Control:**
      `assertThrows` allows for more granular control over the exception handling process. You can capture the thrown exception and perform additional assertions on it if needed.
    - **Support for Lambda Expressions:**
      `assertThrows` allows the use of lambda expressions, making it more versatile and expressive in handling exceptions.
   ```java
   assertThrows(ArithmeticException.class, () -> divide(5, 0));
   ```