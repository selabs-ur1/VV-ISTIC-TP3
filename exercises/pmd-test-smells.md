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

I used the rule `UnitTestShouldIncludeAssert` on the Apache Commons Collections project source code :

```bash
pmd check -d commons-collections/ -R category/java/bestpractices.xml/UnitTestShouldIncludeAssert
```

And here's a test that got flagged :

`commons-collections/src/test/java/org/apache/commons/collections4/AbstractObjectTest.java:283:  UnitTestShouldIncludeAssert:    This unit test should include assert() or fail()`

```java
/**
 * Sanity check method, makes sure that any Serializable
 * class can be serialized and de-serialized in memory,
 * using the handy makeObject() method
 *
 * @throws IOException
 * @throws ClassNotFoundException
 */
@Test
public void testSimpleSerialization() throws Exception {
    final Object o = makeObject();
    if (o instanceof Serializable && isTestSerialization()) {
        final byte[] object = writeExternalFormToBytes((Serializable) o);
        readExternalFormFromBytes(object);
    }
}
```

It seems that the goal of this test is simply to be executed without throwing an exception. However it is not explicit, no comments explain the success or failure cases of the test, or why each exception would be thrown.

A better way to write this test would be like so :

```diff
/**
 * Sanity check method, makes sure that any Serializable
 * class can be serialized and de-serialized in memory,
 * using the handy makeObject() method
- *
- * @throws IOException
- * @throws ClassNotFoundException
 */
@Test
public void testSimpleSerialization() throws Exception {
    final Object o = makeObject();
    if (o instanceof Serializable && isTestSerialization()) {
+        assertDoesNotThrow(() -> {
            final byte[] object = writeExternalFormToBytes((Serializable) o);
            readExternalFormFromBytes(object);
+        });
    }
}
```
