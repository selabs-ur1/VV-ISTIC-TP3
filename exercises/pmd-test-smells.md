# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD
rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

[JUnitTestContainsTooManyAsserts.md](../pmd-documentation/JUnitTestContainsTooManyAsserts.md) sur [Apache Commons Collections](https://github.com/apache/commons-collections)

Problème repéré :
```bash
    @Test
    public void testNewArrayList() {
      final ArrayList<E> list = makeObject();
      assertTrue(list.isEmpty(), "New list is empty");
      assertEquals(0, list.size(), "New list has size zero");

      assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }
```

Correction possible :
```bash
    @Test
    public void testNewArrayListIsEmpty() {
      final ArrayList<E> list = makeObject();
      assertTrue(list.isEmpty(), "New list is empty");
    }
    
    @Test
    public void testNewArrayListHasSizeZero() {
      final ArrayList<E> list = makeObject();
      assertEquals(0, list.size(), "New list has size zero");
    }
    
    @Test
    public void testEmptyArrayListOutOfBoundOnFirstIndex() {
      final ArrayList<E> list = makeObject();
      assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }
```