# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.
2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. float values aren't precise number and may have very little variation, a good way to test float values is to get the absolute value of the result and tolerate up to a certain treshold of difference :
```assertTrue(Math.abs(3 * .4 - 1.2) < 0.000001);```

2. assertEquals check if the values of an object are the same, as assertSame check if the two references concern the same instance of an object:
```
Object a = new Object("test");
Object b = new Object("test");

assertEquals(a,b); // return True
assertSame(a,b);   // return False
---------------------------------
Object a = new Object("test");
Object b = a;

assertEquals(a,b); // return True
assertSame(a,b);   // return True
 ```

3. (source:https://www.baeldung.com/junit-fail) the method fail has multiple functions:
   1. you can use fail to indicate that a test method is not implemented yet or incomplete
   ```
   @Test
   public void incompleteTest() {
    fail("Not yet implemented");
   } 
   ```
   2. you can use fail to indicate that a test should have returned an exception
   ```
    @Test
    public void expectedException() {
     try {
        methodThrowsException();
        fail("Expected exception was not thrown");
     } catch (Exception e) {
        assertNotNull(e);
     }
    }
   ```
   3. you can use fail to stop a test when the test doesn't meet the desired condition
   ```
   @Test
   public void testingCondition() {
    int result = randomInteger();
    if(result > Integer.MAX_VALUE) {
        fail("Result cannot exceed integer max value");
    }
    // more testing code
   }
   ```
   4. you can use fail to fail a test when the code doesn't return or break when expected
   ```
   @Test
   public void returnBefore() {
    int value = randomInteger();
    for (int i = 0; i < 5; i++) {
        // returns when (value + i) is an even number
        if ((i + value) % 2 == 0) {
            return;
        }
    }
    fail("Should have returned before");
   }
   ```
4.
   - pour Junit4 : ``` @Test(expected = NullPointerException.class) ```
   - pour junit5 :
     ```
     assertThrows(NullPointerException.class,
      ()->{
       //code that throws an exception   
      });
     ```
using the method assertThrows makes the code clearer

