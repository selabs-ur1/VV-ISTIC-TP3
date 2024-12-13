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
Pour le test ``UnitTestContainsTooManyAsserts`` PMD retourne `.\src\test\java\org\apache\commons\collections4\IteratorUtilsTest.java:381:	UnitTestContainsTooManyAsserts:	Unit tests should not contain more than 1 assert(s).
`
Le test est le suivant : 
````java
@Test
public void testAsIterator() {
    final Vector<String> vector = new Vector<>();
    vector.addElement("zero");
    vector.addElement("one");
    final Enumeration<String> en = vector.elements();
    assertTrue(IteratorUtils.asIterator(en) instanceof Iterator, "create instance fail");
    assertThrows(NullPointerException.class, () -> IteratorUtils.asIterator(null));
}
````
On peut séparer les assert et faire 2 tests pour soit : 

````java
@Test
public void testAsIterator() {
    final Vector<String> vector = new Vector<>();
    vector.addElement("zero");
    vector.addElement("one");
    final Enumeration<String> en = vector.elements();
    assertThrows(NullPointerException.class, () -> IteratorUtils.asIterator(null));
}
````
et 
````java
@Test
public void testAsIterator() {
    final Vector<String> vector = new Vector<>();
    vector.addElement("zero");
    vector.addElement("one");
    final Enumeration<String> en = vector.elements();
    assertTrue(IteratorUtils.asIterator(en) instanceof Iterator, "create instance fail");
}
````