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

#### Identify which of the test smells discussed in classes are implemented by these rules.

*DetachedTestCase* : permet de détecter les cas de test d'une classe de test qui ne sont pas précédés par l'annotation ```@Test```

*JUnit4SuitesShouldUseSuiteAnnotation*, *JUnit4SuitesShouldUseSuiteAnnotation* , *JUnit4TestShouldUseAfterAnnotation*, *JUnit4TestShouldUseBeforeAnnotation*, *JUnit4TestShouldUseTestAnnotation*, *JUnitAssertionsShouldIncludeMessage*, *JUnitSpelling*, *JUnitStaticSuite* : permet de vérifier que les bonnes annotations et fonctions  ont été utilisées pour effectuer les tests.

*JUnitTestContainsTooManyAsserts*, *JUnitTestsShouldIncludeAssert*, *UnnecessaryBooleanAssertion*, *UseAssertEqualsInsteadOfAssertTrue*, *UseAssertEqualsInsteadOfAssertTrue* , *UseAssertNullInsteadOfAssertTrue*, *UseAssertSameInsteadOfAssertTrue*, *UseAssertTrueInsteadOfAssertEquals* : présente les meilleures manières d'utiliser les Assert dans les classes de tests

*JUnitUseExpectedJUnitUseExpected* : gestion des exceptions dans les cas de test

#### Discuss the test smell you found with the help of PMD and propose here an improvement. Include the improved test code in this file.

Dans le projet ```commons-collections``` plus précisement dans la classe de test ```ListUtilsTest.java```, la règle JUnitTestContainsTooManyAsserts a été utilisée: 

```./run.sh pmd -d ./ListUtilsTest.java -R category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts -f  html > err.html```

Plusieurs test smells sont ressorties parmi lesquelles on a : 

```html <tr bgcolor="lightgrey"> 
<td align="center">1</td>
<td width="*%">/home/diane/pmd/pmd-bin-6.43.0/bin/ListUtilsTest.java</td>
<td align="center" width="5%">64</td>
<td width="*"><a href="https://pmd.github.io/pmd-6.43.0/pmd_rules_java_bestpractices.html#junittestcontainstoomanyasserts">Unit tests should not contain more than 1 assert(s).</a></td>
</tr>
```
Ce test smell est dû à l'utilisation de beaucoup d'asserts dans le cas de test ci-dessous :
```java
    @Test
    public void testGetFirst() {
        assertEquals(a, ListUtils.getFirst(fullList));
        assertThrows(NullPointerException.class, () -> ListUtils.getFirst(null));
        assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.getFirst(new ArrayList<>()));
    }
```

Pour éviter ce test smell, on pourrait réécrire le cas de test de cette manière : 
```java
    @Test
    public void testGetFirst() {
        assertEquals(a, ListUtils.getFirst(fullList));
    }
    
    @Test
    public void testGetSecond() {   
      assertThrows(NullPointerException.class, () -> ListUtils.getFirst(null));
    }
    
    @Test
    public void testGetThird() {
       assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.getFirst(new ArrayList<>()));
    }
  ```
    
    
    






































