# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

I used pmd rules on `commons-collections` of apache project. I run with all the rules of `errorprone.xml` and `bestpractices.xml` so I got all 
the test smells that were made on this project, and then I choose one specific : `JunitStaticSuite` that is related on pmd-documentation but when I run pmd I got no result, 
so no test-smell for this rule in the project. Then I run with the `JUnitUseExpected` rule, and we got results. But unfortunately it is a Junit4 test-smells and 
the project use Junit5. 

Maybe this rule should be ignored when the project use Junit5. Despite, if we are on Junit4, the problem occurs when we are trying to catch an exception. 
To solve this issue, we have to annotate the exception like this ```@Test(expected=Exception.class)```

```java
public class MyTest {
    
    @Test(expected = IllegalArgumentException.class)
    public final void constructorTest_WrongShape() {
        final Shape anotherShape = new Shape(testFunctionX, 3, 72, 17);

        final List<Integer> lst = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        final Hasher hasher = new StaticHasher(lst.iterator(), anotherShape);
        createFilter(hasher, shape);
    }
}
```

At least I run pmd with DetachedTestCase that return a problem when some methods are written in testing class
without the `@Test` annotation, so this is not a considered as a test but as a method and the accessor is public 
when it should be private. 

```java

public class MyTest {
    
    @Test
    public void getFromIterable() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", IterableUtils.get(bag, 0));
    }
}
```

This code is for sure a testing case because of the `assertEquals` so I add the `@Test` annotation

