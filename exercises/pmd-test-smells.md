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

/VV/commons-collections/src/test/java/org/apache/commons/collections4/map/AbstractMapTest.java	ligne 2510

```java
    public void verifyEntrySet() {
        final int size = getConfirmed().size();
        final boolean empty = getConfirmed().isEmpty();
        assertEquals(size, entrySet.size(), "entrySet should be same size as HashMap's" + "\nTest: " + entrySet + "\nReal: " + getConfirmed().entrySet());
        assertEquals(empty, entrySet.isEmpty(), "entrySet should be empty if HashMap is" + "\nTest: " + entrySet + "\nReal: " + getConfirmed().entrySet());
        assertTrue(entrySet.containsAll(getConfirmed().entrySet()),
                "entrySet should contain all HashMap's elements" + "\nTest: " + entrySet + "\nReal: " + getConfirmed().entrySet());
        assertEquals(getConfirmed().entrySet().hashCode(), entrySet.hashCode(),
                "entrySet hashCodes should be the same" + "\nTest: " + entrySet + "\nReal: " + getConfirmed().entrySet());
        assertEquals(getConfirmed().entrySet(), entrySet, "Map's entry set should still equal HashMap's");
    }
```
Il n'y a pas le @Test ou @Ignore, le test manque d'explications grâce à un commentaire.
