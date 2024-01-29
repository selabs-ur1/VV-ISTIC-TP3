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

In class, we discussed several test smells, including:

- JUnitTestsShouldIncludeAssert
- TestClassWithoutTestCases
- UnnecessaryBooleanAssertion

We chose TestClassWithoutCases, and here's what we get when we run it with PMD:

```
C:\Users\fjord\Documents\V&V\commons-collections> pmd check -f text -R category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts -d .\src\test
[main] INFO net.sourceforge.pmd.cli - Log level is at INFO
[main] WARN net.sourceforge.pmd.cli - Progressbar rendering conflicts with reporting to STDOUT. No progressbar will be shown. Try running with argument -r <file> to output the report to a file instead.          
[main] WARN net.sourceforge.pmd.cli - This analysis could be faster, please consider using Incremental Analysis: https://docs.pmd-code.org/pmd-doc-7.0.0 rc4/pmd_userdocs_incremental_analysis.html                
.\src\test\java\org\apache\commons\collections4\AbstractArrayListTest.java:44:  JUnitTestContainsTooManyAsserts:        Unit tests should not contain more than 1 assert(s).                                       
.\src\test\java\org\apache\commons\collections4\AbstractArrayListTest.java:54:  JUnitTestContainsTooManyAsserts:        Unit tests should not contain more than 1 assert(s).                                       
.\src\test\java\org\apache\commons\collections4\AbstractLinkedListTest.java:74: JUnitTestContainsTooManyAsserts:        Unit tests should not contain more than 1 assert(s).                                       
.\src\test\java\org\apache\commons\collections4\AbstractLinkedListTest.java:96: JUnitTestContainsTooManyAsserts:        Unit tests should not contain more than 1 assert(s). 
```

In the first report, we can see that this test has more than one assert:

```java
@Test  
public void testNewArrayList() {  
    final ArrayList<E> list = makeObject();  
    assertTrue(list.isEmpty(), "New list is empty");  
    assertEquals(0, list.size(), "New list has size zero");  
  
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));  
}
```

We can correct it as follows:

```java
@Test
public void testNewArrayListIsEmpty() {
    final ArrayList<E> list = makeObject();
    assertTrue(list.isEmpty(), "New list is empty");
}

@Test
public void testNewArrayListSizeIsZero() {
    final ArrayList<E> list = makeObject();
    assertEquals(0, list.size(), "New list has size zero");
}

@Test
public void testNewArrayListGetThrowsException() {
    final ArrayList<E> list = makeObject();
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
}
```
