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

DetachedTestCase = Aucune annotation pour un test

JUnit4SuitesShouldUseSuiteAnnotation, JUnit4TestShouldUseAfterAnnotation, JUnit4TestShouldUseBeforeAnnotation, JUnit4TestShouldUseTestAnnotation, JUnitSpelling, JUnitTestsShouldIncludeAssert = Permet de vérifier l'utilisation des bonnes annotations pour l'automatisations des tests

JUnitTestContainsTooManyAsserts = Trop d'assertions dans un seul test : Le Free ride ( il y a trop de fonctionnalités testées ).

JUnitUseExpected = Happy path, utilisation des bons catcheur d'exeption

UseAssertEqualsInsteadOfAssertTrue, UseAssertNullInsteadOfAssertTrue, UseAssertSameInsteadOfAssertTrue, UseAssertTrueInsteadOfAssertEquals = utilisation du mauvais assert

Comme example nous avons dans le Apache Commons Lang, dans la classe de test AnnotationUtilsTest.java, et à la ligne 218, le test suivant :
```
   @Test
    public void testOneArgNull() {
        assertFalse(AnnotationUtils.equals(field1.getAnnotation(TestAnnotation.class), null));
        assertFalse(AnnotationUtils.equals(null, field1.getAnnotation(TestAnnotation.class)));
    }
```    
Pour corriger ce test smell nous pouvons faire :
```
   @Test
    public void testOneArgNull() {
        assertFalse(AnnotationUtils.equals(field1.getAnnotation(TestAnnotation.class), null));
    }
    @Test
    public void testOneNullArg() {
        assertFalse(AnnotationUtils.equals(null, field1.getAnnotation(TestAnnotation.class)));
    }
```
