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
These rules implement many test smell:
- Test with no annotation
- Test with too much logic in it
- Test with too many asserts
- Test using assertTrue instead of assertEquals

I used this pmd command : ```pmd check .\commons-collections\src\test\ -R category/java/errorprone.xml/DetachedTestCase```

We found a detached TestCase :

Here is the trace of the result
```.\commons-collections\src\test\java\org\apache\commons\collections4\IterableUtilsTest.java:337: DetachedTestCase:       Probable detached JUnit test case.```

```java
public void getFromIterable() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", IterableUtils.get(bag, 0));
    }
```

There is 2 problems according to me in this testCase.
- Firstly, the name of the test
- Secondly, the annotation is missing

I would correct this test like this :
```java
@Test
public void testGetFromIterable() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", IterableUtils.get(bag, 0));
    }
```


