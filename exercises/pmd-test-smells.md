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

Command
```
pmd check -d "E:\IntelliJ IDEA\commons-collections\src\main\java\org\apache\commons\collections4" -R category/java/bestpractices.xml
```

Results
```
...ction\AbstractCollectionTest.java:1284:	JUnitUseExpected:	In JUnit4, use the @Test(expected) annotation to denote tests that should throw ex...
...ators\AbstractMapIteratorTest.java:139:	JUnitUseExpected:	In JUnit4, use the @Test(expected) annotation to denote tests that should throw ex...
...lections4\map\AbstractMapTest.java:850:	JUnitUseExpected:	In JUnit4, use the @Test(expected) annotation to denote tests that should throw ex...
...lections4\map\AbstractMapTest.java:861:	JUnitUseExpected:	In JUnit4, use the @Test(expected) annotation to denote tests that should throw ex...
...lections4\map\AbstractMapTest.java:905:	JUnitUseExpected:	In JUnit4, use the @Test(expected) annotation to denote tests that should throw ex...
...lections4\map\AbstractMapTest.java:925:	JUnitUseExpected:	In JUnit4, use the @Test(expected) annotation to denote tests that should throw ex...
```

In exercice one, we saw that the `@Test(expected)` annotation should not be used, and `assertThrows()` should be used instead.

But, the problem is, some people were used to using `fail("should have thrown an exception");` when code reached a technically unreachable part, because an exception didn't trigger.

This ruleset makes sure that these kind of tests do not exist, and recommends people to at least use the `@Test(expected)` annotation. Ideally though, they should even use `assertThrows()`.

Here is the before and after of the code with this bad smell.

**File :** `commons-collections\src\test\java\org\apache\commons\collections4\map\AbstractMapTest.java:832`
```java
if (isPutChangeSupported()) {
    ...
} else {
    try {
        // two possible exception here, either valid
        getMap().put(keys[0], newValues[0]);
        fail("Expected IllegalArgumentException or UnsupportedOperationException on put (change)");
    } catch (final IllegalArgumentException | UnsupportedOperationException ex) {
        // ignore
    }
}
```
```java
if (isPutChangeSupported()) {
    ...
} else {
    try {
        // two possible exception here, either valid
        assertThrows(
                IllegalArgumentException.class,
                () -> getMap().put(keys[0], newValues[0]),
                "Expected IllegalArgumentException or UnsupportedOperationException on put (change)"
        );
    } catch (final IllegalArgumentException | UnsupportedOperationException ex) {
        // ignore
    }
}
```