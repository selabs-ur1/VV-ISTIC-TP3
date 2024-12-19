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

Les bad smell vu sont : 
- JUnit4SuitesShouldUseSuiteAnnotation pour verfier que les annotations @SuiteClasses sont bien présentes pour les suites
- UnitTestShouldUseTestAnnotation pour verfier que les annotations @Test de Junit4 sont bien présente
- UnitTestContainsTooManyAsserts l'interdiction d'utiliser plusieurs assert dans un test qui va de paire avec : UnitTestShouldIncludeAssert pour minimum 1 assert
- UnitTestShouldUseAfterAnnotation l'utilisation de @AfterEach @AfterAll pour des opérations après les tests
-

J'ai trouver plusieurs erreur de type JUnitTestContainsTooManyAsserts.

```
PS C:\Users\alexa\OneDrive\Bureau\Cours\PMD_VV\pmd-dist-7.5.0-bin\pmd-bin-7.5.0\bin> pmd check -d 'C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4' -R category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts -f text
[WARN] Progressbar rendering conflicts with reporting to STDOUT. No progressbar will be shown. Try running with argument -r <file> to output the report to a file instead.
[WARN] This analysis could be faster, please consider using Incremental Analysis: https://docs.pmd-code.org/pmd-doc-7.5.0/pmd_userdocs_incremental_analysis.html
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\functors\EqualPredicateTest.java:53: JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapCompress672Test.java:83:      JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:132:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:155:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:168:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:191:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:241:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:295:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:357:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:374:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:426:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:440:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:454:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:479:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:494:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
PS C:\Users\alexa\OneDrive\Bureau\Cours\PMD_VV\pmd-dist-7.5.0-bin\pmd-bin-7.5.0\bin>
```

J'ai donc choisi l'erreur 
```
C:\Users\alexa\OneDrive\Bureau\Cours\TMP\commons-collections\src\test\java\org\apache\commons\collections4\map\MultiKeyMapTest.java:494:        JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).
```
qui contient 

```
@Test
    @SuppressWarnings("unchecked")
    public void testNullHandling() {
        resetFull();
        assertNull(map.get(null));
        assertFalse(map.containsKey(null));
        assertFalse(map.containsValue(null));
        assertNull(map.remove(null));
        assertFalse(map.entrySet().contains(null));
        assertFalse(map.containsKey(null));
        assertFalse(map.containsValue(null));

        assertThrows(NullPointerException.class, () -> map.put(null, null));

        assertNull(map.put(new MultiKey<>(null, null), null));

        assertThrows(NullPointerException.class, () -> map.put(null, (V) new Object()));
    }
```
Il y a en effet un bad smell il faudrait faire un test par assert pour mieux identifier les assert qui échoues.