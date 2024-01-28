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

I cannot remember which rules was discussed in class, but I think most of them have been showed up.

Here is a test founded by using `UseAssertEqualsInsteadOfAssertTrue` in `commons-collections` de Apache
dans la classe de test `org/apache/commons/collections4/map/ReferenceIdentityMapTest.java`

```java
@Test
@SuppressWarnings("unchecked")
public void testHashEntry() {
final IterableMap<K, V> map = new ReferenceIdentityMap<>(ReferenceStrength.HARD, ReferenceStrength.HARD);

        map.put((K) I1A, (V) I2A);
        map.put((K) I1B, (V) I2A);

        final Map.Entry<K, V> entry1 = map.entrySet().iterator().next();
        final Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        final Map.Entry<K, V> entry2 = it.next();
        final Map.Entry<K, V> entry3 = it.next();

        assertTrue(entry1.equals(entry2));
        assertTrue(entry2.equals(entry1));
        assertFalse(entry1.equals(entry3));
    }
```

Both assertTrue(a.equals(b)) could be replaced by assertEquals(a, b)