# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1.
The following assertion fails because of the limitations of floating-point arithmetic, computers have to represent real number in a finite presision.
This type of check should by done by having a tolerance between the result and the value expected.
Here's an example of how we can modify the assertion :
```java
assertEquals(3 * .4, 1.2, 1e-10);
```

2.
assertEquals check if object are equals by comparating their values, assertSame check if they are the same object object in memory.

```java
// same result
assertEquals(1, 1);
assertSame(1, 1);

// different result
assertEquals(new ArrayList<String>(), new ArrayList<String>());
assertSame(new ArrayList<String>(), new ArrayList<String>());
```

3.
`fail` is also usefull when a method have precondition, if preconditions are not met, the fail provides an usefull feedback
```java
@Test
public void test(){
    if(this.obj == null){
        fail("obj sould have been initialized");
    }
    assertEquals(this.obj.a, 16);
}
```

4.
With assertThrows we can capture the thrown exeption, so we can test that the code return the specific expected exeption, instead of testing that it return any exeption.