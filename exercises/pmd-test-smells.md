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

## Test Smells 

The only rule discussed in classes that are implemented in the pmd-documentation is the piggybacking rule in the JUnitTestContainsTooManyAsserts.md

## JUnitTestContainsTooManyAsserts in commons-collection

```bash=
~/commons-collections/src/test/java/org/apache/commons/collections4/BagUtilsTest.java:60:	JUnitTestContainsTooManyAsserts:	Unit tests should not contain more than 1 assert(s).
```

#### Avant

```java=
@Test 
public void testUnmodifiableBag() {
    final Bag<Object> bag = BagUtils.unmodifiableBag(new HashBag<>());
    assertTrue(bag instanceof UnmodifiableBag, "Returned object should be an UnmodifiableBag.");
    try {
        BagUtils.unmodifiableBag(null);
        fail("Expecting NullPointerException for null bag.");
    } catch (final NullPointerException ex) {
        // expected
    }
    assertSame(bag, BagUtils.unmodifiableBag(bag), "UnmodifiableBag shall not be decorated");
}
```

#### Après

Une autre implémentation possible est de supprimer le premier assert qui sert de vérification et de créer un nouveau test dont le principe sera de vérifier cette assert
```java=
@Test 
public void testUnmodifiableBag() {
    final Bag<Object> bag = BagUtils.unmodifiableBag(new HashBag<>());
    try {
        BagUtils.unmodifiableBag(null);
        fail("Expecting NullPointerException for null bag.");
    } catch (final NullPointerException ex) {
        // expected
    }

    assertSame(bag, BagUtils.unmodifiableBag(bag), "UnmodifiableBag shall not be decorated");
}
```
```java=
@Test 
public void testShouldBeUnmodifiableBag() {
    final Bag<Object> bag = BagUtils.unmodifiableBag(new HashBag<>());
    assertTrue(bag instanceof UnmodifiableBag, "Returned object should be an UnmodifiableBag.");
}
```
