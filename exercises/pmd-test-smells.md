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

```
commons-collections-master/src/test/java/org/apache/commons/collections4/GuavaTestlibTest.java:55:
JUnit4SuitesShouldUseSuiteAnnotation:	
JUnit 4 indicates test suites via annotations, not the suite method. 
```

La règle `JUnit4SuitesShouldUseSuiteAnnotation` détecte un test smell dans le fichier : *commons-collections-master/src/test/java/org/apache/commons/collections4/GuavaTestlibTest.java*.
Le test utilise la méthode suite() qui n'est pas la plus appropriée dans ce genre de situation, il faut utiliser les annotations @RunWith et @Suite plutôt.
Cela rend le code beaucoup plus lisible et maintenable plutôt que la méthode suite qui est plus lourde.

# Avant

```
public final class GuavaTestlibTest extends TestCase {

    public static Test suite() {
        final TestSuite test = new TestSuite();
        // Map
        test.addTest(suiteMap("HashedMap", HashedMap::new));
        test.addTest(suiteMap("LinkedMap", LinkedMap::new));
        test.addTest(suiteMap("LRUMap", LRUMap::new));
        test.addTest(suiteMap("ReferenceMap", ReferenceMap::new));
        // List
        test.addTest(suiteList("TreeList", TreeList::new));
        // TODO: In COLLECTIONS-811 we enabled the list tests for TreeList, but these other two types did not
        //       pass the tests. Someone needs to confirm if it is a bug in the code, or we need to change the
        //       test features.
        // test.addTest(suiteList("GrowthList", GrowthList::new, CollectionFeature.SERIALIZABLE));
        // test.addTest(suiteList("CursorableLinkedList", CursorableLinkedList::new, CollectionFeature.SERIALIZABLE));
        return test;
    }
```

# Après

```
@RunWith(Suite.class)
@Suite.SuiteClasses({
        HashedMapTest.class,
        LinkedMapTest.class,
        LRUMapTest.class,
        ReferenceMapTest.class,
        TreeListTest.class
})
```



