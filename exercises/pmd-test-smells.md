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

`pmd check -d commons-collections/src/test -R category/java/errorprone.xml -f text`

`commons-collections/src/test/java/org/apache/commons/collections4/map/AbstractMapTest.java:2048: DetachedTestCase: Probable detached JUnit test case.`

As the warning suggests it, the issue here is that the method is a test but it's missing the annotation `@Test`

```java
@Test
public void verifyValues() {
    final List<V> known = new ArrayList<>(getConfirmed().values());

    values = getMap().values();

    final List<V> test = new ArrayList<>(values);

    final int size = getConfirmed().size();
    final boolean empty = getConfirmed().isEmpty();
    assertEquals(size, values.size(),
            "values should be same size as HashMap's" +
                    "\nTest: " + test + "\nReal: " + known);
    assertEquals(empty, values.isEmpty(),
            "values should be empty if HashMap is" +
                    "\nTest: " + test + "\nReal: " + known);
    assertTrue(test.containsAll(known),
            "values should contain all HashMap's elements" +
                    "\nTest: " + test + "\nReal: " + known);
    assertTrue(known.containsAll(test),
            "values should contain all HashMap's elements" +
                    "\nTest: " + test + "\nReal: " + known);
    // originally coded to use a HashBag, but now separate jar so...
    for (final V v : known) {
        final boolean removed = test.remove(v);
        assertTrue(removed, "Map's values should still equal HashMap's");
    }
    assertTrue(test.isEmpty(), "Map's values should still equal HashMap's");
}
```
