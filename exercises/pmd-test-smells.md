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

Les smells vus en cours implémentées dans les règles pmd de `pmd-documentation` sont :
- Eager Test -> JUnitTestContainsTooManyAsserts
- Assertion Roulette -> JUnitTestContainsTooManyAsserts && JUnitTestsShouldIncludeMessage
- The Free Ride -> JUnitTestContainsTooManyAsserts
- Interacting Test -> JUnit4TestShouldUseBeforeAnnotation && JUnit4TestShouldUseAfterAnnotation

On exécute pmd sur Apache Commons Collections avec la règle `UseAssertTrueInsteadOfAssertEquals`.
Il est préférable d'utiliser `assertTrue` et `assertFalse` plutôt que d'utiliser `assertEquals` avec un booléen déterminé. Ces méthodes sont optimisées pour ces comparaisons et permettent une meilleur lisibilité du code.  
On obtient le résultat suivant :
```bat
C:\Users\maxim\Desktop\commons-collections-master>pmd -d ./src -R category/java/bestpractices.xml/UseAssertTrueInsteadOfAssertEquals -f text
janv. 23, 2024 1:21:10 PM net.sourceforge.pmd.PMD encourageToUseIncrementalAnalysis
AVERTISSEMENT: This analysis could be faster, please consider using Incremental Analysis: https://pmd.github.io/pmd-6.55.0/pmd_userdocs_incremental_analysis.html
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:191:  UseAssertTrueInsteadOfAssertEquals:    Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:194:  UseAssertTrueInsteadOfAssertEquals:    Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:244:  UseAssertTrueInsteadOfAssertEquals:    Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:245:  UseAssertTrueInsteadOfAssertEquals:    Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:246:  UseAssertTrueInsteadOfAssertEquals:    Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:247:  UseAssertTrueInsteadOfAssertEquals:    Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
```

On choisie le premier smell et on remplace ensuite la méthode `assertEquals(false)` par `assertFalse` :

Test présent dans le package collections d'Apache :
```java
@Test
public void testInvokerTransformer2() {
    final List<Object> list = new ArrayList<>();

    
    assertEquals(Boolean.FALSE, TransformerUtils.invokerTransformer("contains",
            new Class[] { Object.class }, new Object[] { cString }).transform(list));


    list.add(cString);
    assertEquals(Boolean.TRUE, TransformerUtils.invokerTransformer("contains",
            new Class[] { Object.class }, new Object[] { cString }).transform(list));
    assertNull(TransformerUtils.invokerTransformer("contains",
            new Class[]{Object.class}, new Object[]{cString}).transform(null));
    assertAll(
            () -> assertThrows(NullPointerException.class, () -> TransformerUtils.invokerTransformer(null, null, null)),
            () -> assertThrows(FunctorException.class, () -> TransformerUtils.invokerTransformer("noSuchMethod", new Class[]{Object.class},
                    new Object[]{cString}).transform(new Object())),
            () -> assertThrows(IllegalArgumentException.class, () -> TransformerUtils.invokerTransformer("badArgs", null, new Object[]{cString})),
            () -> assertThrows(IllegalArgumentException.class, () -> TransformerUtils.invokerTransformer("badArgs", new Class[]{Object.class}, null)),
            () -> assertThrows(IllegalArgumentException.class, () -> TransformerUtils.invokerTransformer("badArgs", new Class[]{}, new Object[]{cString}))
    );
}
```

Test modifié pour que le smell n'apparaisse plus :
```java
@Test
public void testInvokerTransformer2() {
    final List<Object> list = new ArrayList<>();


    assertFalse(TransformerUtils.invokerTransformer("contains",
            new Class[] { Object.class }, new Object[] { cString }).transform(list));


    list.add(cString);
    assertEquals(Boolean.TRUE, TransformerUtils.invokerTransformer("contains",
            new Class[] { Object.class }, new Object[] { cString }).transform(list));
    assertNull(TransformerUtils.invokerTransformer("contains",
            new Class[]{Object.class}, new Object[]{cString}).transform(null));
    assertAll(
            () -> assertThrows(NullPointerException.class, () -> TransformerUtils.invokerTransformer(null, null, null)),
            () -> assertThrows(FunctorException.class, () -> TransformerUtils.invokerTransformer("noSuchMethod", new Class[]{Object.class},
                    new Object[]{cString}).transform(new Object())),
            () -> assertThrows(IllegalArgumentException.class, () -> TransformerUtils.invokerTransformer("badArgs", null, new Object[]{cString})),
            () -> assertThrows(IllegalArgumentException.class, () -> TransformerUtils.invokerTransformer("badArgs", new Class[]{Object.class}, null)),
            () -> assertThrows(IllegalArgumentException.class, () -> TransformerUtils.invokerTransformer("badArgs", new Class[]{}, new Object[]{cString}))
    );
}
```

Résultats pmd après changement du test (le premier smell (ligne 191) n'apparait plus) :
```bat
C:\Users\maxim\Desktop\commons-collections-master>pmd -d ./src -R category/java/bestpractices.xml/UseAssertTrueInsteadOfAssertEquals -f text
janv. 23, 2024 1:59:38 PM net.sourceforge.pmd.PMD encourageToUseIncrementalAnalysis
AVERTISSEMENT: This analysis could be faster, please consider using Incremental Analysis: https://pmd.github.io/pmd-6.55.0/pmd_userdocs_incremental_analysis.html
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:194:  UseAssertTrueInsteadOfAssertEquals:     Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:244:  UseAssertTrueInsteadOfAssertEquals:     Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:245:  UseAssertTrueInsteadOfAssertEquals:     Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:246:  UseAssertTrueInsteadOfAssertEquals:     Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
.\src\test\java\org\apache\commons\collections4\TransformerUtilsTest.java:247:  UseAssertTrueInsteadOfAssertEquals:     Use assertTrue(x)/assertFalse(x) instead of assertEquals(true, x)/assertEquals(false, x) or assertEquals(Boolean.TRUE, x)/assertEquals(Boolean.FALSE, x).
```
