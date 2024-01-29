# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

Authors: Dufeil Jaufret & Gentile Brian

1. Multiplication by a float leading to a difference between the expected and the actual result. We should test the actual result with a margin error to take into account even the smallest difference.

2. `assertEquals` uses equals() so it check the values of the objects whereas `assertSame` uses ==, it checks the objects in themselves.
   
   Same result :
   ```java
   String s1 = "Hello World";
   String s2 = s1;

   assertSame(s1, s2);
   assertEquals(s1, s2);

   //Both are true because they are the same object and have the same content

    ```

    Different result :
    ```java
   String s1 = "Hello World";
   String s2 = new String("Hello World");

   assertSame(s1, s2);
   assertEquals(s1, s2);

   //assertEquals is true because they have the same content but assertSame
   // is false because they are different object

    ```

3. We can use also use `fail` to avoid unimplemented features, test conditions, avoid unexpected cases,...

   Unexpected cases:
   ```java
   @Test
   public void UnexpectedFail(int value) {
      if (value < 0) {
         // value expected
         // some code, will exec
      } else {
         // value not expected
         fail("Unexpected case");
      }
   }

   ```
   Unimplemented feature:
   ``` java
   @Test
   public void UnimplementedFeat() {
         fail("Not yet implemented");
   }
   ```

   Test condition:
   ```java

   @Test
   public void testCondition() {
      if (cond){
         fail("Error on the cond");
      }
      else{
         //some code
      }
   }

   ```


4. We can target with more precision the expected exception and its also more explicit to those who review the test code.