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

I try check with `DetachedTestCase` rule on commun-math library :
```shell
commons-math\commons-math-legacy-core\src\test\java\org\apache\commons\math4\legacy\core\MathArraysTest.java:712:       DetachedTestCase:       Probable detached JUnit test case.
commons-math\commons-math-legacy\src\test\java\org\apache\commons\math4\legacy\linear\SingularValueDecompositionTest.java:169:  DetachedTestCase:       Probable detached JUnit test case.
commons-math\commons-math-legacy\src\test\java\org\apache\commons\math4\legacy\linear\SingularValueDecompositionTest.java:202:  DetachedTestCase:       Probable detached JUnit test case.
commons-math\commons-math-legacy\src\test\java\org\apache\commons\math4\legacy\stat\descriptive\rank\PercentileTest.java:251:   DetachedTestCase:       Probable detached JUnit test case.
commons-math\commons-math-legacy\src\test\java\org\apache\commons\math4\legacy\stat\inference\KolmogorovSmirnovTestTest.java:145:       DetachedTestCase:       Probable detached JUnit test case.
commons-math\commons-math-legacy\src\test\java\org\apache\commons\math4\legacy\stat\inference\KolmogorovSmirnovTestTest.java:370:       DetachedTestCase:       Probable detached JUnit test case.
commons-math\commons-math-legacy\src\test\java\org\apache\commons\math4\legacy\stat\regression\MillerUpdatingRegressionTest.java:559:   DetachedTestCase:       Probable detached JUnit test case.
```

The rule verify `@Test` or `@Ignore` annotation isn't missing. So we can just add this annotation to the function.
Before:
```java
public void testConcatenateEmptyArguments() {
    //...
}
```
After:
```java
@Test or @Ignore
public void testConcatenateEmptyArguments() {
    //...
}
```