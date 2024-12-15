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

Les règles qui implémentent ces "test smells" sont:
JUnitSpelling, UnitTestContainsTooManyAsserts, UnitTestShouldUseTestAnnotation, UnitTestShouldIncludeAssert, UnnecessaryBooleanAssertion, UseAssertEqualsInsteadOfAssertTrue, UseAssertNullInsteadOfAssertTrue, UseAssertSameInsteadOfAssertTrue, UseAssertTrueInsteadOfAssertEquals.

Règle choisie: UnitTestContainsTooManyAsserts
Utilisation sur Apache Commons Math avec "pmd check -d commons-math-master -R category/java/bestpractices.xml/JUnitTestContainsTooManyAsserts -r pmd_test_tp3_testshouldincludeassert.html -f html"

En effet, dans le cas de test il y a deux "assertEquals".

Amélioration possible: séparer le cas de test en deux pour testerla partie gauche et droite séparemment.

    @Test
    public void testAccessorLeft() {
        final Pair<Integer, Double> p
            = new Pair<>(Integer.valueOf(1), Double.valueOf(2));
        Assert.assertEquals(Integer.valueOf(1), p.getKey());
    }
    @Test
    public void testAccessorRight() {
        final Pair<Integer, Double> p
            = new Pair<>(Integer.valueOf(1), Double.valueOf(2));
        Assert.assertEquals(2, p.getValue().doubleValue(), Math.ulp(1d));
    }


