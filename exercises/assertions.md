# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. the expression : (* .4 == 1.2) has a result wich is approximative. we should test a range of values.

2. assertEquals check the values of parameters and assertSame check the reference of the parameters.

3. we can use fail to test uncomplete code or unimplemented code, for example :

import org.junit.Test;
import static org.junit.Assert.fail;

public class CodeTest {

    public void testIncompleteFeature() {
        fail("Feature not yet implemented");

        
    }

    // code implemented to test
}

4. assertThrow is specialized for testing exception and permit a better read of the test's code.