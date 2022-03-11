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

PMD Result : 
```txt
AkimaSplineInterpolatorTest.java:249:      UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
```
It is best to use assertEquals instead of assertTrue with .equals() for readability and it is the purpose of the assertEquals.
