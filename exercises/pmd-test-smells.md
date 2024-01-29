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

Command run:
```bash
pmd check -d commons-collections/src/test/ -R bestpractices.xml/JUnitTestContainsTooManyAsserts
```

Problem:
```java
   @Test
    public void testComparatorChainOnMinValuedComparator() {
        // -1 * Integer.MIN_VALUE is less than 0,
        // test that ComparatorChain handles this edge case correctly
        final ComparatorChain<Integer> chain = new ComparatorChain<>();
        chain.addComparator((a, b) -> {
            final int result = a.compareTo(b);
            if (result < 0) {
                return Integer.MIN_VALUE;
            }
            if (result > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }, true);

        assertTrue(chain.compare(4, 5) > 0);
        assertTrue(chain.compare(5, 4) < 0);
        assertEquals(0, chain.compare(4, 4));
    }
```

Here is a solution to avoid the bad smell:
```java
   @Test
    public void testComparatorChainOnMinValuedComparatorInfSup() {
        // -1 * Integer.MIN_VALUE is less than 0,
        // test that ComparatorChain handles this edge case correctly
        final ComparatorChain<Integer> chain = new ComparatorChain<>();
        chain.addComparator((a, b) -> {
            final int result = a.compareTo(b);
            if (result < 0) {
                return Integer.MIN_VALUE;
            }
            if (result > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }, true);

        assertTrue(chain.compare(4, 5) > 0);
    }
    
    @Test
    public void testComparatorChainOnMinValuedComparatorSupInf() {
        // -1 * Integer.MIN_VALUE is less than 0,
        // test that ComparatorChain handles this edge case correctly
        final ComparatorChain<Integer> chain = new ComparatorChain<>();
        chain.addComparator((a, b) -> {
            final int result = a.compareTo(b);
            if (result < 0) {
                return Integer.MIN_VALUE;
            }
            if (result > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }, true);

        assertTrue(chain.compare(5, 4) < 0);
    }
    
    @Test
    public void testComparatorChainOnMinValuedComparatorEqual() {
        // -1 * Integer.MIN_VALUE is less than 0,
        // test that ComparatorChain handles this edge case correctly
        final ComparatorChain<Integer> chain = new ComparatorChain<>();
        chain.addComparator((a, b) -> {
            final int result = a.compareTo(b);
            if (result < 0) {
                return Integer.MIN_VALUE;
            }
            if (result > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }, true);

        assertEquals(0, chain.compare(4, 4));
    }
```
