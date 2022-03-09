# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. the assertion tries to compare strictly two floats, where it should check that both values are within a margin `Ɛ`.  
A correct assertion comparing equality of two floating points would be `assertEquals(3*.4, 1.2, 0.000001)`.
2. `assertEquals(x,y)` checks that x and y ahave the same value, whereas `assertSame(x,y)` checks that x and y are the same reference.  
3. A fail can be used in a catch, such that if the tested code throws an exception, the test fails :
```java
@Test
public void testFail(){
    try{
        //do something that should not throw exception
        assert(...)
    }
    catch(MyException e){
        fail("Exception thrown : "+e.getMessage());
    }
}
```
4. it allows to do something after the exception was thrown, such as asserting equalities and not just stop the test when the exception was thrown.
